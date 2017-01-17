package components;

import components.entity.Direction;
import components.entity.Entity;
import components.entity.Link;
import components.entity.enemies.Enemy;
import components.map.AnimationObject;
import components.map.MapItem;
import components.map.collectibles.Collectible;
import utility.Images;
import utility.MapFactory;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class OverWorld
{
	private int drawX;
	private int drawY;
	private int drawVelX;
	private int drawVelY;

	private int cameraX;
	private int cameraY;
	private int cameraVelX;
	private int cameraVelY;

	private final int numOfColumns;
	private final int numOfRows;
	private final int widthOfTile;
	private final int heightOfTile;

	private final int mapWidth;
	private final int mapHeight;

	private final ArrayList<Enemy> enemies;
	private final ArrayList<MapItem> mapItems;
	private final Link link;

	private final Tile[][] tiles;

	private final MapFactory mapFactory;

	public OverWorld(int numOfColumns, int numOfRows, int cameraX, int cameraY)
	{
		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		this.cameraX = cameraX;
		this.cameraY = cameraY;

		cameraVelX = 0;
		cameraVelY = 0;

		this.numOfColumns = numOfColumns;
		this.numOfRows = numOfRows;

		widthOfTile = 16;
		heightOfTile = 16;

		mapWidth = 256;
		mapHeight = 176;

		tiles = new Tile[numOfColumns][numOfRows];
		enemies = new ArrayList<>();
		mapItems = new ArrayList<>();

		link = new Link(this);

		mapFactory = new MapFactory(this);
	}

	public void update()
	{
		cameraX += cameraVelX;
		cameraY += cameraVelY;

		if(cameraX % mapWidth == 0) cameraVelX = 0;
		if(cameraY % mapHeight == 0) cameraVelY = 0;

		link.update();

		if(link.getX() - this.getCameraX() <= widthOfTile && link.getDirection() == Direction.LEFT &&
				!link.getState().equals("TRANSITION"))
		{
			this.setVector(-4, 0);
			link.setTransitionVector(-1, 0);
		}

		if(link.getX() - this.getCameraX() >= mapWidth - widthOfTile && link.getDirection() == Direction.RIGHT &&
				!link.getState().equals("TRANSITION"))
		{
			this.setVector(4, 0);
			link.setTransitionVector(1, 0);
		}
		if(link.getY() - this.getCameraY() <= heightOfTile && link.getDirection() == Direction.UP &&
				!link.getState().equals("TRANSITION"))
		{
			this.setVector(0, -4);
			link.setTransitionVector(0, -1);
		}
		if(link.getY() - this.getCameraY() >= mapHeight - heightOfTile && link.getDirection() == Direction.DOWN &&
				!link.getState().equals("TRANSITION"))
		{
			this.setVector(0, 4);
			link.setTransitionVector(0, 1);
		}


		Iterator enemyIterator = enemies.iterator();
		while(enemyIterator.hasNext())
		{
			Enemy enemy = (Enemy) enemyIterator.next();

			if(this.checkVisibility(enemy))
			{
				if(link.getSword() != null) enemy.setSword(link.getSword());
				else enemy.setSword(null);

				enemy.update();

				if(enemy.getDestroyFlag())
				{
					enemyIterator.remove();
					mapItems.add(new AnimationObject(
							(int) Math.round(enemy.getX() - enemy.getWidth() / 2),
							(int) Math.round(enemy.getY() - enemy.getHeight() / 2),
							new Animation(3, false,
									Images.Enemies.ENEMY_DEATH, 16, 16),
							this));
				}
			}
		}

		Iterator mapItemIterator = mapItems.iterator();
		while(mapItemIterator.hasNext())
		{
			MapItem mapItem = (MapItem) mapItemIterator.next();

			if(checkVisibility(mapItem.getRectangle()))
			{
				mapItem.update();

				if(mapItem instanceof AnimationObject)
				{
					AnimationObject animationObject = (AnimationObject) mapItem;
					if(animationObject.getAnimation().getIndex() == -1)
					{
						mapItemIterator.remove();
					}
				}
			}
		}
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.translate(drawX, drawY);

		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][j]), widthOfTile * i - cameraX, heightOfTile * j - cameraY,
						widthOfTile, heightOfTile, null);
			}
		}

		for(Enemy enemy : enemies)
		{
			if(this.checkVisibility(enemy)) enemy.draw(g2d);
		}

		for(MapItem mapItem : mapItems)
		{
			if(checkVisibility(mapItem.getRectangle())) mapItem.draw(g2d);
		}

		link.draw(g2d);

		//drawDebug(g2d);

		g2d.setTransform(transform);
	}

	public void loadTiles(String filePath)
	{
		try(BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(filePath), Charset.defaultCharset()))
		)
		{
			String line;
			int lineCount = 0;
			while((line = bufferedReader.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line);
				int characterCount = 0;
				while(tokenizer.hasMoreElements())
				{
					String token = tokenizer.nextToken();
					try
					{
						tiles[characterCount][lineCount] = Tile.parseID(Integer.parseInt(token));
					}
					catch(NumberFormatException e)
					{
						mapItems.add(mapFactory.buildWarpTile(token,
								characterCount * widthOfTile,
								lineCount * heightOfTile));
						tiles[characterCount][lineCount] = Tile.parseID(0);
					}
					characterCount++;
				}
				lineCount++;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadEnemies(String filePath)
	{
		try(BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream(filePath), Charset.defaultCharset())))
		{
			String line;
			int lineCount = 0;
			while((line = bufferedReader.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line);
				int characterCount = 0;
				while(tokenizer.hasMoreElements())
				{
					Enemy enemy = mapFactory.buildEnemy(tokenizer.nextToken(),
							characterCount * widthOfTile, lineCount * heightOfTile);
					if(enemy != null) enemies.add(enemy);
					characterCount++;
				}
				lineCount++;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private boolean checkVisibility(Entity entity)
	{
		Rectangle visibleSector = new Rectangle(cameraX, cameraY, 256, 192);
		Rectangle object = new Rectangle((int) Math.round(entity.getX()),
				(int) Math.round(entity.getY()),
				entity.getWidth(), entity.getHeight());

		return visibleSector.intersects(object);
	}

	public boolean checkVisibility(Rectangle object)
	{
		Rectangle visibleSector = new Rectangle(cameraX, cameraY, 256, 192);

		return visibleSector.intersects(object);
	}

	public void updateDrawCoordinates()
	{
		drawX += drawVelX;
		drawY += drawVelY;
	}

	private void setVector(int velX, int velY)
	{
		this.cameraVelX = velX;
		this.cameraVelY = velY;
	}

	public void addCollectible(Collectible collectible)
	{
		mapItems.add(collectible);
	}

	public Tile getTile(int column, int row)
	{
		return tiles[column][row];
	}

	public int getWidthOfTile()
	{
		return widthOfTile;
	}

	public int getHeightOfTile()
	{
		return heightOfTile;
	}

	public int getNumOfColumns()
	{
		return numOfColumns;
	}
	public int getNumOfRows()
	{
		return numOfRows;
	}

	public int getCameraX()
	{
		return cameraX;
	}
	public int getCameraY()
	{
		return cameraY;
	}

	public void setCameraX(int cameraX)
	{
		this.cameraX = cameraX;
	}
	public void setCameraY(int cameraY)
	{
		this.cameraY = cameraY;
	}
	public void setDrawCoordinates(int drawX, int drawY)
	{
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int[] getDrawCoordinates()
	{
		return new int[] {drawX, drawY};
	}

	public void setDrawVector(int drawVelX, int drawVelY)
	{
		this.drawVelX = drawVelX;
		this.drawVelY = drawVelY;
	}

	public int getMapWidth()
	{
		return mapWidth;
	}

	public int getMapHeight()
	{
		return mapHeight;
	}
	public Link getLink()
	{
		return link;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}

	public ArrayList<MapItem> getMapItems()
	{
		return mapItems;
	}

	private void drawDebug(Graphics2D g2d)
	{
		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				if(!tiles[i][j].isPassible())
				{
					g2d.setColor(new Color(0, 255, 0, 100));
					Rectangle tileRectangle = new Rectangle(i * this.getWidthOfTile() - this.getCameraX(),
							j * this.getWidthOfTile() - this.getCameraY(),
							this.getWidthOfTile(),
							this.getHeightOfTile() / 2);
					g2d.fill(tileRectangle);
				}
			}
		}
	}
}

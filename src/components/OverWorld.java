package components;

import entity.*;
import entity.enemies.Enemy;
import reference.Images;
import reference.MapHelper;

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

	private int numOfColumns;
	private int numOfRows;
	private int widthOfTile;
	private int heightOfTile;

	private int mapWidth;
	private int mapHeight;

	private ArrayList<Enemy> enemies;
	private ArrayList<AnimationObject> animations;
	private Link link;

	private Tile[][] tiles;

	public OverWorld(int numOfColumns, int rows)
	{
		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		cameraX = 0;
		cameraY = 0;
		cameraVelX = 0;
		cameraVelY = 0;

		this.numOfColumns = numOfColumns;
		this.numOfRows = rows;

		widthOfTile = 16;
		heightOfTile = 16;

		mapWidth = 256;
		mapHeight = 192;

		tiles = new Tile[numOfColumns][rows];
		enemies = new ArrayList<>();
		animations = new ArrayList<>();

		link = new Link(this);
	}

	public void update()
	{
		cameraX += cameraVelX;
		cameraY += cameraVelY;

		if(cameraX % mapWidth == 0) cameraVelX = 0;
		if(cameraY % mapHeight == 0) cameraVelY = 0;

		link.update();

		if(link.getX() - this.getCameraX() <= widthOfTile && link.getDirection() == Direction.LEFT && !link.getState().equals("TRANSITION"))
		{
			this.setVector(-4, 0);
			link.setTransitionVector(-1, 0);
		}

		if(link.getX() - this.getCameraX() >= mapWidth - widthOfTile && link.getDirection() == Direction.RIGHT && !link.getState().equals("TRANSITION"))
		{
			this.setVector(4, 0);
			link.setTransitionVector(1, 0);
		}
		if(link.getY() - this.getCameraY() <= heightOfTile && link.getDirection() == Direction.UP && !link.getState().equals("TRANSITION"))
		{
			this.setVector(0, -4);
			link.setTransitionVector(0, -1);
		}
		if(link.getY() - this.getCameraY() >= mapHeight - heightOfTile && link.getDirection() == Direction.DOWN && !link.getState().equals("TRANSITION"))
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
					animations.add(new AnimationObject(enemy.getX() - enemy.getWidth() / 2,
							enemy.getY() - enemy.getHeight() / 2,
							new Animation(3, false,
									Images.Enemies.ENEMY_DEATH, 16, 16),
							this));
				}
			}
		}

		Iterator animationIterator = animations.iterator();
		while(animationIterator.hasNext())
		{
			AnimationObject animationObject = (AnimationObject) animationIterator.next();

			if(this.checkVisibility(new Rectangle(animationObject.getX(), animationObject.getY(),
					animationObject.getWidth(), animationObject.getHeight())))
			{
				animationObject.update();

				if(animationObject.getAnimation().getIndex() == -1)
				{
					animationIterator.remove();
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
			for(int k = 0; k < numOfRows; k++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][k]), widthOfTile * i - cameraX, heightOfTile * k - cameraY,
						widthOfTile, heightOfTile, null);
			}
		}

		for(Enemy enemy : enemies)
		{
			if(this.checkVisibility(enemy)) enemy.draw(g2d);
		}
		for(AnimationObject animation : animations)
		{
			if(this.checkVisibility(new Rectangle(animation.getX(), animation.getY(), animation.getWidth(), animation.getHeight())))
				animation.draw(g2d);
		}

		link.draw(g2d);

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
					tiles[characterCount][lineCount] = Tile.parseID(Integer.parseInt(tokenizer.nextToken()));
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
					Enemy enemy = MapHelper.parseEnemy(tokenizer.nextToken(), characterCount * widthOfTile,
							lineCount * heightOfTile, this);
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

	public boolean checkVisibility(MapObject mapObject)
	{
		Rectangle visibleSector = new Rectangle(cameraX, cameraY, 256, 192);
		Rectangle object = new Rectangle(mapObject.getX(), mapObject.getY(), mapObject.getWidth(), mapObject.getHeight());

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

	public void setVector(int velX, int velY)
	{
		this.cameraVelX = velX;
		this.cameraVelY = velY;
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

	public Link getLink()
	{
		return link;
	}
}
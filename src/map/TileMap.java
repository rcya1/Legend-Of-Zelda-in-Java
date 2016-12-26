package map;

import entity.*;
import entity.enemies.Enemy;
import reference.Images;
import reference.MapHelper;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class TileMap
{
	private int x;
	private int y;

	private int velX;
	private int velY;

	private int columns;
	private int rows;

	private int widthOfTile;
	private int heightOfTile;

	private int mapWidth;
	private int mapHeight;

	private ArrayList<Enemy> enemies;
	private ArrayList<AnimationObject> animations;

	private Link link;

	private Tile[][] tiles;

	public TileMap(int columns, int rows)
	{
		x = 0;
		y = 0;

		velX = 0;
		velY = 0;

		this.columns = columns;
		this.rows = rows;

		widthOfTile = 16;
		heightOfTile = 16;

		mapWidth = 256;
		mapHeight = 192;

		tiles = new Tile[columns][rows];
		enemies = new ArrayList<>();
		animations = new ArrayList<>();

		link = new Link(this);
	}

	public void update()
	{
		x += velX;
		y += velY;

		if(x % mapWidth == 0) velX = 0;
		if(y % mapHeight == 0) velY = 0;

		link.update();

		if(link.getX() - this.getX() <= widthOfTile && link.getDirection() == Direction.LEFT && !link.getState().equals("TRANSITION"))
		{
			this.setVector(-4, 0);
			link.setTransitionVector(-1, 0);
		}

		if(link.getX() - this.getX() >= mapWidth - widthOfTile && link.getDirection() == Direction.RIGHT && !link.getState().equals("TRANSITION"))
		{
			this.setVector(4, 0);
			link.setTransitionVector(1, 0);
		}
		if(link.getY() - this.getY() <= heightOfTile && link.getDirection() == Direction.UP && !link.getState().equals("TRANSITION"))
		{
			this.setVector(0, -4);
			link.setTransitionVector(0, -1);
		}
		if(link.getY() - this.getY() >= mapHeight - heightOfTile && link.getDirection() == Direction.DOWN && !link.getState().equals("TRANSITION"))
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
					animations.add(new AnimationObject(enemy.getX() - enemy.getWidth() / 2, enemy.getY() - enemy.getHeight() / 2, new Animation(3, false, Images.Enemies.ENEMY_DEATH, 16, 16)));
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
		for(int i = 0; i < columns; i++)
		{
			for(int k = 0; k < rows; k++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][k]), widthOfTile * i - x, heightOfTile * k - y,
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
	}

	public void loadTiles(String filePath)
	{
		try
		{
			InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
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
		try
		{
			InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
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

	private boolean checkVisibility(MapObject mapObject)
	{
		Rectangle visibleSector = new Rectangle(x, y, 256, 192);
		Rectangle object = new Rectangle(mapObject.getX(), mapObject.getY(), mapObject.getWidth(), mapObject.getHeight());

		return visibleSector.intersects(object);
	}

	private boolean checkVisibility(Rectangle object)
	{
		Rectangle visibleSector = new Rectangle(x, y, 256, 192);

		return visibleSector.intersects(object);
	}

	public void setVector(int velX, int velY)
	{
		this.velX = velX;
		this.velY = velY;
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

	public int getColumns()
	{
		return columns;
	}
	public int getRows()
	{
		return rows;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	public ArrayList<AnimationObject> getAnimations()
	{
		return animations;
	}

	public Link getLink()
	{
		return link;
	}
}

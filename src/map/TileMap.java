package map;

import entity.AnimationObject;
import entity.enemies.Enemy;
import reference.MapHelper;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

	private ArrayList<Enemy> enemies;
	private ArrayList<AnimationObject> animations;

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

		tiles = new Tile[columns][rows];
		enemies = new ArrayList<>();
		animations = new ArrayList<>();
	}

	public void update()
	{
		x += velX;
		y += velY;

		if(x % 256 == 0) velX = 0;
		if(y % 192 == 0) velY = 0;
	}

	public void draw(Graphics2D g2d)
	{
		for(int i = 0; i < columns; i++)
		{
			for(int k = 0; k < rows; k++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][k]), widthOfTile * i + x, heightOfTile * k + y,
						widthOfTile, heightOfTile, null);
			}
		}

		for(Enemy enemy : enemies) enemy.draw(g2d);
		for(AnimationObject animation : animations) animation.draw(g2d);
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

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}
	public ArrayList<AnimationObject> getAnimations()
	{
		return animations;
	}
}

package map;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

public class TileMap
{
	private int columns;
	private int rows;

	private int widthOfTile;
	private int heightOfTile;

	private Tile[][] tiles;

	public TileMap(int columns, int rows)
	{
		this.columns = columns;
		this.rows = rows;

		this.widthOfTile = 16;
		this.heightOfTile = 16;

		tiles = new Tile[rows][columns];
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

	public void draw(Graphics2D g2d)
	{
		for(int i = 0; i < columns; i++)
		{
			for(int k = 0; k < rows; k++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][k]), widthOfTile * i, heightOfTile * k, widthOfTile, heightOfTile, null);
			}
		}
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
}

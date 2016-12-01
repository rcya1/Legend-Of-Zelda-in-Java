package map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TileMap
{
	private int columns;
	private int rows;

	private int widthOfTile;
	private int heightOfTile;

	private ArrayList<BufferedImage> sprites;

	private Tile[][] tiles;

	public TileMap(int columns, int rows)
	{
		this.columns = columns;
		this.rows = rows;

		this.widthOfTile = 16;
		this.heightOfTile = 16;

		sprites = new ArrayList<>();
		tiles = new Tile[rows][columns];
	}

	/*public void loadSprites(String filePath)
	{
		BufferedImage spritesImage = null;

		try
		{
			spritesImage = ImageIO.read(getClass().getResourceAsStream(filePath));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		if(sprites != null)
		{
			int spriteColumns = spritesImage.getWidth() / widthOfTile;
			int spriteRows = spritesImage.getHeight() / heightOfTile;

			for(int i = 0; i < spriteColumns; i++)
			{
				for(int j = 0; j < spriteRows; j++)
				{
					sprites.add(spritesImage.getSubimage(widthOfTile * i, heightOfTile * j,
							widthOfTile, heightOfTile));
				}
			}
		}
	}*/

	public void loadTiles(String filePath)
	{
		InputStream inputStream = getClass().getResourceAsStream(filePath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

		try
		{
			String line;
			int lineCount = 0;
			while((line = bufferedReader.readLine()) != null)
			{
				StringTokenizer tokenizer = new StringTokenizer(line);
				int characterCount = 0;
				while(tokenizer.hasMoreElements())
				{
					tiles[lineCount][characterCount] = Tile.parseID(Integer.parseInt(tokenizer.nextToken()));
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
}

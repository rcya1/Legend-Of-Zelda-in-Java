package utility;

import components.OverWorld;
import components.Tile;
import components.entity.Direction;
import components.entity.enemies.Enemy;
import components.entity.enemies.Octorok;
import components.map.WarpTile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

public class MapFactory
{
	private final OverWorld overWorld;
	private final String[][] overWorldTiles;

	public MapFactory(OverWorld overWorld, String tileMapFilePath, int columns, int rows)
	{
		this.overWorld = overWorld;
		overWorldTiles = new String[columns][rows];
		loadTiles(tileMapFilePath);
	}

	private void loadTiles(String filePath)
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
					overWorldTiles[characterCount][lineCount] = token;
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

	public String getTile(int column, int row)
	{
		return overWorldTiles[column][row];
	}

	public Enemy buildEnemy(String string, int x, int y)
	{
		switch(string)
		{
		case "A":
			return new Octorok(x + overWorld.getWidthOfTile() / 2, y + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(), overWorld.getCurrentRoom());
		default:
			return null;
		}
	}

	public WarpTile buildWarpTile(String id, int x, int y)
	{
		return new WarpTile(x, y, overWorld.getWidthOfTile(), overWorld.getHeightOfTile(),
				id.charAt(0));
	}
}

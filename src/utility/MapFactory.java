package utility;

import components.entity.enemies.*;
import components.map.OverWorld;
import components.entity.Direction;
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

	public Enemy buildEnemy(String string, int col, int row)
	{
		switch(string)
		{
		case "OCTOROK":
			return new Octorok(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(),
					(overWorld.getLoadingRoom() != null) ?
					overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "TEKTITE":
			return new Tektite(col * overWorld.getWidthOfTile()
				+ overWorld.getWidthOfTile() / 2,
				row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
				(overWorld.getLoadingRoom() != null) ?
						overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "OCTOROK-BLUE":
			return new OctorokBlue(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(),
					(overWorld.getLoadingRoom() != null) ?
							overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "LEEVER":
			return new Leever(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(),
					(overWorld.getLoadingRoom() != null) ?
							overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "LEEVER-BLUE":
			return new LeeverBlue(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(),
					(overWorld.getLoadingRoom() != null) ?
							overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "PEAHAT":
			return new Peahat(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					(int) (Math.random() * 8),
					(overWorld.getLoadingRoom() != null) ?
							overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		case "ZOLA":
			return new Zola(col * overWorld.getWidthOfTile()
					+ overWorld.getWidthOfTile() / 2,
					row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2,
					(overWorld.getLoadingRoom() != null) ?
							overWorld.getLoadingRoom() : overWorld.getCurrentRoom());
		default:
			return null;
		}
	}

	public WarpTile buildWarpTile(int column, int row, int destColumn, int destRow, String type, String direction)
	{
		Direction dir = Direction.parseString(direction);
		return new WarpTile((overWorld.getLoadingRoom() != null) ?
				overWorld.getLoadingRoom() : overWorld.getCurrentRoom(),
				column, row, destColumn, destRow, type, dir);
	}
}

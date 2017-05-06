package utility;

import components.entity.enemies.*;
import components.map.OverWorld;
import components.entity.Direction;
import components.map.WarpTile;
import components.map.rooms.Room;

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
		int x = col * overWorld.getWidthOfTile() + overWorld.getWidthOfTile() / 2;
		int y = row * overWorld.getHeightOfTile() + overWorld.getHeightOfTile() / 2;
		Room room = (overWorld.getLoadingRoom() != null) ?
				overWorld.getLoadingRoom() : overWorld.getCurrentRoom();

		switch(string)
		{
		case "OCTOROK":
			return new Octorok(x, y, Direction.getRandom(), room);
		case "TEKTITE":
			return new Tektite(x, y, room);
		case "TEKTITE-BLUE":
			return new TektiteBlue(x, y, room);
		case "OCTOROK-BLUE":
			return new OctorokBlue(x, y, Direction.getRandom(), room);
		case "LEEVER":
			return new Leever(x, y, Direction.getRandom(), room);
		case "LEEVER-BLUE":
			return new LeeverBlue(x, y, Direction.getRandom(), room);
		case "PEAHAT":
			return new Peahat(x, y, (int) (Math.random() * 8), room);
		case "ZOLA":
			return new Zola(x, y, room);
		case "MOLBLIN":
			return new Molblin(x, y, Direction.getRandom(), room);
		case "MOLBLIN-BLUE":
			return new MolblinBlue(x, y, Direction.getRandom(), room);
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

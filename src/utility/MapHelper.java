package utility;

import components.entity.Direction;
import components.entity.enemies.*;
import components.items.collectibles.Collectible;
import components.items.collectibles.Sword;
import components.map.WarpTile;
import components.map.World;
import components.map.rooms.Room;
import components.map.rooms.WorldRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

//Used by a map to handle the tiles, enemies, and warp tiles
//The map helper is created by a world, and the rooms use it to handle things
public class MapHelper
{
	private final World world;               //The world that the maphelper is used for
	private final String[][] worldTiles;     //Array that stores the tiles for the world

	public MapHelper(World world, String tileMapFilePath, int columns, int rows)
	{
		this.world = world;
		worldTiles = new String[columns][rows];
		loadTiles(tileMapFilePath);
	}

	//Loads all of the tiles from a file
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
					worldTiles[characterCount][lineCount] = token;
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

	//Returns the tile for given coordinates
	public String getTile(int column, int row)
	{
		return worldTiles[column][row];
	}

	//Returns an enemy that is constructed from id and coordinates
	public Enemy buildEnemy(String id, int col, int row)
	{
		//Calculates the coordinates for the given coordinates
		int x = col * world.getWidthOfTile() + world.getWidthOfTile() / 2;
		int y = row * world.getHeightOfTile() + world.getHeightOfTile() / 2;

		//If the overworld is loading a new room, then put the enemy in the new room, otherwise, take the current room
		Room room = (world.getLoadingRoom() != null) ?
				world.getLoadingRoom() : world.getCurrentRoom();

		//Create all of the various enemies from the required parameters
		switch(id)
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
			case "ARMOS-BROWN":
				return new ArmosBrown(x, y, room);
			case "ARMOS-GREEN":
				return new ArmosGreen(x, y, room);
			case "ARMOS-WHITE":
				return new ArmosWhite(x, y, room);
			case "LYNEL":
				return new Lynel(x, y, Direction.getRandom(), room);
			case "LYNEL-BLUE":
				return new LynelBlue(x, y, Direction.getRandom(), room);
            case "GHINI":
                return new Ghini(x, y, ((int) (Math.random() * 4)) * 2, room, true);
            case "GHINI-SPAWNER":
                return new GhiniSpawner(x, y, room);
		default:
			return null;
		}
	}

	//Returns a warp tile from parameters
	public WarpTile buildWarpTile(int column, int row, int destColumn, int destRow, String type, String direction)
	{
		//Parse the direction from a string
		Direction dir = Direction.parseString(direction);

		//If the overworld is loading a new room, then put the tile in the new room, otherwise, take the current room
		WorldRoom room = (world.getLoadingRoom() != null) ? world.getLoadingRoom() : world.getCurrentRoom();

		return new WarpTile(room, column, row, destColumn, destRow, type, dir);
	}

	public static Collectible parse(String collectible, int x, int y, Room room)
	{
		switch(collectible)
		{
			case "SWORD":
				return new Sword(x, y, room);
			default:
				return null;
		}
	}
}

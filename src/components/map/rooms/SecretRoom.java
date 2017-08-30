package components.map.rooms;

import components.MapItem;
import components.entity.Direction;
import components.entity.Link;
import components.entity.enemies.Enemy;
import components.items.collectibles.Collectible;
import components.map.AnimationObject;
import components.map.World;
import utility.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

//A cave or hidden room that is stored with another room in the world
public class SecretRoom implements Room
{
	private final int id;                          //The col/row of the room that this room is linked with

	private final int numOfColumns, numOfRows;     //The number of cols/rows in this room
	private final int widthOfTile, heightOfTile;   //The dimensions of a single tile

	private final Link link;                       //The current player
	private final Tile[][] tiles;                  //An array storing every tile
	private final World world;                     //The main world

	private final int warpColumn, warpRow;         //The coordinates where to put the player when they leave

	private final RoomMetadata metadata;           //The information about the room
	private final AnimationObject fire1, fire2;    //Animations
	private Collectible[] items;                   //All of the purchasable items/collectibles

	//Constructs a secret room based on params
	public SecretRoom(int id, World world, RoomMetadata metadata, int warpColumn, int warpRow)
	{
		this.id = id;

		this.numOfColumns = 16;
		this.numOfRows = 11;

		widthOfTile = world.getWidthOfTile();
		heightOfTile = world.getHeightOfTile();

		tiles = new Tile[numOfColumns][numOfRows];
		link = world.getLink();

		//Sets the tiles
		loadTiles("/tileMaps/SecretRoom.txt");

		this.world = world;

		this.metadata = metadata;

		this.warpColumn = warpColumn;
		this.warpRow = warpRow;

		//Creates animations
		fire1 = new AnimationObject(72, 64, new Animation(4, true,
				Images.Blocks.Secret.FIRE_1, Images.Blocks.Secret.FIRE_2), this);
		fire2 = new AnimationObject(168, 64, new Animation(4, true,
				Images.Blocks.Secret.FIRE_1, Images.Blocks.Secret.FIRE_2), this);

		//Goes through the metadata and grabs each item and places them in the room
		items = new Collectible[3];
		for(int itemIndex = 0; itemIndex < metadata.getItems().size(); itemIndex++)
		{
			Rectangle rectangle = getItemLocation(itemIndex);
			int x = (int) rectangle.getX();
			int y = (int) rectangle.getY();

			items[itemIndex] = MapHelper.parse(metadata.getItems().get(itemIndex)[0],
					x, y, this);
		}
	}

	//Loads the tiles from a file
	private void loadTiles(String filePath)
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
					String token = tokenizer.nextToken();
					tiles[characterCount][lineCount] = Tile.parseID(Integer.parseInt(token));
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

	//Updates the room
	public void update()
	{
		//Check whether link has left the room
		if(link.getY() >= getMapHeight() - heightOfTile &&
				link.getDirection() == Direction.DOWN)
		{
			link.setCoordinates((int) ((warpColumn + 0.5) * widthOfTile),
					(int) ((warpRow + 1.25) * heightOfTile));
			link.setRoom(new WorldRoom(id, world, world.getMapHelper()));
		}

		//Update everything in the room
		fire1.update();
		fire2.update();

		link.update();

		//Cheeck if link has collided with any items
		for(int itemIndex = 0; itemIndex < metadata.getItems().size(); itemIndex++)
		{
			if(items[itemIndex] != null)
			{
				Rectangle rectangle = getItemLocation(itemIndex);

				if(rectangle.intersects(link.getRectangle()))
				{
					if(items[itemIndex].action(link)) items[itemIndex] = null;
				}
			}

			if(items[itemIndex] != null) items[itemIndex].update();
		}
	}

	//Draw the room
	public void draw(Graphics2D g2d)
	{
		//Draw the tiles
		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][j]), widthOfTile * i,
						heightOfTile * j, widthOfTile, heightOfTile, null);
			}
		}

		//Draw animations
		fire1.draw(g2d);
		fire2.draw(g2d);

		//Draw the npc at the top
		BufferedImage npcSprite = null;
		switch(metadata.getCaveNPC())
		{
		case "OLD-MAN":
			npcSprite = Images.Blocks.Secret.OLD_MAN_1;
			break;
		}
		g2d.drawImage(npcSprite, 120, 64, null);

		//Draw all items
		for(Collectible item : items)
		{
			if(item != null) item.draw(g2d);
		}

		//Draw the text at the top of the screen
		g2d.setColor(Color.WHITE);
		TextHelper.drawCenteredString(metadata.getCaveText(), 38, g2d);

		//Draw link
		link.draw(g2d);
	}

	//Returns where an item should be placed depending on its index
	private Rectangle getItemLocation(int itemIndex)
	{
		Rectangle rectangle;

		switch(itemIndex)
		{
			case 0:
				rectangle = new Rectangle(128, 96, 16, 16);
				break;
			case 1:
				rectangle = new Rectangle(100, 96, 16, 16);
				break;
			case 2:
				rectangle = new Rectangle(176, 96, 16, 16);
				break;
			default:
				rectangle = new Rectangle(0, 0, 0, 0);
				break;
		}

		return rectangle;
	}

	public Tile getTile(int column, int row)
	{
		return tiles[column][row];
	}

	public ArrayList<Enemy> getEnemies()
	{
		return new ArrayList<>();
	}

	public ArrayList<MapItem> getMapItems()
	{
		return new ArrayList<>();
	}

	public int getWidthOfTile()
	{
		return widthOfTile;
	}

	public int getHeightOfTile()
	{
		return heightOfTile;
	}
	public int getId()
	{
		return id;
	}

	public int getMapWidth()
	{
		return getWidthOfTile() * getNumOfColumns();
	}
	public int getMapHeight()
	{
		return getHeightOfTile() * getNumOfRows();
	}

	public int getNumOfColumns()
	{
		return numOfColumns;
	}
	public int getNumOfRows()
	{
		return numOfRows;
	}

	public RoomMetadata getMetadata()
	{
		return metadata;
	}

	public Link getLink()
	{
		return link;
	}

	public void addCollectible(Collectible collectible)
	{

	}
}

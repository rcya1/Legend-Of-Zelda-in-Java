package components.map.rooms;

import utility.Animation;
import utility.Tile;
import components.entity.Direction;
import components.entity.Link;
import components.map.AnimationObject;
import components.map.OverWorld;
import components.items.collectibles.Collectible;
import utility.Images;
import utility.TextHelper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

public class SecretRoom implements RoomBase
{
	private final int id;

	private final int numOfColumns;
	private final int numOfRows;
	private final int widthOfTile;
	private final int heightOfTile;

	private final Link link;

	private final Tile[][] tiles;

	private OverWorld overWorld;

	private int warpColumn;
	private int warpRow;

	private RoomMetadata metadata;

	private AnimationObject fire1;
	private AnimationObject fire2;

	private Collectible[] items;

	public SecretRoom(int id, OverWorld overWorld, RoomMetadata metadata, int warpColumn, int warpRow)
	{
		this.id = id;

		this.numOfColumns = 16;
		this.numOfRows = 11;

		widthOfTile = overWorld.getWidthOfTile();
		heightOfTile = overWorld.getHeightOfTile();

		tiles = new Tile[numOfColumns][numOfRows];
		link = overWorld.getLink();

		loadTiles("/tileMaps/SecretRoom.txt");

		this.overWorld = overWorld;

		this.metadata = metadata;

		this.warpColumn = warpColumn;
		this.warpRow = warpRow;

		fire1 = new AnimationObject(72, 64, new Animation(4, true,
				Images.Blocks.Secret.FIRE_1, Images.Blocks.Secret.FIRE_2), this);
		fire2 = new AnimationObject(168, 64, new Animation(4, true,
				Images.Blocks.Secret.FIRE_1, Images.Blocks.Secret.FIRE_2), this);

		items = new Collectible[3];

		for(int itemIndex = 0; itemIndex < metadata.getItems().size(); itemIndex++)
		{
			Rectangle rectangle = getItemLocation(itemIndex);
			int x = (int) rectangle.getX();
			int y = (int) rectangle.getY();

			items[itemIndex] = Collectible.parse(metadata.getItems().get(itemIndex)[0],
					x, y, this);
		}
	}

	public void update()
	{
		if(link.getY() >= getMapHeight() - heightOfTile &&
				link.getDirection() == Direction.DOWN)
		{
			link.setCoordinates((int) ((warpColumn + 0.5) * widthOfTile),
					(int) ((warpRow + 1.25) * heightOfTile));
			link.setRoom(new OverWorldRoom(id, overWorld, overWorld.getMapFactory()));
		}

		fire1.update();
		fire2.update();

		link.update();

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

	public void draw(Graphics2D g2d)
	{
		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][j]), widthOfTile * i,
						heightOfTile * j, widthOfTile, heightOfTile, null);
			}
		}

		fire1.draw(g2d);
		fire2.draw(g2d);

		BufferedImage npcSprite = null;
		switch(metadata.getCaveNPC())
		{
		case "OLD-MAN":
			npcSprite = Images.Blocks.Secret.OLD_MAN_1;
			break;
		}

		g2d.drawImage(npcSprite, 120, 64, null);

		for(Collectible item : items)
		{
			if(item != null) item.draw(g2d);
		}

		g2d.setColor(Color.WHITE);
		TextHelper.drawCenteredString(metadata.getCaveText(), 38, g2d);

		link.draw(g2d);
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

	public Tile getTile(int column, int row)
	{
		return tiles[column][row];
	}

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

	public int getWidthOfTile()
	{
		return widthOfTile;
	}

	public int getHeightOfTile()
	{
		return heightOfTile;
	}
	@Override public int getId()
	{
		return id;
	}

	@Override public int getMapWidth()
	{
		return getWidthOfTile() * getNumOfColumns();
	}
	@Override public int getMapHeight()
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

	public void addCollectible(Collectible collectible)
	{

	}
}

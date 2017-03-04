package components;

import components.entity.Direction;
import components.entity.Link;
import components.entity.enemies.Enemy;
import components.map.AnimationObject;
import components.map.MapItem;
import components.map.collectibles.Collectible;
import utility.Images;
import utility.MapFactory;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
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
	}

	public void draw(Graphics2D g2d)
	{
		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][j]), widthOfTile * i, heightOfTile * j, widthOfTile, heightOfTile, null);
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

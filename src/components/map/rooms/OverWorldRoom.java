package components.map.rooms;

import utility.Animation;
import utility.Tile;
import components.entity.Link;
import components.entity.enemies.Enemy;
import components.map.AnimationObject;
import components.items.MapItem;
import components.map.OverWorld;
import components.items.collectibles.Collectible;
import utility.Images;
import utility.MapFactory;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

public class OverWorldRoom implements Room
{
	private final int id;

	private int drawX;
	private int drawY;
	private int drawVelX;
	private int drawVelY;

	private final int numOfColumns;
	private final int numOfRows;
	private final int widthOfTile;
	private final int heightOfTile;

	private final int mapWidth;
	private final int mapHeight;

	private final ArrayList<Enemy> enemies;
	private final ArrayList<MapItem> mapItems;
	private final Link link;

	private final Tile[][] tiles;

	private final MapFactory mapFactory;

	private RoomMetadata roomMetadata;

	public OverWorldRoom(int id, OverWorld overWorld, MapFactory mapFactory)
	{
		this.id = id;

		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		this.numOfColumns = 16;
		this.numOfRows = 11;

		widthOfTile = overWorld.getWidthOfTile();
		heightOfTile = overWorld.getHeightOfTile();

		mapWidth = numOfColumns * widthOfTile;
		mapHeight = numOfRows * heightOfTile;

		tiles = new Tile[numOfColumns][numOfRows];
		enemies = new ArrayList<>();
		mapItems = new ArrayList<>();
		link = overWorld.getLink();

		this.mapFactory = mapFactory;

		loadTiles();
	}

	public void update()
	{
		updateDrawCoordinates();

		Iterator enemyIterator = enemies.iterator();
		while(enemyIterator.hasNext())
		{
			Enemy enemy = (Enemy) enemyIterator.next();

			if(link.getSword() != null) enemy.setSword(link.getSword());
			else enemy.setSword(null);
			if(link.getArrow() != null) enemy.setArrow(link.getArrow());
			else enemy.setArrow(null);
			if(link.getBoomerang() != null) enemy.setBoomerang(link.getBoomerang());
			else enemy.setBoomerang(null);

			if(enemy.getStunTimer() == 0) enemy.update();
			else enemy.updateHealth();

			if(enemy.getDestroyFlag())
			{
				enemyIterator.remove();
				mapItems.add(new AnimationObject(
						(int) Math.round(enemy.getX() - enemy.getWidth() / 2),
						(int) Math.round(enemy.getY() - enemy.getHeight() / 2),
						new Animation(3, false,
								Images.Enemies.ENEMY_DEATH, 16, 16),
						this));
			}
		}

		Iterator mapItemIterator = mapItems.iterator();
		while(mapItemIterator.hasNext())
		{
			MapItem mapItem = (MapItem) mapItemIterator.next();

			mapItem.update();

			if(mapItem instanceof AnimationObject)
			{
				AnimationObject animationObject = (AnimationObject) mapItem;
				if(animationObject.getAnimation().isOver())
				{
					mapItemIterator.remove();
				}
			}
		}
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.translate(drawX, drawY);

		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				g2d.drawImage(Tile.getSprite(tiles[i][j]), widthOfTile * i, heightOfTile * j, widthOfTile, heightOfTile, null);
			}
		}

		for(Enemy enemy : enemies)
		{
			enemy.draw(g2d);
		}

		for(MapItem mapItem : mapItems)
		{
			mapItem.draw(g2d);
		}

		g2d.setTransform(transform);
	}

	private void loadTiles()
	{
		int screenColumn = (int) Math.floor((double) id / 10);
		int screenRow = id % 10;

		for(int column = (screenColumn - 1) * numOfColumns; column < screenColumn * numOfColumns; column++)
		{
			for(int row = (screenRow - 1) * numOfRows; row < screenRow * numOfRows; row++)
			{
				String tile = mapFactory.getTile(column, row);

				tiles[column - ((screenColumn - 1) * numOfColumns)]
						[row - (screenRow - 1) * numOfRows] =
						Tile.parseID(Integer.parseInt(tile));
			}
		}
	}

	public void setRoomMetadata(RoomMetadata roomMetadata)
	{
		this.roomMetadata = roomMetadata;
		this.enemies.addAll(roomMetadata.getEnemies());
		this.mapItems.addAll(roomMetadata.getWarpTiles());
	}

	public RoomMetadata getRoomMetadata()
	{
		return roomMetadata;
	}

	public void updateDrawCoordinates()
	{
		drawX += drawVelX;
		drawY += drawVelY;
	}

	public void addCollectible(Collectible collectible)
	{
		mapItems.add(collectible);
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

	public int getNumOfColumns()
	{
		return numOfColumns;
	}
	public int getNumOfRows()
	{
		return numOfRows;
	}

	public void setDrawCoordinates(int drawX, int drawY)
	{
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int[] getDrawCoordinates()
	{
		return new int[] {drawX, drawY};
	}

	public void setDrawVector(int drawVelX, int drawVelY)
	{
		this.drawVelX = drawVelX;
		this.drawVelY = drawVelY;
	}

	public int[] getDrawVector()
	{
		return new int[] {drawVelX, drawVelY};
	}

	public int getMapWidth()
	{
		return mapWidth;
	}

	public int getMapHeight()
	{
		return mapHeight;
	}

	public int getId()
	{
		return id;
	}

	public RoomMetadata getMetadata()
	{
		return roomMetadata;
	}

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}

	public ArrayList<MapItem> getMapItems()
	{
		return mapItems;
	}

	public Link getLink()
	{
		return link;
	}

	private void drawDebug(Graphics2D g2d)
	{
		for(int i = 0; i < numOfColumns; i++)
		{
			for(int j = 0; j < numOfRows; j++)
			{
				if(!tiles[i][j].isPassible())
				{
					g2d.setColor(new Color(0, 255, 0, 100));
					Rectangle tileRectangle = new Rectangle(i * this.getWidthOfTile(),
							j * this.getWidthOfTile(),
							this.getWidthOfTile(),
							this.getHeightOfTile() / 2);
					g2d.fill(tileRectangle);
				}
			}
		}
	}
}

package components.map.rooms;

import components.MapItem;
import components.entity.Link;
import components.entity.enemies.Cloud;
import components.entity.enemies.Enemy;
import components.entity.enemies.overworld.Ghini;
import components.items.collectibles.Collectible;
import components.map.AnimationObject;
import components.map.World;
import utility.Animation;
import utility.Images;
import utility.MapHelper;
import utility.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;

//The rooms that make up a world
public class WorldRoom implements Room
{
	private final int id;                        //Col/row of the room in the world

	private int drawX, drawY;                    //Where the room should be drawn
	private int drawVelX, drawVelY;              //Velocity for the drawX/drawY coords

	private final int numOfColumns, numOfRows;   //Dimensions of the room in cols/rows
	private final int widthOfTile, heightOfTile; //Dimensions of tiles
	private final int mapWidth, mapHeight;       //Dimensions of the room in pixels

	private final ArrayList<Enemy> enemies;
	private final ArrayList<Enemy> addEnemies;
	private final ArrayList<MapItem> mapItems;
	private final Link link;

	private final Tile[][] tiles;

	private final MapHelper mapHelper;
	private RoomMetadata roomMetadata;

	public WorldRoom(int id, World world, MapHelper mapHelper)
	{
		this.id = id;

		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		this.numOfColumns = 16;
		this.numOfRows = 11;

		widthOfTile = world.getWidthOfTile();
		heightOfTile = world.getHeightOfTile();

		mapWidth = numOfColumns * widthOfTile;
		mapHeight = numOfRows * heightOfTile;

		tiles = new Tile[numOfColumns][numOfRows];
		enemies = new ArrayList<>();
		addEnemies = new ArrayList<>();
		mapItems = new ArrayList<>();
		link = world.getLink();

		this.mapHelper = mapHelper;

		loadTiles();
	}

	//Goes through the world's tiles and gets the section that belongs to this room
	private void loadTiles()
	{
		int screenColumn = (int) Math.floor((double) id / 10);
		int screenRow = id % 10;

		for(int column = (screenColumn - 1) * numOfColumns; column < screenColumn * numOfColumns; column++)
		{
			for(int row = (screenRow - 1) * numOfRows; row < screenRow * numOfRows; row++)
			{
				String tile = mapHelper.getTile(column, row);

				tiles[column - ((screenColumn - 1) * numOfColumns)]
						[row - (screenRow - 1) * numOfRows] =
						Tile.parseID(Integer.parseInt(tile));
			}
		}
	}

	//Updates all of the objects in the room
	public void update()
	{
		updateDrawCoordinates();

		boolean destroyAllGhini = false;
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
				if(enemy instanceof Ghini)
				{
					destroyAllGhini = true;
				}
				enemyIterator.remove();
				mapItems.add(new AnimationObject(
						(int) Math.round(enemy.getX() - enemy.getWidth() / 2),
						(int) Math.round(enemy.getY() - enemy.getHeight() / 2),
						new Animation(3, false,
								Images.Enemies.ENEMY_DEATH, 16, 16),
						this));
			}
		}

		enemies.addAll(addEnemies);
		addEnemies.clear();

		if(destroyAllGhini)
		{
			for(Enemy enemy : enemies)
			{
				if(enemy instanceof Ghini) enemy.setDestroyFlag(true);
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
					if(animationObject instanceof Cloud)
					{
						Cloud cloud = (Cloud) animationObject;
						enemies.add(cloud.getEnemy());
					}
				}
			}
		}
	}

	//Draws the room
	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.translate(drawX, drawY);

		for(int col = 0; col < numOfColumns; col++)
		{
			for(int row = 0; row < numOfRows; row++)
			{
				g2d.drawImage(Tile.getSprite(tiles[col][row]), widthOfTile * col, heightOfTile * row,
						widthOfTile, heightOfTile, null);
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

	//Sets the metadata and adds all of the items/enemies from that metadata
	public void setRoomMetadata(RoomMetadata roomMetadata)
	{
		this.roomMetadata = roomMetadata;
		for(Enemy enemy : roomMetadata.getEnemies())
		{
			this.mapItems.add(new Cloud((int) enemy.getX(), (int) enemy.getY(),this, enemy));
		}
		this.mapItems.addAll(roomMetadata.getWarpTiles());
	}

	//Returns the metadata for the room
	public RoomMetadata getRoomMetadata()
	{
		return roomMetadata;
	}

	//Updates the draw position of the room
	public void updateDrawCoordinates()
	{
		drawX += drawVelX;
		drawY += drawVelY;
	}

	//Sets where to draw the room
	public void setDrawCoordinates(int drawX, int drawY)
	{
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int[] getDrawCoordinates()
	{
		return new int[] {drawX, drawY};
	}

	//Sets the velocity of the room
	public void setDrawVector(int drawVelX, int drawVelY)
	{
		this.drawVelX = drawVelX;
		this.drawVelY = drawVelY;
	}

	//Returns the velocity of the room
	public int[] getDrawVelocity()
	{
		return new int[] {drawVelX, drawVelY};
	}

	//Adds a collectible to the room
	public void addCollectible(Collectible collectible)
	{
		mapItems.add(collectible);
	}

	public void addEnemy(Enemy enemy)
	{
		addEnemies.add(enemy);
	}
	//Returns the tile in this room at a given coordinate
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

	//Draws a debug menu that shows collision boxes
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

package components.map;

import components.MapItem;
import components.entity.Direction;
import components.map.rooms.Room;

import java.awt.*;

//A Tile that is used to transport the player to another location
public class WarpTile extends MapItem
{
	private final int destColumn, destRow;      //The result coords for the player
	private final String type;                  //Type of the tile (CAVE, DUNGEON)
	private final Direction direction;          //The direction that the player must approach the tile

	//Creates a warp tile in the given room from the given parameters
	public WarpTile(Room room, int column, int row, int destColumn, int destRow, String type, Direction direction)
	{
		this.x = column * room.getWidthOfTile();
		this.y = row * room.getHeightOfTile();

		this.width = room.getWidthOfTile();
		this.height = room.getHeightOfTile() / 2;

		this.direction = direction;

		//If there is a given direction, then move the tile so that it can only be hit from one way
		if(direction != null)
		{
			switch(direction)
			{
			case UP:
				this.height++;
				break;
			case RIGHT:
				this.width++;
				break;
			case DOWN:
				this.y--;
				this.height++;
				break;
			case LEFT:
				this.x--;
				this.width++;
				break;
			}
		}
		//With no given direction, then move the tile so that it can be hit from all directions
		else
		{
			this.x--;
			this.y--;
			this.width += 2;
			this.height += 2;
		}

		this.destColumn = destColumn;
		this.destRow = destRow;
		this.type = type;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{

	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getColumn(int tileWidth)
	{
		return x / tileWidth;
	}

	public int getRow(int tileHeight)
	{
		return y / tileHeight;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getDestColumn()
	{
		return destColumn;
	}

	public int getDestRow()
	{
		return destRow;
	}

	public String getType()
	{
		return type;
	}

	public Direction getDirection()
	{
		return direction;
	}
}
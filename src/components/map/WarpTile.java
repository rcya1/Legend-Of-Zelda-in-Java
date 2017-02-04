package components.map;

import components.Room;
import components.entity.Direction;

import java.awt.*;

public class WarpTile extends MapItem
{
	private final int destColumn;
	private final int destRow;

	private final String type;

	private final Direction direction;

	public WarpTile(Room room, int column, int row,
			int destColumn, int destRow, String type, Direction direction)
	{
		this.x = column * room.getWidthOfTile();
		this.y = row * room.getHeightOfTile();

		this.width = room.getWidthOfTile();
		this.height = room.getHeightOfTile() / 2;

		this.direction = direction;

		if(direction != null)
		{
			switch(direction)
			{
			case DOWN:
				this.height++;
				break;
			case LEFT:
				this.width++;
				break;
			case UP:
				this.y--;
				this.height++;
				break;
			case RIGHT:
				this.x--;
				this.width++;
				break;
			}
		}
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
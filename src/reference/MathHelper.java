package reference;

import entity.Direction;
import map.Tile;
import map.TileMap;

public class MathHelper
{
	public static int sign(int number)
	{
		if(number > 0) return 1;
		else if(number < 0) return -1;
		else return 0;
	}

	public static int[] getSwordOffset(int x, int y, int offset, Direction direction)
	{
		switch(direction)
		{
		case UP:
			return new int[] {x, y - 12};
		case RIGHT:
			return new int[] {x + 12, y};
		case DOWN:
			return new int[] {x, y + 12};
		case LEFT:
			return new int[] {x - 12, y};
		default:
			return new int[] {x, y};
		}
	}
}

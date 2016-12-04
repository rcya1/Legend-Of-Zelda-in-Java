package reference;

import entity.Direction;
import entity.enemies.Enemy;
import entity.enemies.Octorok;
import map.TileMap;

public class MathHelper
{
	public static int sign(int number)
	{
		if(number > 0) return 1;
		else if(number < 0) return -1;
		else return 0;
	}

	public static int sign(double number)
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
			return new int[] {x, y - offset};
		case RIGHT:
			return new int[] {x + offset, y};
		case DOWN:
			return new int[] {x, y + offset};
		case LEFT:
			return new int[] {x - offset, y};
		default:
			return new int[] {x, y};
		}
	}

	public static Enemy getEnemy(String string, int x, int y, TileMap tileMap)
	{
		switch(string)
		{
		case "A":
			return new Octorok(x, y, Direction.getRandom(), tileMap);
		default:
			return null;
		}
	}
}

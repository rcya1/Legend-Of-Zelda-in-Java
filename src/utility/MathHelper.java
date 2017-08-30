package utility;

import components.entity.Direction;

//Provides various math functions
public class MathHelper
{
	//Returns a sign of an integer
	public static int sign(int number)
	{
		if(number > 0) return 1;
		else if(number < 0) return -1;
		else return 0;
	}

	//Returns the sign of a double
	public static int sign(double number)
	{
		if(number > 0) return 1;
		else if(number < 0) return -1;
		else return 0;
	}

	//Calculates the sword draw offset for a given direction
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
}

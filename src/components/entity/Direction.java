package components.entity;

//Used to store the direction and other helper methods for entites
public enum Direction
{
	UP, RIGHT, DOWN, LEFT;

	public int getInteger()
	{
		switch(this)
		{
		case UP:
			return 0;
		case RIGHT:
			return 1;
		case DOWN:
			return 2;
		case LEFT:
			return 3;
		default:
			return 0;
		}
	}

	public Direction getOpposite()
	{
		switch(this)
		{
		case UP:
			return DOWN;
		case RIGHT:
			return LEFT;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		default:
			return UP;
		}
	}

	public double getRadians()
	{
		switch(this)
		{
		case UP:
			return 0;
		case RIGHT:
			return Math.PI / 2;
		case DOWN:
			return Math.PI;
		case LEFT:
			return Math.PI * 1.5;
		default:
			return 0;
		}
	}

	public int[] getVector(int magnitude)
	{
		switch(this)
		{
		case UP:
			return new int[] {0, -magnitude};
		case RIGHT:
			return new int[] {magnitude, 0};
		case DOWN:
			return new int[] {0, magnitude};
		case LEFT:
			return new int[] {-magnitude, 0};
		default:
			return new int[] {0, 0};
		}
	}

	public double[] getVector(double magnitude)
	{
		switch(this)
		{
		case UP:
			return new double[] {0, -magnitude};
		case RIGHT:
			return new double[] {magnitude, 0};
		case DOWN:
			return new double[] {0, magnitude};
		case LEFT:
			return new double[] {-magnitude, 0};
		default:
			return new double[] {0, 0};
		}
	}

	public static Direction getRandom()
	{
		double number = Math.random() * 4;
		if(number < 1) return UP;
		else if(number < 2) return RIGHT;
		else if(number < 3) return DOWN;
		else if(number < 4) return LEFT;
		else return RIGHT;
	}

	public static Direction getExcludedRandom(Direction direction)
	{
		Direction chosenDirection = RIGHT;

		double number = Math.random() * 4;
		if(number < 1) chosenDirection = UP;
		else if(number < 2) chosenDirection = RIGHT;
		else if(number < 3) chosenDirection = DOWN;
		else if(number < 4) chosenDirection = LEFT;

		if(chosenDirection == direction) chosenDirection = getExcludedRandom(direction);
		return chosenDirection;
	}

	public static Direction parseString(String direction)
	{
		switch(direction)
		{
		case "UP":
			return UP;
		case "RIGHT":
			return RIGHT;
		case "DOWN":
			return DOWN;
		case "LEFT":
			return LEFT;
		default:
			return null;
		}
	}
}

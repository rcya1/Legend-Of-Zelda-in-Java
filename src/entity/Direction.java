package entity;

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
}

package entity.weapons;

public abstract class Weapon
{
	int x;
	int y;

	int width;
	int height;

	int damage;

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getDamage()
	{
		return damage;
	}
}

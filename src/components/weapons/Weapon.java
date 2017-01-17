package components.weapons;

import components.map.MapItem;

import java.awt.*;

public abstract class Weapon extends MapItem
{
	int damage;

	public int getDamage()
	{
		return damage;
	}

	public Rectangle getRectangle()
	{
		return new Rectangle(x - width / 2, y - height / 2, width, height);
	}
}

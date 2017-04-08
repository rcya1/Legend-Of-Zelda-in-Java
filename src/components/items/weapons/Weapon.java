package components.items.weapons;

import components.entity.enemies.Enemy;
import components.items.MapItem;

import java.awt.*;

public abstract class Weapon extends MapItem
{
	int damage;

	public void action(Enemy enemy)
	{
		//Normally do Nothing
	}

	public abstract boolean callsInvincibility();

	public int getDamage()
	{
		return damage;
	}

	public Rectangle getRectangle()
	{
		return new Rectangle(x - width / 2, y - height / 2, width, height);
	}
}

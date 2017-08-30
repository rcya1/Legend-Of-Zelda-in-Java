package components.items.weapons;

import components.MapItem;
import components.entity.enemies.Enemy;

import java.awt.*;

//A map item that can harm an enemy
public abstract class Weapon extends MapItem
{
	int damage;

	public void action(Enemy enemy)
	{
		//Normally do Nothing
	}

	//Returns whether the enemy should experience invinicibility frames after being hit by this weapon
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

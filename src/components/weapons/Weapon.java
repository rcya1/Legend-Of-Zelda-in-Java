package components.weapons;

import components.map.MapItem;

public abstract class Weapon extends MapItem
{
	int damage;

	public int getDamage()
	{
		return damage;
	}
}

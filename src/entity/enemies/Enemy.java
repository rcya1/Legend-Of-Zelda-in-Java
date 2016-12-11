package entity.enemies;

import entity.MapObject;
import entity.weapons.Sword;
import entity.weapons.Weapon;

import java.awt.*;

public abstract class Enemy extends MapObject
{
	private Sword sword = null;

	private Weapon[] weapons = {sword};

	int damage;

	public void update()
	{
		if(invincibilityFrames > 0) invincibilityFrames--;
		if(health < 0)
		{
			destroyFlag = true;
		}
	}

	void checkDamageCollisions()
	{
		for(Weapon weapon : weapons)
		{
			if(weapon != null)
			{
				boolean collision = checkCollisionWith(new Rectangle(weapon.getX() - weapon.getWidth() / 2,
						weapon.getY() - weapon.getHeight() / 2, weapon.getWidth(), weapon.getHeight()));

				if(collision && invincibilityFrames == 0)
				{
					health -= weapon.getDamage();
					invincibilityFrames = 30;
				}
			}
		}
	}

	public void setSword(Sword sword)
	{
		this.sword = sword;
		weapons = new Weapon[] {sword};
	}
}

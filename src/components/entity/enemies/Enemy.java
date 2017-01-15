package components.entity.enemies;

import components.entity.Entity;
import components.map.collectibles.Heart;
import components.weapons.Sword;
import components.weapons.Weapon;

public abstract class Enemy extends Entity
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
			overWorld.addCollectible(new Heart(x, y, overWorld));
		}
	}

	void checkDamageCollisions()
	{
		for(Weapon weapon : weapons)
		{
			if(weapon != null)
			{
				boolean collision = checkCollisionWith(weapon.getRectangle());

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

	public int getDamage()
	{
		return damage;
	}
}

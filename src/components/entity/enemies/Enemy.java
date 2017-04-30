package components.entity.enemies;

import components.entity.Entity;
import components.items.collectibles.Heart;
import components.items.weapons.Arrow;
import components.items.weapons.Boomerang;
import components.items.weapons.Sword;
import components.items.weapons.Weapon;

public abstract class Enemy extends Entity
{
	private int stunTimer = 0;

	private Sword sword = null;
	private Arrow arrow = null;
	private Boomerang boomerang = null;

	private Weapon[] weapons = {sword, arrow, boomerang};

	int damage;

	public void update()
	{
		checkDamageCollisions();
		if(invincibilityFrames > 0) invincibilityFrames--;
		if(health < 0)
		{
			destroyFlag = true;
			room.addCollectible(new Heart((int) Math.round(x), (int) Math.round(y), room));
		}
	}

	private void checkDamageCollisions()
	{
		for(Weapon weapon : weapons)
		{
			if(weapon != null)
			{
				boolean collision = checkCollisionWith(weapon.getRectangle());

				if(collision && invincibilityFrames == 0)
				{
					health -= weapon.getDamage();
					weapon.action(this);
					if(weapon.callsInvincibility()) invincibilityFrames = 30;

					if(weapon instanceof Arrow)
					{
						room.getLink().setArrow(null);
					}
				}
			}
		}
	}

	public void setSword(Sword sword)
	{
		this.sword = sword;
		weapons = new Weapon[] {sword, arrow};
	}

	public void setArrow(Arrow arrow)
	{
		this.arrow = arrow;
		weapons = new Weapon[] {sword, arrow};
	}

	public void setBoomerang(Boomerang boomerang)
	{
		this.boomerang = boomerang;
		weapons = new Weapon[] {sword, arrow, boomerang};
	}

	public int getDamage()
	{
		return damage;
	}

	int getStunTimer()
	{
		return stunTimer;
	}

	public void setStunTimer(int stunTimer)
	{
		this.stunTimer = stunTimer;
	}
}

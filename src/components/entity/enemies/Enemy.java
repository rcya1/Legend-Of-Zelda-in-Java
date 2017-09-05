package components.entity.enemies;

import components.entity.Entity;
import components.items.collectibles.Heart;
import components.items.weapons.Arrow;
import components.items.weapons.Boomerang;
import components.items.weapons.Sword;
import components.items.weapons.Weapon;
import components.map.rooms.Room;

public abstract class Enemy extends Entity
{
	private String priorState;

	private int stunTimer = 0;

	private Sword sword = null;
	private Arrow arrow = null;
	private Boomerang boomerang = null;

	private Weapon[] weapons = {sword, arrow, boomerang};

	protected int damage;

	//Setup parameters required for an enemy
	public Enemy(int x, int y, Room room, int health, int damage, String state, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.room = room;
		this.health = health;
		this.damage = damage;
		this.state = state;
		this.width = width;
		this.height = height;
	}

	public void update()
	{
		if(state.equals("KNOCKBACK"))
		{
			//Get a vector in the direction link is facing
			int[] knockback = room.getLink().getDirection().getVector(2);
			velX = knockback[0];
			velY = knockback[1];

			//Store how far the enemy has moved
			knockbackDistance += (velX + velY);
			//If the enemy has moved three tiles, then stop the knockback
			if(Math.abs(knockbackDistance) >= room.getWidthOfTile() * 2)
			{
				knockbackDistance = 0;
				state = priorState;
			}
		}
		updateHealth();
	}

	public void updateHealth()
	{
		checkDamageCollisions();
		if(invincibilityFrames > 0) invincibilityFrames--;
		if(health < 0)
		{
			destroyFlag = true;
		}

		if(destroyFlag)
		{
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
					if(!(this instanceof KnockbackResistEnemy))
					{
						priorState = state;
						state = "KNOCKBACK";
					}
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

	public int getStunTimer()
	{
		return stunTimer;
	}

	public void setStunTimer(int stunTimer)
	{
		this.stunTimer = stunTimer;
	}
}

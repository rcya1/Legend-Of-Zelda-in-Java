package components.entity.enemies;

import components.entity.Direction;
import components.map.rooms.Room;
import jdk.nashorn.internal.ir.PropertyKey;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Zola extends TeleportingEnemy implements ProjectileEnemy, ProjectileDeflectibleEnemy
{
	private boolean facingUp;

	private int timer;
	private int warpTimer;

	private Animation warping;

	private ZolaFireball fireball;

	private double destX;
	private double destY;

	public Zola(int x, int y, Room room)
	{
		this.x = x;
		this.y = y;

		width = 16;
		height = 16;

		this.room = room;

		state = "WARPING";

		fireball = null;

		health = 4;
		damage = 1;

		facingUp = false;

		timer = 0;
		warpTimer = 0;

		warping = new Animation(5, true, Images.Enemies.Zola.ZOLA_WARP_1,
				Images.Enemies.Zola.ZOLA_WARP_2);
	}

	public void update()
	{
		switch(state)
		{
		case "WARPING":
			invincibilityFrames = 1;

			if(warpTimer == 90)
			{
				warp();
			}
			if(warpTimer > 120)
			{
				state = "SHOOTING";
				warpTimer = 0;

				this.health += 2;
				if(this.health > 4) this.health = 4;

				facingUp = !(room.getLink().getY() > y);
			}
			warpTimer++;
			warping.update();
			break;
		case "SHOOTING":
			if(timer == 60)
			{
				fireball = new ZolaFireball(x, y,
						Math.atan2(room.getLink().getY() - y - room.getLink().getHeight() / 2,
								room.getLink().getX() - x));
			}
			if(timer > 120)
			{
				state = "WARPING";
				timer = 0;
			}

			timer++;
			break;
		}

		if(fireball != null)
		{
			fireball.update();
			Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
			if(!screen.intersects(fireball.getRectangle())) fireball = null;
		}

		super.update();
	}

	private void warp()
	{
		generateTargetLocation();

		//Make sure the coordinates are in bounds
		while(!checkTargetIsInMap(destX, destY))
		{
			generateTargetLocation();
		}

		//Check if the coordinates are in water
		while(!checkTargetIsAvailable(destX, destY))
		{
			generateTargetLocation();

			//Make sure the new coordinates are in bounds
			while(!checkTargetIsInMap(destX, destY))
			{
				generateTargetLocation();
			}
			//If the coordinates are no longer in water, the loop starts over
		}

		this.x = destX;
		this.y = destY;
	}

	void generateTargetLocation()
	{
		destX = x + (4 * room.getWidthOfTile() -
				Math.round(Math.random() * 8 * room.getWidthOfTile()));
		destY = y + (4 * room.getHeightOfTile() -
				Math.round(Math.random() * 8 * room.getHeightOfTile()));
	}

	boolean checkTargetIsAvailable(double destX, double destY)
	{
		return room.getTile((int) (destX / room.getWidthOfTile()),
				(int) (destY / room.getHeightOfTile())).isWater();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(fireball != null) fireball.draw(g2d);

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0) && state.equals("SHOOTING"))
		{
			if(facingUp)
			{
				g2d.drawImage(Images.Enemies.Zola.ZOLA_BACK, drawX, drawY, width, height, null);
			}
			else
			{
				g2d.drawImage(Images.Enemies.Zola.ZOLA_FRONT, drawX, drawY, width, height, null);
			}
		}

		if(state.equals("WARPING"))
		{
			warping.draw(g2d, drawX, drawY, width, height);
		}
	}
	public void removeProjectile()
	{
		fireball = null;
	}

	public Rectangle getProjectileCollisionBox()
	{
		if(fireball != null) return fireball.getRectangle();
		else return new Rectangle();
	}

	public int getProjectileDamage()
	{
		if(fireball != null) return fireball.getDamage();
		else return 0;
	}

	public int getShieldRequiredLevel()
	{
		return 1;
	}

	public Direction getProjectileDirection()
	{
		return null;
		//return fireball.getDirection();
		//TODO Do this
	}
}

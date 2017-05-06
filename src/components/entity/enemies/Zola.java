package components.entity.enemies;

import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Zola extends Enemy //TODO Divide teleporting code into other methods
		//TODO Make a Teleporting Enemies Interface
{
	private boolean facingUp;

	private int timer;
	private int warpTimer;

	private Animation warping;

	private ZolaFireball fireball;

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
				double destX = x + (4 * room.getWidthOfTile() -
						Math.round(Math.random() * 8 * room.getWidthOfTile()));
				double destY = y + (4 * room.getHeightOfTile() -
						Math.round(Math.random() * 8 * room.getHeightOfTile()));

				while(!(destX > 0 && destY > 0 && destX < room.getMapWidth()
						&& destY < room.getMapHeight()))
				{
					destX = x + (4 * room.getWidthOfTile() -
							Math.round(Math.random() * 8 * room.getWidthOfTile()));
					destY = y + (4 * room.getHeightOfTile() -
							Math.round(Math.random() * 8 * room.getHeightOfTile()));
				}

				while(!room.getTile((int) (destX / room.getWidthOfTile()),
								(int) (destY / room.getHeightOfTile())).isWater())
				{
					destX = x + (4 * room.getWidthOfTile() -
							Math.round(Math.random() * 8 * room.getWidthOfTile()));
					destY = y + (4 * room.getHeightOfTile() -
							Math.round(Math.random() * 8 * room.getHeightOfTile()));

					while(!(destX > 0 && destY > 0 && destX < room.getMapWidth()
							&& destY < room.getMapHeight()))
					{
						destX = x + (4 * room.getWidthOfTile() -
								Math.round(Math.random() * 8 * room.getWidthOfTile()));
						destY = y + (4 * room.getHeightOfTile() -
								Math.round(Math.random() * 8 * room.getHeightOfTile()));
					}
				}

				this.x = destX;
				this.y = destY;
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
				fireball = new ZolaFireball(x, y, Math.atan2(room.getLink().getY() - y - room.getLink().getHeight() / 2, room.getLink().getX() - x));
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
}

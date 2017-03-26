package components.entity.enemies;

import components.map.rooms.RoomBase;
import utility.Images;

import java.awt.*;

public class Zola extends Enemy //TODO Add the Warping animation
{
	private boolean facingUp;

	private int timer;
	private int warpTimer;

	private ZolaFireball fireball;

	public Zola(int x, int y, RoomBase room)
	{
		this.x = x;
		this.y = y;

		width = 16;
		height = 16;

		this.room = room;

		state = "WARPING";

		fireball = null;

		health = 2;
		damage = 4;

		facingUp = false;

		timer = 0;
		warpTimer = 0;
	}

	public void update()
	{
		switch(state)
		{
		case "WARPING":
			if(warpTimer == 119)
			{
				double destX = x + (64 - Math.random() * 128);
				double destY = y + (64 - Math.random() * 128);

				while(!(destX > 0 && destY > 0 && destX < room.getMapWidth() &&
						destY < room.getMapHeight()))
				{
					destX = x + (64 - Math.random() * 128);
					destY = y + (64 - Math.random() * 128);
				}

				while(!room.getTile((int) destX / 16, (int) destY / 16).isWater())
				{
					destX = x + (64 - Math.random() * 128);
					destY = y + (64 - Math.random() * 128);
				}

				this.x = destX;
				this.y = destY;
			}
			if(warpTimer > 120)
			{
				state = "SHOOTING";
				warpTimer = 0;

				if(room.getLink().getY() > y) facingUp = false;
				else facingUp = true;
			}
			warpTimer++;
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

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);

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
	}
}

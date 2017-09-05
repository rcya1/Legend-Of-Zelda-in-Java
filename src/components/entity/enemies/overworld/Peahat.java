package components.entity.enemies.overworld;

import components.entity.enemies.Enemy;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Peahat extends Enemy
{
	private int direction;

	private final Animation animation;

	private double speed;
	private int timer;

	public Peahat(int x, int y, int direction, Room room)
	{
		super(x, y, room, 4, 1, "MOVING", 16, 16);

		this.direction = direction;

		velX = 0;
		velY = 0;

		speed = 10;
		timer = (int) (Math.random() * 90);

		animation = new Animation(10, true, Images.Enemies.Peahat.PEAHAT_1, Images.Enemies.Peahat.PEAHAT_2);
	}

	public void update()
	{
		if(Math.random() * 250 > 248)
		{
			int newDirection = (int) (Math.random() * 8);
			while(newDirection == direction)
			{
				newDirection = (int) (Math.random() * 8);
			}

			direction = newDirection;
		}

		double angle = direction * Math.PI / 4;

		velX = Math.cos(angle) * speed / 10;
		velY = Math.sin(angle) * speed / 10;

		x += velX;
		y += velY;

		Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
		if(!screen.intersects(getRectangle()))
		{
			direction = ((direction + 4) + (int) (Math.random() * 3 - 1.5)) % 8;
		}

		animation.setDelay((int) (15 - speed));
		if(animation.getDelay() == 14) animation.setDelay(120);

		animation.update();

		super.update();

		speed = (Math.cos(timer / 90.0) * 5) + 5;

		if(speed > 2) invincibilityFrames = 1;
		else invincibilityFrames = 0;

		timer++;
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			animation.draw(g2d, drawX, drawY, width, height);
		}
	}
}

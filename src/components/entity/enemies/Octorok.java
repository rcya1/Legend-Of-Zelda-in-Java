package components.entity.enemies;

import components.map.rooms.Room;
import utility.Animation;
import components.entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Octorok extends Enemy
{
	private int shootingTimer;
	private int movementRefreshTimer;

	Animation animation;

	private OctorokPellet pellet;

	public Octorok(int x, int y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		this.room = room;

		velX = 0;
		velY = 0;

		width = 16;
		height = 16;

		moveSpeed = 0.75;
		state = "MOVING";

		animation = new Animation(20, true, Images.Enemies.Octorok.OCTOROK_1, Images.Enemies.Octorok.OCTOROK_2);

		pellet = null;

		health = 2;
		damage = 4;
	}

	public void update()
	{
		if(getStunTimer() == 0)
		{
			switch(state)
			{
			case "MOVING":
				double[] vector = direction.getVector(moveSpeed);
				velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
				velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

				animation.update();

				if((Math.random() * 100) < 2) direction = Direction.getRandom();
				if((Math.random() * 300) < 2)
				{
					state = "SHOOTING";
					shootingTimer = 0;
				}

				break;
			case "SHOOTING":
				velX = 0;
				velY = 0;

				if(shootingTimer < 90)
				{
					shootingTimer++;
					if(shootingTimer == 60)
					{
						pellet = new OctorokPellet(x, y, direction);
					}
				}
				else
				{
					state = "MOVING";
				}
				break;
			default:
				break;
			}

			if(handleTileCollisions() && movementRefreshTimer == 0)
			{
				movementRefreshTimer = 120;
				direction = Direction.getExcludedRandom(direction);
			}

			if(movementRefreshTimer > 0) movementRefreshTimer--;
		}
		else setStunTimer(getStunTimer() - 1);

		if(pellet != null)
		{
			pellet.update();
			Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
			if(!screen.intersects(pellet.getRectangle())) pellet = null;
		}
		super.update();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(pellet != null) pellet.draw(g2d);

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			AffineTransform transform = g2d.getTransform();
			g2d.rotate(direction.getRadians(), drawX + width / 2, drawY + height / 2);
			animation.draw(g2d, drawX, drawY, width, height);
			g2d.setTransform(transform);
		}
	}

	public void removePellet()
	{
		pellet = null;
	}

	public Rectangle getPelletCollisionBox()
	{
		if(pellet != null)
		{
			return pellet.getRectangle();
		}
		else
		{
			return null;
		}
	}

	public int getPelletDamage()
	{
		if(pellet != null)
		{
			return pellet.getDamage();
		}
		else
		{
			return 0;
		}
	}
}

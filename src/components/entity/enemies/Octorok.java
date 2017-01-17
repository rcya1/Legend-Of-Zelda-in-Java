package components.entity.enemies;

import components.OverWorld;
import components.Animation;
import components.entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Octorok extends Enemy
{
	private int shootingTimer;
	private int movementRefreshTimer;

	private final Animation animation;

	private OctorokPellet pellet;

	public Octorok(int x, int y, Direction direction, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		this.overWorld = overWorld;

		velX = 0;
		velY = 0;

		width = 16;
		height = 16;

		moveSpeed = 0.5;
		state = "MOVING";

		animation = new Animation(20, true, Images.Enemies.OCTOROK, Images.Enemies.OCTOROK_2);

		pellet = null;

		health = 4;
		damage = 4;
	}

	public void update()
	{
		switch(state)
		{
		case "MOVING":
			double[] vector = direction.getVector(moveSpeed);
			//velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
			//velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

			velX = vector[0];
			velY = vector[1];

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
					pellet = new OctorokPellet(x, y, direction, overWorld);
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

		if(pellet != null)
		{
			pellet.update();
			if(!overWorld.checkVisibility(pellet.getRectangle())) pellet = null;
		}

		if(handleTileCollisions() && movementRefreshTimer == 0)
		{
			movementRefreshTimer = 120;
			direction = Direction.getExcludedRandom(direction);
		}

		if(movementRefreshTimer > 0) movementRefreshTimer --;

		checkDamageCollisions();

		super.update();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - overWorld.getCameraX();
		drawY = (int) Math.round(y) - overWorld.getCameraY();

		if(pellet != null) pellet.draw(g2d);

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			AffineTransform transform = g2d.getTransform();
			g2d.rotate(direction.getRadians(), drawX, drawY);
			animation.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
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

package entity.enemies;

import entity.Direction;
import entity.Animation;

import entity.MapObject;
import map.TileMap;
import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Octorok extends MapObject implements Enemy
{
	private int timer;

	private Animation animation;

	private OctorokPellet pellet;

	public Octorok(int x, int y, Direction direction, TileMap tileMap)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		this.tileMap = tileMap;

		velX = 0;
		velY = 0;

		width = 16;
		height = 16;

		moveSpeed = 0.5;
		state = "MOVING";

		animation = new Animation(20, Images.Enemies.OCTOROK, Images.Enemies.OCTOROK_2);

		pellet = null;
	}

	public void update()
	{
		switch(state)
		{
		case "MOVING":
			double[] vector = direction.getVector(moveSpeed);
			velX = vector[0];
			velY = vector[1];

			animation.update();

			if((Math.random() * 100) < 2) direction = Direction.getRandom();
			if((Math.random() * 300) < 2)
			{
				state = "SHOOTING";
				timer = 0;
			}

			break;
		case "SHOOTING":
			velX = 0;
			velY = 0;

			if(timer < 90)
			{
				timer++;
				if(timer == 60)
				{
					pellet = new OctorokPellet(x, y, direction);
				}
			}
			else
			{
				state = "MOVING";
			}
			break;
		}

		if(pellet != null) pellet.update();

		handleCollisions();
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), x, y);
		animation.draw(g2d, x - width / 2, y - height / 2, width, height);
		g2d.setTransform(transform);

		if(pellet != null) pellet.draw(g2d);
	}
}

package entity.enemies;

import entity.Direction;
import entity.Animation;

import map.TileMap;
import reference.Images;
import reference.MapHelper;
import reference.MathHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Octorok
{
	private TileMap tileMap;

	private int x;
	private int y;

	private double subPixelX;
	private double subPixelY;

	private double velX;
	private double velY;

	private double moveSpeed;

	private int width;
	private int height;

	private Direction direction;

	private String state;

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

		subPixelX += velX;
		subPixelY += velY;

		int newVelX = Math.round((float) subPixelX);
		int newVelY = Math.round((float) subPixelY);

		subPixelX = velX - newVelX;
		subPixelY = velY - newVelY;

		if(newVelX > Math.ceil(moveSpeed)) newVelX = (int) Math.ceil(newVelX);
		if(newVelY > Math.ceil(moveSpeed)) newVelY = (int) Math.ceil(newVelY);

		int collisionOffset = 6;

		for(int i = 0; i < Math.abs(newVelX); i++)
		{
			subPixelY = 0;
			int temporaryX = x + MathHelper.sign(velX);
			if(!MapHelper.checkCollisionWithTileMap(temporaryX, y, tileMap,
					width - collisionOffset, height - collisionOffset))
				x = temporaryX;
			else
			{
				direction = Direction.getRandom();
			}
		}

		for(int i = 0; i < Math.abs(newVelY); i++)
		{
			subPixelX = 0;
			int temporaryY = y + MathHelper.sign(velY);
			if(!MapHelper.checkCollisionWithTileMap(x, temporaryY, tileMap,
					width - collisionOffset, height - collisionOffset))
				y = temporaryY;
			else
			{
				direction = Direction.getRandom();
			}
		}

		if(pellet != null) pellet.update();
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

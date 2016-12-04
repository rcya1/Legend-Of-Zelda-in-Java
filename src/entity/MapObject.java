package entity;

import map.TileMap;
import reference.MapHelper;
import reference.MathHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class MapObject
{
	protected TileMap tileMap;

	protected int x;
	protected int y;

	protected double subPixelXVelocity;
	protected double subPixelYVelocity;

	protected double velX;
	protected double velY;

	protected double moveSpeed;

	protected int width;
	protected int height;

	protected Direction direction;

	protected String state;

	public abstract void update();

	public abstract void draw(Graphics2D g2d);

	public boolean handleCollisions()
	{
		boolean collision = false;

		subPixelXVelocity += velX;
		subPixelYVelocity += velY;

		int newVelX = Math.round((float) subPixelXVelocity);
		int newVelY = Math.round((float) subPixelYVelocity);

		subPixelXVelocity = velX - newVelX;
		subPixelYVelocity = velY - newVelY;

		int collisionOffset = 6;

		if(newVelX > Math.ceil(moveSpeed)) newVelX = (int) Math.ceil(newVelX);
		if(newVelY > Math.ceil(moveSpeed)) newVelY = (int) Math.ceil(newVelY);

		for(int i = 0; i < Math.abs(newVelX); i++)
		{
			subPixelYVelocity = 0;
			int temporaryX = x + MathHelper.sign(velX);
			if(!MapHelper.checkCollisionWithTileMap(temporaryX, y, tileMap,
					width - collisionOffset, height - collisionOffset))
			{
				x = temporaryX;
				collision = true;
			}
			else break;
		}

		for(int i = 0; i < Math.abs(newVelY); i++)
		{
			subPixelXVelocity = 0;
			int temporaryY = y + MathHelper.sign(velY);
			if(!MapHelper.checkCollisionWithTileMap(x, temporaryY, tileMap, width - collisionOffset, height - collisionOffset))
			{
				y = temporaryY;
				collision = true;
			}
			else break;
		}

		return collision;
	}
}

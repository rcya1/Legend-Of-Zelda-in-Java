package entity.weapons;

import entity.Direction;
import reference.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sword extends Weapon
{
	private int velX;
	private int velY;

	private Direction direction;

	public Sword(int x, int y, Direction direction)
	{
		this.x = x;
		this.y = y;

		velX = 0;
		velY = 0;

		this.direction = direction;

		width = 16;
		height = 16;

		damage = 2;
	}

	public void update()
	{
		x += velX;
		y += velY;
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), x, y);
		g2d.drawImage(Images.Link.SWORD, x - width / 2, y - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	public void retract()
	{
		int[] vector = direction.getVector(4);

		velX = -vector[0];
		velY = -vector[1];
	}
}

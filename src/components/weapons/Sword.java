package components.weapons;

import components.Room;
import components.entity.Direction;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Sword extends Weapon
{
	private int velX;
	private int velY;

	private final Direction direction;

	public Sword(int x, int y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		velX = 0;
		velY = 0;

		this.direction = direction;

		this.room = room;

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
		int drawX = x;
		int drawY = y;

		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), drawX, drawY);
		g2d.drawImage(Images.Link.SWORD, drawX - width / 2, drawY - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	public void retract()
	{
		int[] vector = direction.getVector(4);

		velX = -vector[0];
		velY = -vector[1];
	}
}

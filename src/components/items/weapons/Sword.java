package components.items.weapons;

import components.entity.Direction;
import components.map.rooms.WorldRoom;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

//The sword that Link wields
public class Sword extends Weapon
{
	private int velX;
	private int velY;

	private final Direction direction;

	public Sword(int x, int y, Direction direction, WorldRoom room)
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

	//Updates the position of the sword
	public void update()
	{
		x += velX;
		y += velY;
	}

	//Draws the sword at the current x/y
	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), x, y);
		g2d.drawImage(Images.Link.SWORD, x - width / 2, y - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	//Pulls back the sword
	public void retract()
	{
		int[] vector = direction.getVector(4);

		velX = -vector[0];
		velY = -vector[1];
	}

	public boolean callsInvincibility()
	{
		return true;
	}
}

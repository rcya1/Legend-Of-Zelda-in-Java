package components.items.weapons;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Arrow extends Weapon
{
	private Direction direction;

	public Arrow(int x, int y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		width = 16;
		height = 16;

		damage = 2;
	}

	public void update()
	{
		this.x += direction.getVector(2)[0];
		this.y += direction.getVector(2)[1];
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.rotate(direction.getRadians(), x, y);
		g2d.drawImage(Images.Link.Items.ARROW, x - width / 2, y - width / 2, width, height, null);
		g2d.setTransform(transform);
	}

	public boolean callsInvincibility()
	{
		return true;
	}
}

package components.items.weapons;

import components.entity.Direction;
import components.entity.enemies.Enemy;
import components.map.rooms.Room;
import utility.Images;
import utility.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Boomerang extends Weapon
{
	private double x;
	private double y;

	private double velX;
	private double velY;

	private int returnTimer;

	private int animationTimer;

	public Boomerang(double x, double y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		width = 16;
		height = 16;

		velX = direction.getVector(2)[0];
		velY = direction.getVector(2)[1];

		this.room = room;

		returnTimer = 40;
		animationTimer = 0;
	}

	public void update()
	{
		if(returnTimer == 0)
		{
			double angle = Math.atan2(room.getLink().getY() - y - room.getLink().getHeight() / 2,
					room.getLink().getX() - x);
			velX = Math.cos(angle) * 2.00;
			velY = Math.sin(angle) * 2.00;
		}

		this.x += velX;
		this.y += velY;

		int leftColumn = (int) Math.round(x - width / 2) / room.getWidthOfTile();
		int rightColumn = (int) Math.round(x + width / 2) / room.getWidthOfTile();
		int topRow = (int) Math.round(y - height / 2) / room.getHeightOfTile();
		int bottomRow = (int) Math.round(y + height / 2) / room.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > room.getNumOfColumns() - 1) rightColumn = room.getNumOfColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > room.getNumOfRows() - 1) bottomRow = room.getNumOfRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				Tile tile = room.getTile(i, j);
				if(tile != null)
				{
					Rectangle tileRectangle = new Rectangle(i * room.getWidthOfTile(),
							j * room.getHeightOfTile(), room.getWidthOfTile(),
							room.getHeightOfTile() / 2);

					if(!tile.isPassible() && getRectangle().intersects(tileRectangle))
					{
						returnTimer = 0;
					}
				}
			}
		}

		if(returnTimer > 0) returnTimer--;

		animationTimer++;
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();

		if(animationTimer % 16 < 4) g2d.rotate(Math.PI / 2, x, y);
		else if(animationTimer % 16 < 8) g2d.rotate(Math.PI, x, y);
		else if(animationTimer % 16 < 12) g2d.rotate(Math.PI * 1.5, x, y);

		g2d.drawImage(Images.Link.Items.BOOMERANG, (int) x - width / 2, (int) y - width / 2,
				width, height, null);

		g2d.setTransform(transform);
	}

	public void action(Enemy enemy)
	{
		enemy.setStunTimer(120);
		returnTimer = 0;
	}

	public boolean callsInvincibility()
	{
		return false;
	}

	public int getReturnTimer()
	{
		return returnTimer;
	}

	public Rectangle getRectangle()
	{
		return new Rectangle((int) x, (int) y, width, height);
	}
}

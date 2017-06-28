package components.items.weapons;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Images;
import utility.Tile;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Arrow extends Weapon
{
	private final Direction direction;

	public Arrow(int x, int y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		this.room = room;

		width = 16;
		height = 16;

		damage = 2;
	}

	public void update()
	{
		x += direction.getVector(2)[0];
		y += direction.getVector(2)[1];

		int leftColumn = Math.round(x - width / 2) / room.getWidthOfTile();
		int rightColumn = Math.round(x + width / 2) / room.getWidthOfTile();
		int topRow = Math.round(y - height / 2) / room.getHeightOfTile();
		int bottomRow = Math.round(y + height / 2) / room.getHeightOfTile();

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
						room.getLink().setArrow(null);
					}
				}
			}
		}
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

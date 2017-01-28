package components.entity;

import components.Room;
import components.Tile;
import utility.MathHelper;

import java.awt.*;

public abstract class Entity
{
	protected Room room;

	protected double x;
	protected double y;

	protected int drawX;
	protected int drawY;

	protected int width;
	protected int height;

	protected double velX;
	protected double velY;

	protected double moveSpeed;

	protected int health;
	protected int invincibilityFrames;

	protected Direction direction;

	protected String state;

	protected boolean destroyFlag;


	public abstract void update();

	public abstract void draw(Graphics2D g2d);


	boolean checkCollisionWith(Entity other)
	{
		return getRectangle().intersects(other.getRectangle());
	}

	protected boolean checkCollisionWith(Rectangle otherRectangle)
	{
		return getRectangle().intersects(otherRectangle);
	}

	private Rectangle getRectangle()
	{
		return new Rectangle((int) Math.round(x - width / 2),
				(int) Math.round(y - height / 2), width, height);
	}

	protected boolean handleTileCollisions()
	{
		boolean collisionX = false;
		boolean collisionY = false;

		if(checkCollisionWithTileMap(x + velX, y))
		{
			collisionX = true;
		}
		if(checkCollisionWithTileMap(x, y + velY))
		{
			collisionY = true;
		}

		x += velX;
		y += velY;

		if(collisionX)
		{
			x = ((int) Math.round(x) / 8) * 8;
			if(MathHelper.sign(velX) == -1) x += 8;
			if(velX == 0 && direction == Direction.LEFT) x += 8;

			velX = 0;
			velY = 0;
		}

		if(collisionY)
		{
			y = ((int) Math.round(y) / 8) * 8;
			if(MathHelper.sign(velY) == -1) y += 8;
			if(velY == 0 && direction == Direction.UP) y += 8;

			velX = 0;
			velY = 0;
		}

		return collisionX || collisionY;
	}

	private boolean checkCollisionWithTileMap(double newX, double newY)
	{
		boolean collisionFlag = false;

		int leftColumn = (int) Math.round(newX - width / 2) / room.getWidthOfTile();
		int rightColumn = (int) Math.round(newX + width / 2) / room.getWidthOfTile();
		int topRow = (int) Math.round(newY - height / 2) / room.getHeightOfTile();
		int bottomRow = (int) Math.round(newY + height / 2) / room.getHeightOfTile();

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

					Rectangle thisRectangle = new Rectangle((int) Math.round(newX) - width / 2,
							(int) Math.round(newY) - height / 2, width, height);

					if(!tile.isPassible() && thisRectangle.intersects(tileRectangle))
					{
						collisionFlag = true;
					}
				}
			}
		}

		return collisionFlag;
	}

	protected int alignToGrid(double value, int alignTo)
	{
		int extra = (int) Math.round(value) % alignTo; //Figure out how much the value is off by
		int halfway = (alignTo - 1) / 2;
		//Find the halfway mark of the offset (subtract 1 because modulo returns 0-7)

		//If the extra is greater than the halfway, return a vel that pushes it to the next mark
		if(extra > halfway)
		{
			return alignTo - extra;
		}
		//Otherwise, return a vel that pushes it to the previous mark
		else
		{
			return -extra;
		}
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public boolean getDestroyFlag()
	{
		return destroyFlag;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public String getState()
	{
		return state;
	}

	public int getHealth()
	{
		return health;
	}

	void drawDebug(Graphics2D g2d)
	{
		g2d.setColor(new Color(255, 0, 0, 100));

		int leftColumn = (int) (x - width / 2) / room.getWidthOfTile();
		int rightColumn = (int) (x + width / 2) / room.getWidthOfTile();
		int topRow = (int) (y - height / 2) / room.getHeightOfTile();
		int bottomRow = (int) (y + height / 2) / room.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > room.getNumOfColumns() - 1) rightColumn = room.getNumOfColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > room.getNumOfRows() - 1) bottomRow = room.getNumOfRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				g2d.fillRect(i * 16, j * 16, 16, 16);
			}
		}

		g2d.setColor(new Color(0, 0, 255, 100));
	}
}

package components.entity;

import components.OverWorld;
import components.Tile;
import utility.MathHelper;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class Entity
{
	protected OverWorld overWorld;

	protected double x;
	protected double y;

	protected int drawX;
	protected int drawY;

	protected int width;
	protected int height;

	private double subPixelXVelocity;
	private double subPixelYVelocity;

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
		return new Rectangle((int) x - width / 2, (int) y - height / 2, width, height);
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
			x = ((int) x / 8) * 8;
			if(MathHelper.sign(velX) == -1) x += 8;
			if(velX == 0 && direction == Direction.LEFT) x += 8;

			alignToGrid(x, 8);
		}
		if(collisionY)
		{
			y = ((int) y / 8) * 8;
			if(MathHelper.sign(velY) == -1) y += 8;
			if(velY == 0 && direction == Direction.UP) y += 8;

			alignToGrid(y, 8);
		}

		return collisionX || collisionY;
	}

	private boolean checkCollisionWithTileMap(double x, double y)
	{
		boolean collisionFlag = false;

		int leftColumn = (int) (x - width / 2) / overWorld.getWidthOfTile();
		int rightColumn = (int) (x + width / 2) / overWorld.getWidthOfTile();
		int topRow = (int) (y - height / 2) / overWorld.getHeightOfTile();
		int bottomRow = (int) (y + height / 2) / overWorld.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > overWorld.getNumOfColumns() - 1) rightColumn = overWorld.getNumOfColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > overWorld.getNumOfRows() - 1) bottomRow = overWorld.getNumOfRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				Tile tile = overWorld.getTile(i, j);
				Rectangle tileRectangle = new Rectangle(i * overWorld.getWidthOfTile(),
						j * overWorld.getHeightOfTile(), overWorld.getWidthOfTile(),
						overWorld.getHeightOfTile() / 2);

				if(!tile.isPassible() && getRectangle().intersects(tileRectangle))
				{
					collisionFlag = true;
				}
			}
		}

		return collisionFlag;
	}

	int alignToGrid(double value, int alignTo)
	{
		int extra = (int) value % alignTo; //Figure out how much the value is off by
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

		int leftColumn = (int) (x - width / 2) / overWorld.getWidthOfTile();
		int rightColumn = (int) (x + width / 2) / overWorld.getWidthOfTile();
		int topRow = (int) (y - height / 2) / overWorld.getHeightOfTile();
		int bottomRow = (int) (y + height / 2) / overWorld.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > overWorld.getNumOfColumns() - 1) rightColumn = overWorld.getNumOfColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > overWorld.getNumOfRows() - 1) bottomRow = overWorld.getNumOfRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				g2d.fillRect(i * 16 - overWorld.getCameraX(), j * 16 - overWorld.getCameraY(), 16, 16);
			}
		}

		g2d.setColor(new Color(0, 0, 255, 100));

		AffineTransform t = g2d.getTransform();
		g2d.translate(-overWorld.getCameraX(), -overWorld.getCameraY());
		g2d.fill(getRectangle());
		g2d.setTransform(t);
	}
}

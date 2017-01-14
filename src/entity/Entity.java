package entity;

import components.OverWorld;
import components.Tile;
import utility.MathHelper;

import java.awt.*;

public abstract class Entity
{
	protected OverWorld overWorld;

	protected int x;
	protected int y;

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
		return new Rectangle(x - width / 2, y - height / 2, width, height);
	}

	protected boolean handleTileCollisions()
	{
		int collisionOffset = 2;

		boolean collision = false;

		subPixelXVelocity += velX;
		subPixelYVelocity += velY;

		int newVelX = Math.round((float) subPixelXVelocity);
		int newVelY = Math.round((float) subPixelYVelocity);

		subPixelXVelocity = velX - newVelX;
		subPixelYVelocity = velY - newVelY;

		if(newVelX > Math.ceil(moveSpeed)) newVelX = (int) Math.ceil(newVelX);
		if(newVelY > Math.ceil(moveSpeed)) newVelY = (int) Math.ceil(newVelY);

		if(subPixelXVelocity > Math.ceil(moveSpeed)) subPixelXVelocity = (int) Math.ceil(moveSpeed);
		if(subPixelYVelocity > Math.ceil(moveSpeed)) subPixelYVelocity = (int) Math.ceil(moveSpeed);

		if(subPixelXVelocity < Math.floor(-moveSpeed)) subPixelXVelocity = (int) Math.floor(-moveSpeed);
		if(subPixelYVelocity < Math.floor(-moveSpeed)) subPixelYVelocity = (int) Math.floor(-moveSpeed);

		for(int i = 0; i < Math.abs(newVelX); i++)
		{
			subPixelYVelocity = 0;
			int temporaryX = x + MathHelper.sign(velX);
			if(!checkCollisionWithTileMap(temporaryX, y, collisionOffset))
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
			if(!checkCollisionWithTileMap(x, temporaryY, collisionOffset))
			{
				y = temporaryY;
				collision = true;
			}
			else break;
		}

		return collision;
	}

	private boolean checkCollisionWithTileMap(int x, int y, int collisionOffset)
	{
		boolean collisionFlag = false;
		int entityWidth = width - collisionOffset;
		int entityHeight = height - collisionOffset;

		int leftColumn = (x - entityWidth / 2) / overWorld.getWidthOfTile();
		int rightColumn = (x + entityWidth / 2) / overWorld.getWidthOfTile();
		int topRow = (y - entityHeight / 2) / overWorld.getHeightOfTile();
		int bottomRow = (y + entityHeight / 2) / overWorld.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > overWorld.getNumOfColumns() - 1) rightColumn = overWorld.getNumOfColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > overWorld.getNumOfRows() - 1) bottomRow = overWorld.getNumOfRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				Tile tile = overWorld.getTile(i, j);
				if(!tile.isPassible())
				{
					collisionFlag = true;
				}
			}
		}
		return collisionFlag;
	}

	int alignToGrid(int value, int alignTo)
	{
		int extra = value % alignTo; //Figure out how much the value os off by
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

	public int getX()
	{
		return x;
	}

	public int getY()
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
}

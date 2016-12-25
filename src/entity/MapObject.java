package entity;

import map.Tile;
import map.TileMap;
import reference.MathHelper;

import java.awt.*;

public abstract class MapObject
{
	protected TileMap tileMap;

	protected int x;
	protected int y;

	protected int drawX;
	protected int drawY;

	int transitionAmountX;
	int transitionAmountY;

	int transitionVelX;
	int transitionVelY;

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


	protected boolean checkCollisionWith(MapObject other)
	{
		Rectangle thisRectangle = new Rectangle(x - width / 2, y - width / 2, width, height);
		Rectangle otherRectangle = new Rectangle(other.x - other.width / 2, other.y - other.height / 2,
				other.width, other.height);

		return thisRectangle.intersects(otherRectangle);
	}

	protected boolean checkCollisionWith(Rectangle otherRectangle)
	{
		Rectangle thisRectangle = new Rectangle(x - width / 2, y - width / 2, width, height);

		return thisRectangle.intersects(otherRectangle);
	}

	protected boolean handleCollisions()
	{
		int collisionOffset = 6;

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

		int leftColumn = (x - entityWidth / 2) / tileMap.getWidthOfTile();
		int rightColumn = (x + entityWidth / 2) / tileMap.getWidthOfTile();
		int topRow = (y - entityHeight / 2) / tileMap.getHeightOfTile();
		int bottomRow = (y + entityHeight / 2) / tileMap.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > tileMap.getColumns() - 1) rightColumn = tileMap.getColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > tileMap.getRows() - 1) bottomRow = tileMap.getRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				Tile tile = tileMap.getTile(i, j);
				if(!tile.isPassible())
				{
					collisionFlag = true;
				}
			}
		}
		return collisionFlag;
	}

	public void setTransitionVector(int transitionVelX, int transitionVelY)
	{
		this.transitionVelX = transitionVelX;
		this.transitionVelY = transitionVelY;
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
}

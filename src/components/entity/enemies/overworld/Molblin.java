package components.entity.enemies.overworld;

import components.entity.Direction;
import components.entity.enemies.Enemy;
import components.entity.enemies.ProjectileDeflectibleEnemy;
import components.entity.enemies.ProjectileEnemy;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;
import utility.Tile;

import java.awt.*;

public class Molblin extends Enemy implements ProjectileEnemy, ProjectileDeflectibleEnemy
{
	private int shootingTimer;
	private int movementRefreshTimer;

	Animation up;
	Animation right;
	Animation down;
	Animation left;

	private MolblinSpear spear;

	public Molblin(int x, int y, Direction direction, Room room)
	{
		super(x, y, room, 4, 1, "MOVING", 16, 16);

		this.direction = direction;

		velX = 0;
		velY = 0;

		moveSpeed = 0.5;

		up = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_UP,
				Images.Enemies.Molblin.MOLBLIN_UP_2);
		right = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_RIGHT,
				Images.Enemies.Molblin.MOLBLIN_RIGHT_2);
		down = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_DOWN,
				Images.Enemies.Molblin.MOLBLIN_DOWN_2);
		left = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_LEFT,
				Images.Enemies.Molblin.MOLBLIN_LEFT_2);

		spear = null;
	}

	public void update()
	{
		switch(state)
		{
			case "MOVING":
				double[] vector = direction.getVector(moveSpeed);
				velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
				velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

				if((Math.random() * 100) < 2) direction = Direction.getRandom();
				if((Math.random() * 300) < 2)
				{
					state = "SHOOTING";
					shootingTimer = 0;
				}
				if(x < 0) direction = Direction.RIGHT;
				if(y < 0) direction = Direction.DOWN;
				if(x > room.getMapWidth()) direction = Direction.LEFT;
				if(y > room.getMapHeight()) direction = Direction.UP;

				break;
			case "SHOOTING":
				velX = 0;
				velY = 0;

				if(shootingTimer < 90)
				{
					shootingTimer++;
					if(shootingTimer == 60)
					{
						spear = new MolblinSpear(x, y, direction);
					}
				}
				else
				{
					state = "MOVING";
				}
				break;
			default:
				break;
		}

		switch(direction)
		{
			case UP:
				up.update();
				break;
			case RIGHT:
				right.update();
				break;
			case DOWN:
				down.update();
				break;
			case LEFT:
				left.update();
				break;
		}

		if(handleTileCollisions() && movementRefreshTimer == 0)
		{
			movementRefreshTimer = 120;
			direction = Direction.getExcludedRandom(direction);
		}

		if(movementRefreshTimer > 0) movementRefreshTimer--;

		if(spear != null)
		{
			spear.update();

			Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
			if(!screen.intersects(spear.getRectangle())) spear = null;

			if(spear != null) handleSpearWallCollision();
		}
		super.update();
	}

	private void handleSpearWallCollision()
	{
		double x = spear.getRectangle().getCenterX();
		double y = spear.getRectangle().getCenterY();

		int width = (int) spear.getRectangle().getWidth();
		int height = (int) spear.getRectangle().getHeight();

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

					if(!tile.isPassible() && spear.getRectangle().intersects(tileRectangle))
					{
						spear = null;
						break;
					}
				}

				if(spear == null) break;
			}

			if(spear == null) break;
		}
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(spear != null) spear.draw(g2d);

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			switch(direction)
			{
			case UP:
				up.draw(g2d, drawX, drawY, width, height);
				break;
			case RIGHT:
				right.draw(g2d, drawX, drawY, width, height);
				break;
			case DOWN:
				down.draw(g2d, drawX, drawY, width, height);
				break;
			case LEFT:
				left.draw(g2d, drawX, drawY, width, height);
				break;
			}
		}
	}

	public void removeProjectile()
	{
		spear = null;
	}

	public Rectangle getProjectileCollisionBox()
	{
		if(spear != null)
		{
			return spear.getRectangle();
		}
		else
		{
			return null;
		}
	}

	public int getProjectileDamage()
	{
		if(spear != null)
		{
			return spear.getDamage();
		}
		else
		{
			return 0;
		}
	}

	public int getShieldRequiredLevel()
	{
		return 0;
	}

	public Direction getProjectileDirection()
	{
		return spear.getDirection();
	}
}

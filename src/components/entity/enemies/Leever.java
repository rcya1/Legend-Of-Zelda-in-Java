package components.entity.enemies;

import components.entity.Direction;
import components.entity.Link;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Leever extends Enemy //TODO Divide tunnelling code into other methods
	//TODO Make a Teleporting Enemies Interface
{
	Animation normal;
	Animation burrow;
	Animation emerge;

	private int targetColumn;
	private int targetRow;

	public Leever(int x, int y, Direction direction, Room room)
	{
		this.x = x;
		this.y = y;

		this.direction = direction;

		this.room = room;

		velX = 0;
		velY = 0;

		width = 16;
		height = 16;

		targetColumn = -2;
		targetRow = -2;

		moveSpeed = 0.8;
		state = "NORMAL";

		normal = new Animation(10, true, Images.Enemies.Leever.LEEVER_1, Images.Enemies.Leever.LEEVER_2);
		burrow = new Animation(10, false, Images.Enemies.Leever.LEEVER_MIDBURROW, Images.Enemies.Leever.LEEVER_BURROW_1, Images.Enemies.Leever.LEEVER_BURROW_2);
		emerge = new Animation(10, false, Images.Enemies.Leever.LEEVER_BURROW_2, Images.Enemies.Leever.LEEVER_BURROW_1, Images.Enemies.Leever.LEEVER_MIDBURROW);

		health = 4;
		damage = 1;
	}

	public void update()
	{
		switch(state)
		{
		case "NORMAL":
			double[] vector = direction.getVector(moveSpeed);
			velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
			velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

			Rectangle screen = new Rectangle(room.getMapWidth() - room.getWidthOfTile(), room.getMapHeight() - room.getHeightOfTile());
			if(Math.random() * 1000 > 999 || handleTileCollisions() || !screen.intersects(getRectangle()))
				state = "BURROW";
			normal.update();
			break;
		case "BURROW":
			if(targetColumn == -2)
			{
				Link link = room.getLink();
				targetColumn = (int) (link.getX() / room.getWidthOfTile())
						+ (3 - (int) (Math.random() * 6));
				targetRow = (int) (link.getY() / room.getWidthOfTile())
						+ (3 - (int) (Math.random() * 6));

				while(!(targetColumn > 0 && targetRow > 0 &&
						targetColumn < room.getNumOfColumns() - 1 &&
						targetRow < room.getNumOfRows() - 1))
				{
					targetColumn = (int) (link.getX() / room.getWidthOfTile())
							+ (3 - (int) (Math.random() * 6));
					targetRow = (int) (link.getY() / room.getWidthOfTile())
							+ (3 - (int) (Math.random() * 6));

				}

				while(!room.getTile(targetColumn, targetRow).isPassible())
				{
					targetColumn = (int) (link.getX() / room.getWidthOfTile())
							+ (3 - (int) (Math.random() * 6));
					targetRow = (int) (link.getY() / room.getWidthOfTile())
							+ (3 - (int) (Math.random() * 6));

					while(!(targetColumn > 0 && targetRow > 0 &&
							targetColumn < room.getNumOfColumns() - 1 &&
							targetRow < room.getNumOfRows() - 1))
					{
						targetColumn = (int) (link.getX() / room.getWidthOfTile())
								+ (3 - (int) (Math.random() * 6));
						targetRow = (int) (link.getY() / room.getWidthOfTile())
								+ (3 - (int) (Math.random() * 6));
					}
				}
			}

			if(burrow.getIndex() == -1)
			{
				this.x = targetColumn * room.getWidthOfTile();
				this.y = targetRow * room.getHeightOfTile();

				state = "EMERGE";

				burrow.reset();
			}
			burrow.update();
			break;
		case "EMERGE":
			Link link = room.getLink();
			double columnDifference = link.getX() / room.getWidthOfTile() - targetColumn;
			double rowDifference = link.getY() / room.getHeightOfTile() - targetRow;

			if(columnDifference >= rowDifference)
			{
				if(columnDifference < 0) direction = Direction.LEFT;
				else direction = Direction.RIGHT;
			}
			else
			{
				if(rowDifference < 0) direction = Direction.UP;
				else direction = Direction.DOWN;
			}

			if(emerge.getIndex() == -1)
			{
				targetColumn = -2;
				targetRow = -2;

				state = "NORMAL";
				emerge.reset();
			}
			emerge.update();
			break;
		}

		super.update();
	}

	public void update(boolean callSuper)
	{
		super.update();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			switch(state)
			{
			case "NORMAL":
				normal.draw(g2d, drawX, drawY, width, height);
				break;
			case "BURROW":
				burrow.draw(g2d, drawX, drawY, width, height);
			case "EMERGE":
				emerge.draw(g2d, drawX, drawY, width, height);
			}
		}
	}
}

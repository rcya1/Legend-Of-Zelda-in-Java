package components.entity.enemies;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class LeeverBlue extends Leever
{
	private int movementRefreshTimer;
	private Animation tunnel;

	public LeeverBlue(int x, int y, Direction direction, Room room)
	{
		super(x, y, direction, room);

		health = 4;
		damage = 4;

		movementRefreshTimer = 0;

		state = "MOVING";

		normal = new Animation(10, true, Images.Enemies.Leever.LEEVER_BLUE_1, Images.Enemies.Leever.LEEVER_BLUE_2);
		burrow = new Animation(10, false, Images.Enemies.Leever.LEEVER_BLUE_MIDBURROW, Images.Enemies.Leever.LEEVER_BLUE_BURROW_1, Images.Enemies.Leever.LEEVER_BLUE_BURROW_2);
		emerge = new Animation(10, false, Images.Enemies.Leever.LEEVER_BLUE_BURROW_2, Images.Enemies.Leever.LEEVER_BLUE_BURROW_1, Images.Enemies.Leever.LEEVER_BLUE_MIDBURROW);
		tunnel = new Animation(10, true, Images.Enemies.Leever.LEEVER_BLUE_BURROW_1, Images.Enemies.Leever.LEEVER_BLUE_BURROW_2);
	}

	public void update()
	{
		if(getStunTimer() == 0)
		{
			switch(state)
			{
			case "MOVING":
				double[] vector = direction.getVector(moveSpeed);
				velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
				velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

				normal.update();

				if((Math.random() * 100) < 2) direction = Direction.getRandom();
				if((Math.random() * 500) > 498)
				{
					state = "BURROW";
				}
				break;
			case "BURROW":
				burrow.update();
				if(burrow.getIndex() == -1)
				{
					state = "TUNNEL";
					burrow.reset();
				}
				break;
			case "TUNNEL":
				vector = direction.getVector(moveSpeed);
				velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
				velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

				tunnel.update();

				if((Math.random() * 100) < 2) direction = Direction.getRandom();
				if((Math.random() * 500) > 498)
				{
					state = "EMERGE";
				}
				break;
			case "EMERGE":
				emerge.update();
				if(emerge.getIndex() == -1)
				{
					state = "MOVING";
					emerge.reset();
				}
				break;
			}

			if(handleTileCollisions() && movementRefreshTimer == 0)
			{
				movementRefreshTimer = 120;
				direction = Direction.getExcludedRandom(direction);
			}

			if(movementRefreshTimer > 0) movementRefreshTimer--;
		}
		else setStunTimer(getStunTimer() - 1);

		super.update(true);
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			switch(state)
			{
			case "MOVING":
				normal.draw(g2d, drawX, drawY, width, height);
				break;
			case "TUNNEL":
				tunnel.draw(g2d, drawX, drawY, width, height);
				break;
			case "BURROW":
				burrow.draw(g2d, drawX, drawY, width, height);
			case "EMERGE":
				emerge.draw(g2d, drawX, drawY, width, height);
			}
		}

		System.out.println(state);
	}
}

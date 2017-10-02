package components.entity.enemies.overworld;

import components.entity.enemies.Enemy;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Tektite extends Enemy
{
	Animation animation;

	double targetX, targetY;
	final double gravity;

	public Tektite(int x, int y, Room room)
	{
		super(x, y, room, 1, 1, "IDLE", 16, 16);

		velX = 0;
		velY = 0;

		moveSpeed = 0;

		animation = new Animation(10, true, Images.Enemies.Tektite.TEKTITE_1,
				Images.Enemies.Tektite.TEKTITE_2);

		gravity = -0.3;
	}

	public void update()
	{
		switch(state)
		{
			case "IDLE":
				animation.update();

				if(Math.random() * 75 >= 74)
				{
					generateTargetTile();

					state = "JUMPING";
					double distance = targetX - x;
					double framesForJump = 40;
					velX = distance / framesForJump;
					velY = framesForJump * gravity / 2.0;
				}
				break;
			case "JUMPING":
				x += velX;
				y += velY;

				velY -= gravity;

				if(Math.abs(targetX - x) < 10 && Math.abs(targetY - y) < 15)
				{
					velX = 0;
					velY = 0;
					x = targetX;
					y = targetY;

					state = "IDLE";
				}
				break;
			default:
				break;
		}

		super.update();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = (int) Math.round(x) - width / 2;
		drawY = (int) Math.round(y) - height / 2;

		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			animation.draw(g2d, drawX, drawY, width, height);
		}
	}

	void generateTargetTile()
	{
		switch((int) (Math.random() * 8))
		{
		case 0:
			targetX = x + room.getWidthOfTile();
			targetY = y + room.getHeightOfTile() * 2;
			break;
		case 1:
			targetX = x + room.getWidthOfTile();
			targetY = y + room.getHeightOfTile() * -2;
			break;
		case 2:
			targetX = x + room.getWidthOfTile() * -1;
			targetY = y + room.getHeightOfTile() * -2;
			break;
		case 3:
			targetX = x + room.getWidthOfTile() * -1;
			targetY = y + room.getHeightOfTile() * 2;
			break;
		case 4:
			targetX = x + room.getWidthOfTile();
			targetY = y + room.getHeightOfTile();
			break;
		case 5:
			targetX = x + room.getWidthOfTile() * -1;
			targetY = y + room.getHeightOfTile();
			break;
		case 6:
			targetX = x + room.getWidthOfTile();
			targetY = y + room.getHeightOfTile() * -1;
			break;
		case 7:
			targetX = x + room.getWidthOfTile() * -1;
			targetY = y + room.getHeightOfTile() * -1;
			break;
		}

		if(targetX <= 0 || targetY <= 0 || targetX >= room.getMapWidth() || targetY >= room.getMapHeight() ||
				room.getTile((int) targetX / room.getWidthOfTile(), (int) targetY / room.getHeightOfTile()).isWater())
			generateTargetTile();
	}
}

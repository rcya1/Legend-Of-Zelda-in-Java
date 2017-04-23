package components.entity.enemies;

import utility.Animation;
import components.map.rooms.OverWorldRoom;
import utility.Images;

import java.awt.*;

public class Tektite extends Enemy
{
	private final Animation animation;

	private int targetColumn;
	private int targetRow;

	private double gravity;

	public Tektite(int x, int y, OverWorldRoom room)
	{
		this.x = x;
		this.y = y;

		this.room = room;

		velX = 0;
		velY = 0;

		width = 16;
		height = 16;

		moveSpeed = 0;
		state = "IDLE";

		animation = new Animation(10, true, Images.Enemies.Tektite.TEKTITE_1,
				Images.Enemies.Tektite.TEKTITE_2);

		health = 2;
		damage = 4;

		gravity = -0.3;
	}

	public void update()
	{
		if(getStunTimer() == 0)
		{
			switch(state)
			{
			case "IDLE":
				animation.update();

				if(Math.random() * 75 >= 74)
				{
					generateTargetTile();

					state = "JUMPING";
					double distance = (targetColumn * width) - x;
					double framesForJump = 40;
					velX = distance / framesForJump;
					velY = framesForJump * gravity / 2;
				}
				break;
			case "JUMPING":
				x += velX;
				y += velY;

				velY -= gravity;

				if(Math.abs((targetColumn * room.getWidthOfTile()) - x) < 3 && Math.abs(targetRow * room.getHeightOfTile() - y) < 10)
				{
					velX = 0;
					velY = 0;
					x = targetColumn * room.getWidthOfTile();
					y = targetRow * room.getHeightOfTile();

					state = "IDLE";
				}
				break;
			default:
				break;
			}
		}

		else setStunTimer(getStunTimer() - 1);

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

	private void generateTargetTile()
	{
		switch((int) (Math.random() * 8))
		{
		case 0:
			targetColumn = (int) (x / room.getWidthOfTile()) + 1;
			targetRow = (int) (y / room.getHeightOfTile()) + 2;
			break;
		case 1:
			targetColumn = (int) (x / room.getWidthOfTile()) + 1;
			targetRow = (int) (y / room.getHeightOfTile()) - 2;
			break;
		case 2:
			targetColumn = (int) (x / room.getWidthOfTile()) - 1;
			targetRow = (int) (y / room.getHeightOfTile()) - 2;
			break;
		case 3:
			targetColumn = (int) (x / room.getWidthOfTile()) - 1;
			targetRow = (int) (y / room.getHeightOfTile()) + 2;
			break;
		case 4:
			targetColumn = (int) (x / room.getWidthOfTile()) + 1;
			targetRow = (int) (y / room.getHeightOfTile()) + 1;
			break;
		case 5:
			targetColumn = (int) (x / room.getWidthOfTile()) - 1;
			targetRow = (int) (y / room.getHeightOfTile()) + 1;
			break;
		case 6:
			targetColumn = (int) (x / room.getWidthOfTile()) + 1;
			targetRow = (int) (y / room.getHeightOfTile()) - 1;
			break;
		case 7:
			targetColumn = (int) (x / room.getWidthOfTile()) - 1;
			targetRow = (int) (y / room.getHeightOfTile()) - 1;
			break;
		}

		if(targetColumn <= 0) generateTargetTile();
		if(targetRow <= 0) generateTargetTile();
		if((targetColumn + 1) >= room.getNumOfColumns()) generateTargetTile();
		if((targetRow + 1) >= room.getNumOfRows()) generateTargetTile();
	}
}

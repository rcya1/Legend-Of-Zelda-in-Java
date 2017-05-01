package components.entity.enemies;

import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

public class TektiteBlue extends Tektite
{

	public TektiteBlue(int x, int y, Room room)
	{
		super(x, y, room);

		animation = new Animation(20, true, Images.Enemies.Tektite.TEKTITE_BLUE_1, Images.Enemies.Tektite.TEKTITE_BLUE_2);
	}

	public void update()
	{
		switch(state)
		{
		case "IDLE":
			animation.update();

			if(Math.random() * 300 >= 299)
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

			if(Math.abs((targetColumn * room.getWidthOfTile()) - x) < 3
					&& Math.abs(targetRow * room.getHeightOfTile() - y) < 10)
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

		super.update();
	}
}

package components.entity.enemies.overworld;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

public class MolblinBlue extends Molblin
{
	public MolblinBlue(int x, int y, Direction direction, Room room)
	{
		super(x, y, direction, room);

		this.moveSpeed = 0.5;

		up = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_BLUE_UP,
				Images.Enemies.Molblin.MOLBLIN_BLUE_UP_2);
		right = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_BLUE_RIGHT,
				Images.Enemies.Molblin.MOLBLIN_BLUE_RIGHT_2);
		down = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_BLUE_DOWN,
				Images.Enemies.Molblin.MOLBLIN_BLUE_DOWN_2);
		left = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_BLUE_LEFT,
				Images.Enemies.Molblin.MOLBLIN_BLUE_LEFT_2);

		this.health = 6;
	}
}

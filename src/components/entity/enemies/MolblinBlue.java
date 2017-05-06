package components.entity.enemies;

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

		animation = new Animation(20, true, Images.Enemies.Molblin.MOLBLIN_BLUE_1, Images.Enemies.Molblin.MOLBLIN_BLUE_2);

		this.health = 6;
	}
}

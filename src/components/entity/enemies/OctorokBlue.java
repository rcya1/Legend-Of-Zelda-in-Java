package components.entity.enemies;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

public class OctorokBlue extends Octorok
{
	public OctorokBlue(int x, int y, Direction direction, Room room)
	{
		super(x, y, direction, room);

		this.moveSpeed = 0.5;
		this.health = 4;

		animation = new Animation(20, true, Images.Enemies.Octorok.OCTOROK_BLUE_1, Images.Enemies.Octorok.OCTOROK_BLUE_2);
	}
}

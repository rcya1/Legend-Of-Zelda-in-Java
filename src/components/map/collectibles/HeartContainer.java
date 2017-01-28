package components.map.collectibles;

import components.Room;
import utility.Images;

public class HeartContainer extends Collectible
{
	public HeartContainer(int x, int y, Room room)
	{
		this.x = x;
		this.y = y;

		this.width = 13;
		this.height = 13;

		this.room = room;

		this.bufferedImage = Images.Menu.HEART_CONTAINER;
	}

	public void update()
	{

	}
}

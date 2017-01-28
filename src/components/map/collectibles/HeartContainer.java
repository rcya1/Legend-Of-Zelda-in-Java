package components.map.collectibles;

import components.Room;
import utility.Images;

public class HeartContainer extends Collectible
{
	public HeartContainer(int x, int y, Room overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 13;
		this.height = 13;

		this.room = overWorld;

		this.bufferedImage = Images.Menu.HEART_CONTAINER;
	}

	public void update()
	{

	}
}

package components.map.collectibles;

import components.map.MapItem;
import components.OverWorld;
import utility.Images;

public class HeartContainer extends Collectible
{
	public HeartContainer(int x, int y, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 13;
		this.height = 13;

		this.overWorld = overWorld;

		this.bufferedImage = Images.Menu.HEART_CONTAINER;
	}

	public void update()
	{

	}
}

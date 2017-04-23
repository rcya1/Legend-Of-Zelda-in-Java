package components.items.collectibles;

import components.map.rooms.OverWorldRoom;
import components.entity.Link;
import utility.Images;

public class HeartContainer extends Collectible
{
	public HeartContainer(int x, int y, OverWorldRoom room)
	{
		this.x = x;
		this.y = y;

		this.width = 13;
		this.height = 13;

		this.room = room;

		this.bufferedImage = Images.Menu.Hearts.HEART_CONTAINER;
	}

	public void update()
	{

	}

	public boolean action(Link link)
	{
		if(link.getHealthContainers() + 1 <= link.getMaxHealthContainers())
		{
			link.addHealthContainers(1);

			return true;
		}

		return false;
	}
}

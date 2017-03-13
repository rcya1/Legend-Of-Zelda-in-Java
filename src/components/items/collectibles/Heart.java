package components.items.collectibles;

import components.map.rooms.RoomBase;
import components.entity.Link;
import utility.Images;

public class Heart extends Collectible
{
	private final int restoreValue;

	public Heart(int x, int y, RoomBase room)
	{
		this.x = x;
		this.y = y;

		this.width = 8;
		this.height = 8;

		restoreValue = 8;

		this.room = room;

		this.bufferedImage = Images.Menu.HEART_FULL;
	}

	public void update()
	{

	}

	public boolean action(Link link)
	{
		if(link.getHealth() != link.getHealthContainers() * 8)
		{
			if(link.getHealth() + restoreValue > link.getHealthContainers() * 8)
			{
				link.addHealth(link.getHealthContainers() * 8 - link.getHealth());
			}
			else
			{
				link.addHealth(restoreValue);
			}

			return true;
		}

		return false;
	}
}

package components.items.collectibles;

import components.map.rooms.Room;
import components.entity.Link;
import utility.Images;

public class Heart extends Collectible
{
	private final int restoreValue;

	public Heart(int x, int y, Room room)
	{
		this.x = x;
		this.y = y;

		this.width = 8;
		this.height = 8;

		restoreValue = 2;

		this.room = room;

		this.bufferedImage = Images.Menu.Hearts.HEART_FULL;
	}

	public void update()
	{

	}

	public boolean action(Link link)
	{
		if(link.getHealth() != link.getHealthContainers() * 2)
		{
			if(link.getHealth() + restoreValue > link.getHealthContainers() * 2)
			{
				link.addHealth(link.getHealthContainers() * 2 - link.getHealth());
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

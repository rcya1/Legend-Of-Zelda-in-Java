package components.items.collectibles;

import components.entity.Link;
import components.map.rooms.Room;
import utility.Images;

//Collectible that increases health
public class Heart extends Collectible
{
	private final int restoreValue;      //How much the heart restores

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
		//If link's health is not maxed
		if(link.getHealth() != link.getHealthContainers() * 2)
		{
			//If adding the restore value makes the health go over the number of containers,
			//then add up to the max amount of health
			if(link.getHealth() + restoreValue > link.getHealthContainers() * 2)
			{
				link.addHealth(link.getHealthContainers() * 2 - link.getHealth());
			}
			//Otherwise, just add the restore value
			else
			{
				link.addHealth(restoreValue);
			}

			//Remove the heart
			return true;
		}

		//The object was not consumed, do not remove the heart
		return false;
	}
}

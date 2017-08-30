package components.items.collectibles;

import components.entity.Link;
import components.map.rooms.WorldRoom;
import utility.Images;

//Collectible that increases max heart count
public class HeartContainer extends Collectible
{
	public HeartContainer(int x, int y, WorldRoom room)
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
		//Make sure Link's num of heart containers does not exceed max
		if(link.getHealthContainers() + 1 <= link.getMaxHealthContainers())
		{
			link.addHealthContainers(1);

			//Remove the container
			return true;
		}

		//Container was not consumed, do not remove it
		return false;
	}
}

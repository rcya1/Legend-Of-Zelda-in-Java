package components.items.player;

import components.entity.Link;
import components.items.weapons.Arrow;
import utility.Data;

public class BowItem extends Item
{
	public BowItem()
	{

	}

	public void action(Link link)
	{
		if(Data.arrowLevel == 1) link.setArrow(new Arrow((int) link.getX(), (int) link.getY(),
				link.getDirection(), link.getRoom()));
		else if(Data.arrowLevel == 2)
		{
			//Placeholder
		}
	}
}

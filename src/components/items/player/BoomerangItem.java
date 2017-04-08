package components.items.player;

import components.entity.Link;
import components.items.weapons.Boomerang;

public class BoomerangItem extends Item
{
	public BoomerangItem()
	{

	}

	public void action(Link link)
	{
		link.setBoomerang(new Boomerang((int) link.getX(), (int) link.getY(),
				link.getDirection(), link.getRoom()));
	}
}

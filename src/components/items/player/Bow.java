package components.items.player;

import components.entity.Link;
import components.items.weapons.Arrow;

public class Bow extends Item
{
	public Bow()
	{

	}

	public void update()
	{

	}

	public void action(Link link)
	{
		link.setArrow(new Arrow((int) link.getX(), (int) link.getY(),
				link.getDirection(), link.getRoom()));
	}
}

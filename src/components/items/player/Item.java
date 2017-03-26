package components.items.player;

import components.entity.Link;
import components.items.MapItem;

public abstract class Item extends MapItem
{
	public abstract void action(Link link);
}

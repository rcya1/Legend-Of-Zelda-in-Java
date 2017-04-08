package components.items.collectibles;

import components.map.rooms.Room;
import components.entity.Link;
import components.items.MapItem;

public abstract class Collectible extends MapItem
{
	public abstract boolean action(Link link);

	public static Collectible parse(String collectible, int x, int y, Room room)
	{
		switch(collectible)
		{
			case "SWORD":
				return new Sword(x, y, room);
			default:
				return null;
		}
	}
}

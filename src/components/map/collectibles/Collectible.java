package components.map.collectibles;

import components.RoomBase;
import components.entity.Link;
import components.map.MapItem;

public abstract class Collectible extends MapItem
{
	public abstract boolean action(Link link);

	public static Collectible parse(String collectible, int x, int y, RoomBase room)
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

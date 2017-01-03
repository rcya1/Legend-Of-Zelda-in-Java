package utility;

import components.OverWorld;
import components.tiles.WarpTile;
import entity.Direction;
import entity.enemies.Enemy;
import entity.enemies.Octorok;

public class MapHelper
{
	public static Enemy parseEnemy(String string, int x, int y, OverWorld overWorld)
	{
		switch(string)
		{
		case "A":
			return new Octorok(x + overWorld.getWidthOfTile() / 2, y + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(), overWorld);
		default:
			return null;
		}
	}

	public static WarpTile parseWarpTile(String id, int x, int y, OverWorld overWorld)
	{
		return new WarpTile(x, y, overWorld.getWidthOfTile(), overWorld.getHeightOfTile(),
				id.charAt(0));
	}
}

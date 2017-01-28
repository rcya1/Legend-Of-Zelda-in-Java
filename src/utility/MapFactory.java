package utility;

import components.OverWorld;
import components.entity.Direction;
import components.entity.enemies.Enemy;
import components.entity.enemies.Octorok;
import components.map.WarpTile;

public class MapFactory
{
	private final OverWorld overWorld;

	public MapFactory(OverWorld overWorld)
	{
		this.overWorld = overWorld;
	}

	public Enemy buildEnemy(String string, int x, int y)
	{
		switch(string)
		{
		case "A":
			return new Octorok(x + overWorld.getWidthOfTile() / 2, y + overWorld.getHeightOfTile() / 2,
					Direction.getRandom(), overWorld.getCurrentRoom());
		default:
			return null;
		}
	}

	public WarpTile buildWarpTile(String id, int x, int y)
	{
		return new WarpTile(x, y, overWorld.getWidthOfTile(), overWorld.getHeightOfTile(),
				id.charAt(0));
	}
}

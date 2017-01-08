package utility;

import components.OverWorld;
import components.tiles.WarpTile;
import entity.Direction;
import entity.enemies.Enemy;
import entity.enemies.Octorok;

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
					Direction.getRandom(), overWorld);
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

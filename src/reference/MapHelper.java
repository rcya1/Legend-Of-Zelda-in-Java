package reference;

import components.OverWorld;
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
			return new Octorok(x + overWorld.getWidthOfTile() / 2, y + overWorld.getHeightOfTile() / 2, Direction.getRandom(), overWorld);
		default:
			return null;
		}
	}
}

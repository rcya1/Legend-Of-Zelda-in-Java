package reference;

import entity.Direction;
import entity.enemies.Enemy;
import entity.enemies.Octorok;
import map.Tile;
import map.TileMap;

public class MapHelper
{
	public static Enemy parseEnemy(String string, int x, int y, TileMap tileMap)
	{
		switch(string)
		{
		case "A":
			return new Octorok(x + tileMap.getWidthOfTile() / 2, y + tileMap.getHeightOfTile() / 2, Direction.getRandom(), tileMap);
		default:
			return null;
		}
	}
}

package reference;

import entity.Direction;
import entity.enemies.Enemy;
import entity.enemies.Octorok;
import map.Tile;
import map.TileMap;

public class MapHelper
{
	public static boolean checkCollisionWithTileMap(int x, int y, TileMap tileMap, int entityWidth, int entityHeight)
	{
		boolean collisionFlag = false;

		int leftColumn = (x - entityWidth / 2) / tileMap.getWidthOfTile();
		int rightColumn = (x + entityWidth / 2) / tileMap.getWidthOfTile();
		int topRow = (y - entityHeight / 2) / tileMap.getHeightOfTile();
		int bottomRow = (y + entityHeight / 2) / tileMap.getHeightOfTile();

		if(leftColumn < 0) leftColumn = 0;
		if(rightColumn > tileMap.getColumns() - 1) rightColumn = tileMap.getColumns() - 1;
		if(topRow < 0) topRow = 0;
		if(bottomRow > tileMap.getRows() - 1) bottomRow = tileMap.getRows() - 1;

		for(int i = leftColumn; i <= rightColumn; i++)
		{
			for(int j = topRow; j <= bottomRow; j++)
			{
				Tile tile = tileMap.getTile(i, j);
				if(!tile.isPassible())
				{
					collisionFlag = true;
				}
			}
		}
		return collisionFlag;
	}

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

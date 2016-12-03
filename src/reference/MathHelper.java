package reference;

import map.Tile;
import map.TileMap;

public class MathHelper
{
	public static int sign(int number)
	{
		if(number > 0) return 1;
		else if(number < 0) return -1;
		else return 0;
	}

	public static boolean checkCollisionWithTileMap(int x, int y, TileMap tileMap, int playerWidth, int playerHeight)
	{
		boolean collisionFlag = false;

		int leftColumn = (x - playerWidth / 2) / tileMap.getWidthOfTile();
		int rightColumn = (x + playerWidth / 2) / tileMap.getWidthOfTile();
		int topRow = (y - playerHeight / 2) / tileMap.getHeightOfTile();
		int bottomRow = (y + playerHeight / 2) / tileMap.getHeightOfTile();

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
}

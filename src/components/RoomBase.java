package components;

import components.map.collectibles.Collectible;

public interface RoomBase
{
	int getMapWidth();
	int getMapHeight();

	int getNumOfColumns();
	int getNumOfRows();

	int getWidthOfTile();
	int getHeightOfTile();

	int getId();

	Tile getTile(int column, int row);

	void addCollectible(Collectible collectible);

	RoomMetadata getMetadata();
}

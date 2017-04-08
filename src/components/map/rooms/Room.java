package components.map.rooms;

import components.entity.Link;
import utility.Tile;
import components.items.collectibles.Collectible;

public interface Room
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

	Link getLink();
}

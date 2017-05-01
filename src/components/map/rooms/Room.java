package components.map.rooms;

import components.entity.Link;
import components.entity.enemies.Enemy;
import components.items.MapItem;
import utility.Tile;
import components.items.collectibles.Collectible;

import java.util.ArrayList;

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

	ArrayList<Enemy> getEnemies();

	ArrayList<MapItem> getMapItems();

	void addCollectible(Collectible collectible);

	RoomMetadata getMetadata();

	Link getLink();
}

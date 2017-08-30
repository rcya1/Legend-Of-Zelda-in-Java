package components.map.rooms;

import components.MapItem;
import components.entity.Link;
import components.entity.enemies.Enemy;
import components.items.collectibles.Collectible;
import utility.Tile;

import java.util.ArrayList;

public interface Room
{
	int getMapWidth();      //Returns the width of the room in pixels
	int getMapHeight();     //Returns the height of the room in pixels

	int getNumOfColumns();  //Returns the num of cols in the room
	int getNumOfRows();     //Returns the num of rows in the room

	int getWidthOfTile();   //Returns the width of a tile
	int getHeightOfTile();  //Returns the height of a tile

	//Returns the col/row of the room
	int getId();

	//Returns the tile in a room
	Tile getTile(int column, int row);

	//Returns all of the enemies in a room
	ArrayList<Enemy> getEnemies();

	//Returns all of the items in a room
	ArrayList<MapItem> getMapItems();

	//Adds a collectible to a room
	void addCollectible(Collectible collectible);

	//Returns the metadata object
	RoomMetadata getMetadata();

	//Returns the player in the room
	Link getLink();
}

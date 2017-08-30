package components.map.rooms;

import components.entity.enemies.Enemy;
import components.map.WarpTile;
import components.map.World;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

//An object that stores the information about a room
public class RoomMetadata
{
	private final int id;                  //The col/row of the room
	private final World world;     //The world the room exists in

	private ArrayList<Enemy> enemies;      //The enemies in the room
	private ArrayList<WarpTile> warpTiles; //The warps in the room
	private ArrayList<String[]> items;     //The items in the room

	private String roomType;               //The type of the room (NORMAL)
	private String music;                  //The music played in a room

	private String caveNPC;                //The cave NPC in a room
	private String caveText;               //The cave text in a room

	//Creates metadata from the given id and world
	public RoomMetadata(int id, World world)
	{
		this.id = id;
		this.world = world;

		loadMetadata();
	}

	//Loads metadata from the xml file using the id
	private void loadMetadata()
	{
		//Grabs the overworld's metadata document
		Document metaData = world.getMetadataDocument();

		Element thisRoom = null;
		//Gets all of the rooms and finds the one that matches the id
		NodeList rooms = metaData.getElementsByTagName("ROOM");
		for(int roomIndex = 0; roomIndex < rooms.getLength(); roomIndex++)
		{
			Element room = (Element) rooms.item(roomIndex);
			if(room.getAttribute("id").equals(id / 10 + "-" + id % 10))
			{
				thisRoom = room;
				break;
			}
		}

		if(thisRoom != null)
		{
			//Grabs basic data
			roomType = thisRoom.getElementsByTagName("ROOM-TYPE").item(0).getTextContent();
			music = thisRoom.getElementsByTagName("MUSIC").item(0).getTextContent();

			enemies = new ArrayList<>();
			warpTiles = new ArrayList<>();
			items = new ArrayList<>();

			//Goes through all of the enemies and constructs each one using the world map helper
			Element enemiesElement = (Element) thisRoom.getElementsByTagName("ENEMIES").item(0);
			NodeList enemiesList = enemiesElement.getElementsByTagName("ENEMY");
			for(int enemyIndex = 0; enemyIndex < enemiesList.getLength(); enemyIndex++)
			{
				Element enemy = (Element) enemiesList.item(enemyIndex);
				String type = enemy.getElementsByTagName("TYPE").item(0).getTextContent();
				int col = Integer.parseInt(enemy.getElementsByTagName("COL").item(0).getTextContent());
				int row = Integer.parseInt(enemy.getElementsByTagName("ROW").item(0).getTextContent());
				enemies.add(world.getMapHelper().buildEnemy(type, col, row));
			}

			//Goes through all of the warps and constructs each one using the world map helper
			Element warpsElement = (Element) thisRoom.getElementsByTagName("WARPS").item(0);
			NodeList warpsList = warpsElement.getElementsByTagName("WARP");
			for(int warpIndex = 0; warpIndex < warpsList.getLength(); warpIndex++)
			{
				Element warp = (Element) warpsList.item(warpIndex);
				String type = warp.getElementsByTagName("TYPE").item(0).getTextContent();
				String direction = warp.getElementsByTagName("DIRECTION").item(0).getTextContent();

				int col = Integer.parseInt(warp.getElementsByTagName("COL").item(0).getTextContent());
				int row = Integer.parseInt(warp.getElementsByTagName("ROW").item(0).getTextContent());

				int destCol = Integer.parseInt(warp.getElementsByTagName("DEST-COL").item(0).getTextContent());
				int destRow = Integer.parseInt(warp.getElementsByTagName("DEST-ROW").item(0).getTextContent());

				warpTiles.add(world.getMapHelper().buildWarpTile(col, row,
						destCol, destRow, type, direction));
			}

			//Goes through the hidden room and sets its data
			if(thisRoom.getElementsByTagName("HIDDEN-ROOM").getLength() > 0)
			{
				Element hiddenRoom = (Element) thisRoom.getElementsByTagName("HIDDEN-ROOM").item(0);
				caveNPC = hiddenRoom.getElementsByTagName("NPC").item(0).getTextContent();
				caveText = hiddenRoom.getElementsByTagName("TEXT").item(0).getTextContent();

				NodeList itemNodes = thisRoom.getElementsByTagName("ITEMS");
				for(int itemIndex = 0; itemIndex < itemNodes.getLength(); itemIndex++)
				{
					Element item = (Element) itemNodes.item(itemIndex);
					items.add(new String[]
							{
									item.getElementsByTagName("COLLECTIBLE").item(0).getTextContent(),
									item.getElementsByTagName("COST").item(0).getTextContent()
							});
				}
			}
		}
	}

	public ArrayList<Enemy> getEnemies()
	{
		return enemies;
	}

	public ArrayList<WarpTile> getWarpTiles()
	{
		return warpTiles;
	}

	public ArrayList<String[]> getItems()
	{
		return items;
	}

	public String getCaveNPC()
	{
		return caveNPC;
	}

	public String getCaveText()
	{
		return caveText;
	}

	public String getRoomType()
	{
		return roomType;
	}

	public String getMusic()
	{
		return music;
	}
}

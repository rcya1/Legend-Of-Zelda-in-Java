package components.map.rooms;

import components.entity.enemies.Enemy;
import components.map.OverWorld;
import components.map.WarpTile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class RoomMetadata
{
	private final int id;
	private final OverWorld overWorld;

	private ArrayList<Enemy> enemies;
	private ArrayList<WarpTile> warpTiles;
	private ArrayList<String[]> items;

	private String roomType;
	private String music;

	private String caveNPC;
	private String caveText;

	public RoomMetadata(int id, OverWorld overWorld)
	{
		this.id = id;
		this.overWorld = overWorld;

		loadMetadata();
	}

	private void loadMetadata()
	{
		Document metaData = overWorld.getMetadataDocument();

		Element thisRoom = null;

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
			roomType = thisRoom.getElementsByTagName("ROOM-TYPE").item(0).getTextContent();
			music = thisRoom.getElementsByTagName("MUSIC").item(0).getTextContent();

			enemies = new ArrayList<>();
			warpTiles = new ArrayList<>();
			items = new ArrayList<>();

			Element enemiesElement = (Element) thisRoom.getElementsByTagName("ENEMIES").item(0);
			NodeList enemiesList = enemiesElement.getElementsByTagName("ENEMY");
			for(int enemyIndex = 0; enemyIndex < enemiesList.getLength(); enemyIndex++)
			{
				Element enemy = (Element) enemiesList.item(enemyIndex);
				String type = enemy.getElementsByTagName("TYPE").item(0).getTextContent();
				int col = Integer.parseInt(enemy.getElementsByTagName("COL").item(0).getTextContent());
				int row = Integer.parseInt(enemy.getElementsByTagName("ROW").item(0).getTextContent());
				enemies.add(overWorld.getMapFactory().buildEnemy(type, col, row));
			}

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

				warpTiles.add(overWorld.getMapFactory().buildWarpTile(col, row,
						destCol, destRow, type, direction));
			}

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

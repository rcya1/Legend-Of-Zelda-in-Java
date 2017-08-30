package components.map;

import components.entity.Direction;
import components.entity.Link;
import components.map.rooms.RoomMetadata;
import components.map.rooms.SecretRoom;
import components.map.rooms.WorldRoom;
import org.w3c.dom.Document;
import utility.MapHelper;
import utility.SoundManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

//A world that contains a maze of rooms that must move around and be loaded from tiles
public class World
{
	private WorldRoom currentRoom;                //The room Link is currently in
	private WorldRoom loadingRoom;                //The room that Link is entering

	private ArrayList<WorldRoom> roomBuffer;      //The last 5 rooms that Link has gone into, to store data

	private final Link link;                      //Player Object

	private final int widthOfTile, heightOfTile;  //Dimensions of a tile

	private final MapHelper mapHelper;            //Object that constructs the world and create enemies
	private Document metadataDocument;            //XML document that stores the metadata

	//Creates a world from given parameters
	public World(int startingRoom, String tileMapFilePath, String metadataFilePath, int columns, int rows)
	{
		link = new Link(this);

		widthOfTile = 16;
		heightOfTile = 16;

		mapHelper = new MapHelper(this, tileMapFilePath, columns, rows);
		loadMetadata(metadataFilePath);

		currentRoom = new WorldRoom(startingRoom, this, mapHelper);
		currentRoom.setRoomMetadata(new RoomMetadata(startingRoom, this));

		loadingRoom = null;

		roomBuffer = new ArrayList<>(Arrays.asList(new WorldRoom[5]));

		//Set the current song
		if(!SoundManager.OVERWORLD.isPlaying()) SoundManager.OVERWORLD.loop();
	}

	//Loads the metadata from the xml file
	private void loadMetadata(String filePath)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder builder = factory.newDocumentBuilder();
			metadataDocument = builder.parse(getClass().getResourceAsStream(filePath));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		metadataDocument.getDocumentElement().normalize();
	}

	//Updates Link and the rooms
	public void update()
	{
		//Update the current room
		currentRoom.update();

		//If there is a new loading room
		if(loadingRoom != null)
		{
			//Update the coords of the loading room
			loadingRoom.updateDrawCoordinates();

			//If the loading room has locked into place, then stop the room and set the room to the new room
			if(loadingRoom.getDrawCoordinates()[0] % 256 == 0 &&
					loadingRoom.getDrawCoordinates()[1] % 176 == 0)
			{
				currentRoom.setDrawVector(0, 0);
				loadingRoom.setDrawVector(0, 0);
				currentRoom = loadingRoom;
				loadingRoom = null;
			}
		}

		link.update();

		//Check for the screen transitions
		if(!(link.getRoom() instanceof SecretRoom) && !link.getState().equals("TRANSITION") && loadingRoom == null)
		{
			//If link is leaving through the left side
			if(link.getX() <= widthOfTile && link.getDirection() == Direction.LEFT)
			{
				loadNewRoom(new int[] {4, 0}, currentRoom.getId() - 10,
						new int[] {-currentRoom.getMapWidth(), 0});
			}

			//If Link is leaving through the right side
			if(link.getX() >= currentRoom.getMapWidth() - widthOfTile && link.getDirection() == Direction.RIGHT)
			{
				loadNewRoom(new int[] {-4, 0}, currentRoom.getId() + 10,
						new int[] {currentRoom.getMapWidth(), 0});
			}

			//If Link is leaving through the top side
			if(link.getY() <= heightOfTile && link.getDirection() == Direction.UP)
			{
				loadNewRoom(new int[] {0, 4}, currentRoom.getId() - 1,
						new int[] {0, -currentRoom.getMapHeight()});
			}

			//If Link is leaving through the bottom side
			if(link.getY() >= currentRoom.getMapHeight() - heightOfTile && link.getDirection() == Direction.DOWN)
			{
				loadNewRoom(new int[] {0, -4}, currentRoom.getId() + 1,
						new int[] {0, currentRoom.getMapHeight()});
			}
		}
	}

	//Loads a new room from another direction
	private void loadNewRoom(int[] transitionVector, int loadingRoomID, int[] loadingRoomLocation)
	{
		//Saves the current room's data so that it can be accessed if returned to
		saveRoom();

		//Set Link's transition vector so that Link moves along with the room
		link.setTransitionVector(transitionVector[0], transitionVector[1]);

		//Check if the new room matches the id of any rooms in the stored rooms
		int roomFoundIndex = -1;
		for(int i = 0; i < 5; i++)
		{
			if(roomBuffer.get(i) != null)
			{
				if(roomBuffer.get(i).getId() == loadingRoomID)
				{
					roomFoundIndex = i;
					break;
				}
			}
		}

		//If there is no room found, then create a new room and set it as the loading room
		if(roomFoundIndex == -1)
		{
			loadingRoom = new WorldRoom(loadingRoomID, this, mapHelper);
			loadingRoom.setRoomMetadata(new RoomMetadata(loadingRoomID, this));
		}
		//If there is one found, then set it as the loading room
		else
		{
			loadingRoom = roomBuffer.get(roomFoundIndex);
		}

		//Set the coordinates/vectors so that the loading room moves
		loadingRoom.setDrawCoordinates(loadingRoomLocation[0], loadingRoomLocation[1]);
		currentRoom.setDrawVector(transitionVector[0], transitionVector[1]);
		loadingRoom.setDrawVector(transitionVector[0], transitionVector[1]);

		currentRoom.updateDrawCoordinates();
		loadingRoom.updateDrawCoordinates();
	}

	//Saves the room into an array
	private void saveRoom()
	{
		roomBuffer.add(0, currentRoom);
		if(roomBuffer.size() > 5) roomBuffer.remove(4);
	}

	public void draw(Graphics2D g2d)
	{
		currentRoom.draw(g2d);
		if(loadingRoom != null)
		{
			loadingRoom.draw(g2d);
		}

		link.draw(g2d);
	}

	public WorldRoom getCurrentRoom()
	{
		return currentRoom;
	}

	public WorldRoom getLoadingRoom()
	{
		return loadingRoom;
	}

	public Link getLink()
	{
		return link;
	}

	public int getWidthOfTile()
	{
		return widthOfTile;
	}

	public int getHeightOfTile()
	{
		return heightOfTile;
	}

	public Document getMetadataDocument()
	{
		return metadataDocument;
	}

	public MapHelper getMapHelper()
	{
		return mapHelper;
	}

	//Returns if the world is currently transitioning between rooms
	public boolean isMoving()
	{
		return (currentRoom.getDrawVelocity()[0] != 0 && currentRoom.getDrawVelocity()[1] != 0)
				&& (loadingRoom != null);
	}
}

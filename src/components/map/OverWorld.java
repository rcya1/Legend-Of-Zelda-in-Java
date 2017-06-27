package components.map;

import components.map.rooms.OverWorldRoom;
import components.map.rooms.RoomMetadata;
import components.map.rooms.SecretRoom;
import components.entity.Direction;
import components.entity.Link;
import org.w3c.dom.Document;
import utility.MapFactory;
import utility.SoundPlayer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OverWorld
{
	private OverWorldRoom currentRoom;
	private OverWorldRoom loadingRoom;

	private ArrayList<OverWorldRoom> roomBuffer;

	private final Link link;

	private final int widthOfTile;
	private final int heightOfTile;

	private final MapFactory mapFactory;

	private Document metadataDocument;

	public OverWorld(int startingRoom, String tileMapFilePath, String metadataFilePath,
			int columns, int rows)
	{
		link = new Link(this);

		widthOfTile = 16;
		heightOfTile = 16;

		mapFactory = new MapFactory(this, tileMapFilePath, columns, rows);
		loadMetadata(metadataFilePath);

		currentRoom = new OverWorldRoom(startingRoom, this, mapFactory);
		currentRoom.setRoomMetadata(new RoomMetadata(startingRoom, this));

		loadingRoom = null;

		roomBuffer = new ArrayList<>(Arrays.asList(new OverWorldRoom[5]));

		if(!SoundPlayer.OVERWORLD.isPlaying()) SoundPlayer.OVERWORLD.loop();
	}

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

	public void update()
	{
		currentRoom.update();
		if(loadingRoom != null)
		{
			loadingRoom.updateDrawCoordinates();
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

		if(!(link.getRoom() instanceof SecretRoom))
		{
			if(link.getX() <= widthOfTile && link.getDirection() == Direction.LEFT &&
					!link.getState().equals("TRANSITION") && loadingRoom == null)
			{
				loadNewRoom(new int[] {4, 0}, currentRoom.getId() - 10,
						new int[] {-currentRoom.getMapWidth(), 0});
			}

			if(link.getX() >= currentRoom.getMapWidth() - widthOfTile &&
					link.getDirection() == Direction.RIGHT &&
					!link.getState().equals("TRANSITION") && loadingRoom == null)
			{
				loadNewRoom(new int[] {-4, 0}, currentRoom.getId() + 10,
						new int[] {currentRoom.getMapWidth(), 0});
			}

			if(link.getY() <= heightOfTile / 2 && link.getDirection() == Direction.UP &&
					!link.getState().equals("TRANSITION") && loadingRoom == null)
			{
				loadNewRoom(new int[] {0, 4}, currentRoom.getId() - 1,
						new int[] {0, -currentRoom.getMapHeight()});
			}

			if(link.getY() >= currentRoom.getMapHeight() - heightOfTile &&
					link.getDirection() == Direction.DOWN &&
					!link.getState().equals("TRANSITION") && loadingRoom == null)
			{
				loadNewRoom(new int[] {0, -4}, currentRoom.getId() + 1,
						new int[] {0, currentRoom.getMapHeight()});
			}
		}
	}

	private void loadNewRoom(int[] transitionVector, int loadingRoomID, int[] loadingRoomLocation)
	{
		saveRoom();

		link.setTransitionVector(transitionVector[0], transitionVector[1]);

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
		if(roomFoundIndex == -1)
		{
			loadingRoom = new OverWorldRoom(loadingRoomID, this, mapFactory);
			loadingRoom.setRoomMetadata(new RoomMetadata(loadingRoomID, this));
		}
		else
		{
			loadingRoom = roomBuffer.get(roomFoundIndex);
		}


		loadingRoom.setDrawCoordinates(loadingRoomLocation[0], loadingRoomLocation[1]);
		currentRoom.setDrawVector(transitionVector[0], transitionVector[1]);
		loadingRoom.setDrawVector(transitionVector[0], transitionVector[1]);

		currentRoom.updateDrawCoordinates();
		loadingRoom.updateDrawCoordinates();
	}

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

	public OverWorldRoom getCurrentRoom()
	{
		return currentRoom;
	}

	public OverWorldRoom getLoadingRoom()
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

	public MapFactory getMapFactory()
	{
		return mapFactory;
	}

	public boolean isMoving()
	{
		return (currentRoom.getDrawVector()[0] != 0 && currentRoom.getDrawVector()[1] != 0)
				&& (loadingRoom != null);
	}
}

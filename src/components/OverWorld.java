package components;

import components.entity.Direction;
import components.entity.Link;
import org.w3c.dom.Document;
import utility.MapFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class OverWorld
{
	private Room currentRoom;
	private Room loadingRoom;

	private final Link link;

	private final int widthOfTile;
	private final int heightOfTile;

	private int drawX;
	private int drawY;

	private int drawVelX;
	private int drawVelY;

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

		currentRoom = new Room(startingRoom, this, mapFactory);
		currentRoom.setRoomMetadata(new RoomMetadata(startingRoom, this));

		loadingRoom = null;
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

		if(link.getX() <= widthOfTile &&
				link.getDirection() == Direction.LEFT &&
				!link.getState().equals("TRANSITION"))
		{
			link.setTransitionVector(4, 0);
			loadingRoom = new Room(currentRoom.getId() - 10, this, mapFactory);
			loadingRoom.setRoomMetadata(new RoomMetadata(currentRoom.getId() - 10, this));
			loadingRoom.setDrawCoordinates(-loadingRoom.getMapWidth(), 0);
			currentRoom.setDrawVector(4, 0);
			loadingRoom.setDrawVector(4, 0);

			currentRoom.updateDrawCoordinates();
			loadingRoom.updateDrawCoordinates();
		}

		if(link.getX() >= currentRoom.getMapWidth() - widthOfTile &&
				link.getDirection() == Direction.RIGHT &&
				!link.getState().equals("TRANSITION"))
		{
			link.setTransitionVector(-4, 0);
			loadingRoom = new Room(currentRoom.getId() + 10, this, mapFactory);
			loadingRoom.setRoomMetadata(new RoomMetadata(currentRoom.getId() + 10, this));
			loadingRoom.setDrawCoordinates(loadingRoom.getMapWidth(), 0);
			currentRoom.setDrawVector(-4, 0);
			loadingRoom.setDrawVector(-4, 0);

			currentRoom.updateDrawCoordinates();
			loadingRoom.updateDrawCoordinates();
		}
		if(link.getY() <= heightOfTile / 2 &&
				link.getDirection() == Direction.UP &&
				!link.getState().equals("TRANSITION"))
		{
			link.setTransitionVector(0, 4);
			loadingRoom = new Room(currentRoom.getId() - 1, this, mapFactory);
			loadingRoom.setRoomMetadata(new RoomMetadata(currentRoom.getId() - 1, this));
			loadingRoom.setDrawCoordinates(0, -loadingRoom.getMapHeight());
			currentRoom.setDrawVector(0, 4);
			loadingRoom.setDrawVector(0, 4);

			currentRoom.updateDrawCoordinates();
			loadingRoom.updateDrawCoordinates();
		}
		if(link.getY() >= currentRoom.getMapHeight() - heightOfTile &&
				link.getDirection() == Direction.DOWN &&
				!link.getState().equals("TRANSITION"))
		{
			link.setTransitionVector(0, -4);
			loadingRoom = new Room(currentRoom.getId() + 1, this, mapFactory);
			loadingRoom.setRoomMetadata(new RoomMetadata(currentRoom.getId() + 1, this));
			loadingRoom.setDrawCoordinates(0, loadingRoom.getMapHeight());
			currentRoom.setDrawVector(0, -4);
			loadingRoom.setDrawVector(0, -4);

			currentRoom.updateDrawCoordinates();
			loadingRoom.updateDrawCoordinates();
		}
	}

	public void draw(Graphics2D g2d)
	{
		AffineTransform transform = g2d.getTransform();
		g2d.translate(drawX, drawY);

		currentRoom.draw(g2d);
		if(loadingRoom != null)
		{
			loadingRoom.draw(g2d);
		}

		link.draw(g2d);

		g2d.setTransform(transform);
	}

	public Room getCurrentRoom()
	{
		return currentRoom;
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

	public void updateDrawCoordinates()
	{
		this.drawX += drawVelX;
		this.drawY += drawVelY;
	}

	public void setDrawCoordinates(int drawX, int drawY)
	{
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int[] getDrawCoordinates()
	{
		return new int[] {drawX, drawY};
	}

	public void setDrawVector(int drawVelX, int drawVelY)
	{
		this.drawVelX = drawVelX;
		this.drawVelY = drawVelY;
	}

	Document getMetadataDocument()
	{
		return metadataDocument;
	}

	MapFactory getMapFactory()
	{
		return mapFactory;
	}

	public boolean isMoving()
	{
		return (currentRoom.getDrawVector()[0] != 0 && currentRoom.getDrawVector()[1] != 0);
	}
}

package components.items.collectibles;

import components.entity.Link;
import components.map.rooms.Room;
import utility.Data;
import utility.Images;

import java.awt.*;

//The collectible that gives the player the the sword
public class Sword extends Collectible
{
	public Sword(int x, int y, Room room)
	{
		this.x = x;
		this.y = y;

		this.width = 16;
		this.height = 16;

		this.room = room;

		this.bufferedImage = Images.Link.SWORD;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		if(Data.swordLevel == 0) super.draw(g2d);
	}

	//
	public boolean action(Link link)
	{
		//Allow link yo use the sword
		Data.swordLevel = 1;
		//Set link to the item collect state and play the sound effect
		link.enterItemState(this);
		playClip();

		return true;
	}
}

package components.items.collectibles;

import components.map.rooms.Room;
import components.entity.Link;
import utility.Data;
import utility.Images;
import utility.SoundPlayer;

import java.awt.*;

public class Sword extends Collectible
{
	Sword(int x, int y, Room room)
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

	public boolean action(Link link)
	{
		Data.swordLevel = 1;
		link.enterItemState(this);
		playClip();

		return true;
	}
}

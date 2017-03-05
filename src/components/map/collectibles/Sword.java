package components.map.collectibles;

import components.RoomBase;
import components.entity.Link;
import utility.Data;
import utility.Images;

import java.awt.*;

public class Sword extends Collectible
{
	Sword(int x, int y, RoomBase room)
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
		if(!Data.hasSword) super.draw(g2d);
	}

	public boolean action(Link link)
	{
		Data.hasSword = true;
		return true;
	}
}

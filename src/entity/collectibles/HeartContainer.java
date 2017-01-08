package entity.collectibles;

import components.OverWorld;
import utility.Images;

import java.awt.*;

public class HeartContainer extends Collectible
{
	private final OverWorld overWorld;

	public HeartContainer(int x, int y, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 13;
		this.height = 13;

		this.overWorld = overWorld;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.Menu.HEART_CONTAINER, x - width / 2 - overWorld.getCameraX(),
				y - height / 2 - overWorld.getCameraY(), width, height, null);
	}
}

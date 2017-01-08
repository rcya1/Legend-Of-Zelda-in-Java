package entity.collectibles;

import components.OverWorld;
import utility.Images;

import java.awt.*;

public class Heart extends Collectible
{
	private final OverWorld overWorld;

	private final int restoreValue;

	public Heart(int x, int y, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 8;
		this.height = 8;

		restoreValue = 8;

		this.overWorld = overWorld;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.Menu.HEART_FULL, x - width / 2 - overWorld.getCameraX(),
				y - height / 2 - overWorld.getCameraY(), width, height, null);
	}

	public int getRestoreValue()
	{
		return restoreValue;
	}
}

package components.map.collectibles;

import components.OverWorld;
import utility.Images;

public class Heart extends Collectible
{
	private final int restoreValue;

	public Heart(int x, int y, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 8;
		this.height = 8;

		restoreValue = 8;

		this.overWorld = overWorld;

		this.bufferedImage = Images.Menu.HEART_FULL;
	}

	public void update()
	{

	}

	public int getRestoreValue()
	{
		return restoreValue;
	}
}

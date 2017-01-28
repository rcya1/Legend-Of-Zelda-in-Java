package components.map.collectibles;

import components.Room;
import utility.Images;

public class Heart extends Collectible
{
	private final int restoreValue;

	public Heart(int x, int y, Room overWorld)
	{
		this.x = x;
		this.y = y;

		this.width = 8;
		this.height = 8;

		restoreValue = 8;

		this.room = overWorld;

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

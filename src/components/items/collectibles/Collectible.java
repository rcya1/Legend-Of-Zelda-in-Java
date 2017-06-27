package components.items.collectibles;

import components.map.rooms.Room;
import components.entity.Link;
import components.items.MapItem;
import utility.SoundPlayer;

import java.awt.*;

public abstract class Collectible extends MapItem
{
	public abstract boolean action(Link link);

	public static Collectible parse(String collectible, int x, int y, Room room)
	{
		switch(collectible)
		{
			case "SWORD":
				return new Sword(x, y, room);
			default:
				return null;
		}
	}

	public void draw(int x, int y, Graphics2D g2d)
	{
		g2d.drawImage(bufferedImage, x - width / 2, y - height / 2, width, height, null);
	}

	void playClip()
	{
		SoundPlayer current = SoundPlayer.getPlaying();
		SoundPlayer.stopAll();
		SoundPlayer.ITEM.play();

		Thread thread = new Thread(() ->
		{
			while(!SoundPlayer.ITEM.isFinished());
			SoundPlayer.ITEM.stop();
			if(current != null)
			{
				if(current.isLooping()) current.loop();
				else current.play();
			}
		});
		thread.start();
	}
}

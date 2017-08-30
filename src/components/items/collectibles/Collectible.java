package components.items.collectibles;

import components.MapItem;
import components.entity.Link;
import utility.SoundManager;
import utility.SoundPlayer;

import java.awt.*;

//An item that performs a certain action when collided with
public abstract class Collectible extends MapItem
{
	//The action to be taken when the player collides with the item
	//Returns whether the item should be removed
	public abstract boolean action(Link link);

	//Draws the item
	public void draw(int x, int y, Graphics2D g2d)
	{
		g2d.drawImage(bufferedImage, x - width / 2, y - height / 2, width, height, null);
	}

	//Plays the sound for collecting an item
	void playClip()
	{
		//Get the current song and save it
		SoundPlayer current = SoundManager.getPlaying();
		//Stop all songs
		SoundManager.stopAll();
		//Play the sound effect
		SoundManager.ITEM.play();

		Thread thread = new Thread(() ->
		{
			//Wait for the sound effect to finish
			while(!SoundManager.ITEM.isFinished());
			//Stop the sound effect
			SoundManager.ITEM.stop();
			//Continue the song that was playing before
			if(current != null)
			{
				if(current.isLooping()) current.loop();
				else current.play();
			}
		});
		thread.start();
	}
}

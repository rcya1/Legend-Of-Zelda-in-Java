package utility;

import java.awt.*;
import java.awt.image.BufferedImage;

//Used for displaying images that update over a set amount of time
public class Animation
{
	private int delay;                       //The time between each update of the image

	private int timer;                       //Timer to keep track of when to update the image
	private int index;                       //The index of the current sprite in the sprite sheet

	private final BufferedImage[] images;    //Sprite sheet containing all frames of animation

	private final boolean repeat;            //Whether or not the animation should cycle

	//Animation with a given list of all frames
	public Animation(int delay, boolean repeat, BufferedImage... frames)
	{
		this.delay = delay;
		this.images = frames;

		timer = 0;
		index = 0;

		this.repeat = repeat;
	}

	//Constructor with a given sprite sheet and a given width/height of each sprite
	//Note: Must be linear, one row
	public Animation(int delay, boolean repeat, BufferedImage spriteSheet, int spriteWidth, int spriteHeight)
	{
		this.delay = delay;

		//Cuts apart spriteSheet and sets each frame in sequential order to images
		images = new BufferedImage[spriteSheet.getWidth() / spriteWidth];
		for(int i = 0; i < images.length; i++)
		{
			images[i] = spriteSheet.getSubimage(i * spriteWidth, 0, spriteWidth, spriteHeight);
		}

		timer = 0;
		index = 0;

		this.repeat = repeat;
	}

	public void update()
	{
		//Updates the timer, and if the timer has reached the delay, then move to the next frame
		timer++;
		if(timer > delay)
		{
			timer = 0;
			nextFrame();
		}
	}

	private void nextFrame()
	{
		//If the index is -1, then the animation should not update, since it was set to not loop
		if(index != -1)
		{
			index++;

			//If the index has gone pass the cycle
			if(index >= images.length)
			{
				//If repeating, then reset the cycle, if not, then set the index to -1
				if(repeat) index = 0;
				else index = -1;
			}
		}
	}

	public void draw(Graphics2D g2d, int x, int y, int width, int height)
	{
		g2d.drawImage(images[index], x, y, width, height,null);
	}

	//Resets the current cycle of the animation
	public void reset()
	{
		index = 0;
	}

	//Returns if a non repeating animation has ended
	public boolean isOver()
	{
		return index == -1;
	}

	//Returns the width of the animation image
	public int getWidth()
	{
		return images[index].getWidth();
	}

	//Returns the height of the animation image
	public int getHeight()
	{
		return images[index].getHeight();
	}

	//Changes the delay for the animation
	public void setDelay(int delay)
	{
		this.delay = delay;
		//Make sure the timer does not go above the delay
		if(timer > delay) timer = delay;
	}

	public int getDelay()
	{
		return delay;
	}
}

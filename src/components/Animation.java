package components;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation
{
	private final int speed;
	private final int frameCount;

	private int timer;
	private int index;

	private final BufferedImage[] images;
	private BufferedImage currentImage;

	private final boolean repeat;

	public Animation(int speed, boolean repeat, BufferedImage... frames)
	{
		this.speed = speed;
		this.images = frames;
		frameCount = frames.length;

		timer = 0;
		index = 0;

		this.repeat = repeat;

		currentImage = this.images[0];
		nextFrame();
	}

	//Note: Must be linear, one row
	public Animation(int speed, boolean repeat, BufferedImage spriteSheet, int spriteWidth, int spriteHeight)
	{
		this.speed = speed;

		BufferedImage[] frames = new BufferedImage[spriteSheet.getWidth() / spriteWidth];

		for(int i = 0; i < frames.length; i++)
		{
			frames[i] = spriteSheet.getSubimage(i * spriteWidth, 0, spriteWidth, spriteHeight);
		}

		this.images = frames;
		frameCount = frames.length;

		timer = 0;
		index = 0;

		this.repeat = repeat;

		currentImage = this.images[0];
		nextFrame();
	}

	public void update()
	{
		timer++;
		if(timer > speed)
		{
			timer = 0;
			nextFrame();
		}
	}

	private void nextFrame()
	{
		if(index != -1)
		{
			currentImage = images[index];
			index++;

			if(index >= frameCount)
			{
				if(repeat) index = 0;
				else index = -1;
			}
		}
	}

	int getIndex()
	{
		return index;
	}

	public void draw(Graphics2D g2d, int x, int y, int width, int height)
	{
		g2d.drawImage(currentImage, x, y, width, height,null);
	}

	public int getWidth()
	{
		return currentImage.getWidth();
	}

	public int getHeight()
	{
		return currentImage.getHeight();
	}
}

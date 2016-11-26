package entity;

import reference.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation
{
	private int speed;
	private int frames; //Number of frames

	private int index; //Current index of array
	private int count; //

	private BufferedImage[] images;
	private BufferedImage currentImage;

	public Animation(int speed, BufferedImage... args)
	{
		this.speed = speed;
		images = args;
		frames = args.length;

		index = 0;
		count = 0;
	}

	public void runAnimation()
	{
		index ++;
		if(index > speed)
		{
			index = 0;
			nextFrame();
		}
	}

	private void nextFrame()
	{
		for(int i = 0; i < frames; i++)
		{
			if(count == i)
			{
				currentImage = images[i];
			}
		}
		count++;
		if(count > frames)
		{
			count = 0;
		}
	}

	public void draw(Graphics2D g2d, int x, int y, int width, int height)
	{
		//currentImage = images[count];
		g2d.drawImage(currentImage, x, y, width, height,null);
	}
}

package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

class Animation
{
	private int speed;
	private int frameCount;

	private int timer;
	private int index;

	private BufferedImage[] images;
	private BufferedImage currentImage;

	Animation(int speed, BufferedImage... frames)
	{
		this.speed = speed;
		this.images = frames;
		frameCount = frames.length;

		timer = 0;
		index = 0;

		currentImage = this.images[0];
		nextFrame();
	}

	void update()
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
		currentImage = images[index];
		index++;
		if(index >= frameCount) index = 0;
	}

	void draw(Graphics2D g2d, int x, int y, int width, int height)
	{
		g2d.drawImage(currentImage, x, y, width, height,null);
	}
}

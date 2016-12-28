package entity;

import components.OverWorld;

import java.awt.*;

public class AnimationObject
{
	private OverWorld overWorld;

	private int x;
	private int y;

	private Animation animation;

	public AnimationObject(int x, int y, Animation animation, OverWorld overWorld)
	{
		this.x = x;
		this.y = y;
		this.animation = animation;
		this.overWorld = overWorld;
	}

	public void update()
	{
		animation.update();
	}

	public void draw(Graphics2D g2d)
	{
		animation.draw(g2d, x - overWorld.getCameraX(), y - overWorld.getCameraY(), 16, 16);
	}

	public Animation getAnimation()
	{
		return animation;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}

	public int getWidth()
	{
		return getAnimation().getWidth();
	}

	public int getHeight()
	{
		return getAnimation().getHeight();
	}
}

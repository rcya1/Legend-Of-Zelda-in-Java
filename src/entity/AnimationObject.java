package entity;

import components.OverWorld;

import java.awt.*;

public class AnimationObject
{
	private final OverWorld overWorld;

	private final int x;
	private final int y;

	private final Animation animation;

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

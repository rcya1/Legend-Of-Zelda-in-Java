package components.map;

import components.Animation;
import components.OverWorldRoom;
import components.RoomBase;

import java.awt.*;

public class AnimationObject extends MapItem
{
	private final Animation animation;

	public AnimationObject(int x, int y, Animation animation, RoomBase room)
	{
		this.x = x;
		this.y = y;

		this.width = animation.getWidth();
		this.height = animation.getHeight();

		this.animation = animation;
		this.room = room;
	}

	public void update()
	{
		animation.update();
	}

	public void draw(Graphics2D g2d)
	{
		animation.draw(g2d, x, y, 16, 16);
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

package components.map;

import components.MapItem;
import components.map.rooms.Room;
import utility.Animation;

import java.awt.*;

//An object that is placed in the world and plays an animation
public class AnimationObject extends MapItem
{
	private final Animation animation;

	//Creates an animation from given parameters
	public AnimationObject(int x, int y, Animation animation, Room room)
	{
		this.x = x;
		this.y = y;

		this.width = animation.getWidth();
		this.height = animation.getHeight();

		this.animation = animation;
		this.room = room;
	}

	//Updates the animation
	public void update()
	{
		animation.update();
	}

	//Draws the animation
	public void draw(Graphics2D g2d)
	{
		animation.draw(g2d, x, y, 16, 16);
	}

	//Returns the animation
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

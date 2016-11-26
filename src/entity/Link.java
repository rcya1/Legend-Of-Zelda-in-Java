package entity;

import main.GamePanel;
import reference.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Link
{
	private int x;
	private int y;

	private int velX;
	private int velY;

	private int moveSpeed;

	private int width;
	private int height;

	private BufferedImage[] standingStillSprites;

	private int left;
	private int up;
	private int right;
	private int down;

	private int direction;

	public Link()
	{
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;

		moveSpeed = 2;

		width = 16;
		height = 16;

		standingStillSprites = new BufferedImage[] {Images.LINK_UP, Images.LINK_RIGHT, Images.LINK_DOWN, Images.LINK_LEFT};
	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(standingStillSprites[direction], x - width / 2, y - height / 2, width, height, null);
	}

	public void update()
	{
		/*
		 * Determining direction moved by setting velX to it. However, only basic directions are allowed,
		 * so the direction is only set if the other velocity is 0
		 */
		if(Math.abs(velY) == 0) velX = (right - left) * moveSpeed;
		if(Math.abs(velX) == 0) velY = (down - up) * moveSpeed;

		x += velX;
		y += velY;

		//Set direction for drawing the sprites
		if(velX > 0) direction = 1;
		if(velX < 0) direction = 3;

		if(velY > 0) direction = 2;
		if(velY < 0) direction = 0;

		//Constrain x and y coordinates
		if(x - width / 2 < 0) x = width / 2;
		if(x + width / 2 > GamePanel.WIDTH) x = GamePanel.WIDTH - width / 2;

		if(y - height / 2 < 0) y = height / 2;
		if(y + height / 2 > GamePanel.HEIGHT) y = GamePanel.HEIGHT - height / 2;
	}

	public void setXVelocity(int newValue)
	{
		velX = newValue;
	}

	public void setYVelocity(int newValue)
	{
		velY = newValue;
	}

	public int getMoveSpeed()
	{
		return moveSpeed;
	}

	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_D)
		{
			right = 1;
		}
		if(key == KeyEvent.VK_A)
		{
			left = 1;
		}
		if(key == KeyEvent.VK_W)
		{
			up = 1;
		}
		if(key == KeyEvent.VK_S)
		{
			down = 1;
		}
	}

	public void keyReleased(int key)
	{
		if(key == KeyEvent.VK_D)
		{
			right = 0;
		}
		if(key == KeyEvent.VK_A)
		{
			left = 0;
		}
		if(key == KeyEvent.VK_W)
		{
			up = 0;
		}
		if(key == KeyEvent.VK_S)
		{
			down = 0;
		}
	}
}

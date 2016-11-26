package entity;

import main.GamePanel;
import reference.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

//TODO Fix flashing when program starts. Possibly because of starting values of animation?
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
	private String state;

	private Animation walkUp;
	private Animation walkRight;
	private Animation walkDown;
	private Animation walkLeft;

	public Link()
	{
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;

		moveSpeed = 2;

		width = 16;
		height = 16;

		standingStillSprites = new BufferedImage[] {Images.LINK_UP, Images.LINK_RIGHT, Images.LINK_DOWN, Images.LINK_LEFT};
		walkUp = new Animation(5, Images.LINK_UP, Images.LINK_UP_2);
		walkRight = new Animation(5, Images.LINK_RIGHT, Images.LINK_RIGHT_2);
		walkDown = new Animation(5, Images.LINK_DOWN, Images.LINK_DOWN_2);
		walkLeft = new Animation(5, Images.LINK_LEFT, Images.LINK_LEFT_2);

		state = "IDLE";
	}

	public void draw(Graphics2D g2d)
	{
		switch(state)
		{
		case "IDLE":
			g2d.drawImage(standingStillSprites[direction], x - width / 2, y - height / 2, width, height, null);
			break;
		case "UP":
			walkUp.draw(g2d, x - width / 2, y - height / 2, width, height);
			break;
		case "DOWN":
			walkDown.draw(g2d, x - width / 2, y - height / 2, width, height);
			break;
		case "RIGHT":
			walkRight.draw(g2d, x - width / 2, y - height / 2, width, height);
			break;
		case "LEFT":
			walkLeft.draw(g2d, x - width / 2, y - height / 2, width, height);
			break;
		default:
			g2d.setColor(Color.RED);
			g2d.drawRect(x - width / 2, y - height / 2, width, height);
			break;
		}
	}

	public void update()
	{
		switch(state)
		{
		case "IDLE":
			velX = 0;
			velY = 0;

			if(up == 1) state = "UP";
			if(down == 1) state = "DOWN";
			if(left == 1) state = "LEFT";
			if(right == 1) state = "RIGHT";
			if(up + down + left + right == 0) state = "IDLE";

			break;
		case "UP":
			velX = 0;
			velY = -moveSpeed;
			direction = 0;

			walkUp.runAnimation();

			if(up == 1) state = "UP";
			if(down == 1) state = "DOWN";
			if(left == 1) state = "LEFT";
			if(right == 1) state = "RIGHT";
			if(up + down + left + right == 0) state = "IDLE";

			break;
		case "DOWN":
			velX = 0;
			velY = moveSpeed;
			direction = 2;

			walkDown.runAnimation();

			if(up == 1) state = "UP";
			if(down == 1) state = "DOWN";
			if(left == 1) state = "LEFT";
			if(right == 1) state = "RIGHT";
			if(up + down + left + right == 0) state = "IDLE";

			break;
		case "RIGHT":
			velX = moveSpeed;
			velY = 0;
			direction = 1;

			walkRight.runAnimation();

			if(up == 1) state = "UP";
			if(down == 1) state = "DOWN";
			if(left == 1) state = "LEFT";
			if(right == 1) state = "RIGHT";
			if(up + down + left + right == 0) state = "IDLE";

			break;
		case "LEFT":
			velX = -moveSpeed;
			velY = 0;
			direction = 3;

			walkLeft.runAnimation();

			if(up == 1) state = "UP";
			if(down == 1) state = "DOWN";
			if(left == 1) state = "LEFT";
			if(right == 1) state = "RIGHT";
			if(up + down + left + right == 0) state = "IDLE";

			break;
		default:
			System.out.println(state);
			break;
		}

		x += velX;
		y += velY;
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

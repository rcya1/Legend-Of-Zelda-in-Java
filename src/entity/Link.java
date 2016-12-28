package entity;

import entity.weapons.Sword;
import main.GamePanel;
import components.OverWorld;
import reference.Images;
import reference.MathHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Link extends MapObject
{
	private boolean inputLeft;
	private boolean inputUp;
	private boolean inputRight;
	private boolean inputDown;
	private boolean inputAttack;

	private Sword sword;
	private int swordTimer;

	private Animation walkUp;
	private Animation walkRight;
	private Animation walkDown;
	private Animation walkLeft;

	private BufferedImage[] swordAttack;

	public Link(OverWorld overWorld)
	{
		this.overWorld = overWorld;

		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;

		drawX = x;
		drawY = y;

		moveSpeed = 1.5;

		width = 16;
		height = 16;

		swordTimer = 0;

		walkUp = new Animation(5, true, Images.Link.LINK_UP, Images.Link.LINK_UP_2);
		walkRight = new Animation(5, true, Images.Link.LINK_RIGHT, Images.Link.LINK_RIGHT_2);
		walkDown = new Animation(5, true, Images.Link.LINK_DOWN, Images.Link.LINK_DOWN_2);
		walkLeft = new Animation(5, true, Images.Link.LINK_LEFT, Images.Link.LINK_LEFT_2);

		swordAttack = new BufferedImage[] {Images.Link.LINK_ATTACK_SWORD_UP, Images.Link.LINK_ATTACK_SWORD_RIGHT,
				                           Images.Link.LINK_ATTACK_SWORD_DOWN, Images.Link.LINK_ATTACK_SWORD_LEFT};

		state = "IDLE";

		direction = Direction.UP;
	}

	public void update()
	{
		switch(state)
		{
		case "IDLE":
			velX = 0;
			velY = 0;

			checkFreeMovement();
			break;
		case "UP":
			velX = 0;
			velY = -moveSpeed;
			direction = Direction.UP;

			walkUp.update();

			checkFreeMovement();
			break;
		case "RIGHT":
			velX = moveSpeed;
			velY = 0;
			direction = Direction.RIGHT;

			walkRight.update();

			checkFreeMovement();
			break;
		case "DOWN":
			velX = 0;
			velY = moveSpeed;
			direction = Direction.DOWN;

			walkDown.update();

			checkFreeMovement();
			break;
		case "LEFT":
			velX = -moveSpeed;
			velY = 0;
			direction = Direction.LEFT;

			walkLeft.update();

			checkFreeMovement();
			break;
		case "ATTACK_SWORD_START":
			velX = 0;
			velY = 0;

			swordTimer = 16;

			state = "ATTACK_SWORD";
			break;
		case "ATTACK_SWORD":
			if(swordTimer == 9)
			{
				int[] drawingCoordinates = MathHelper.getSwordOffset(x, y, 12, direction);
				sword = new Sword(drawingCoordinates[0], drawingCoordinates[1], direction, overWorld);
			}
			else if(swordTimer <= 2)
			{
				sword.retract();
			}

			if(swordTimer > 0) swordTimer--;
			else
			{
				state = "IDLE";
				sword = null;
			}
			break;
		case "TRANSITION":
			//TODO Set longer inactive frames

			//TransitionAmountX determines distance went so far, but keeps going so that link suffers inactive frames

			velX = 0;
			velY = 0;

			x += transitionVelX;
			y += transitionVelY;

			transitionAmountX += transitionVelX;
			transitionAmountY += transitionVelY;

			if(Math.abs(transitionAmountX) == 20) transitionVelX = 0;
			if(Math.abs(transitionAmountY) == 20) transitionVelY = 0;

			if(transitionVelX == 0 && transitionVelY == 0)
			{
				state = "IDLE";
				transitionAmountX = 0;
				transitionAmountY = 0;
			}
			break;
		default:
			System.out.println(state);
			break;
		}

		if(sword != null) sword.update();

		handleCollisions();
	}

	public void draw(Graphics2D g2d)
	{
		drawX = x - overWorld.getCameraX();
		drawY = y - overWorld.getCameraY();

		if(sword != null) sword.draw(g2d);

		switch(state)
		{
		case "IDLE":
			switch(direction)
			{
			case UP:
				walkUp.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case RIGHT:
				walkRight.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case DOWN:
				walkDown.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case LEFT:
				walkLeft.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			default:
				break;
			}
			break;
		case "UP":
			walkUp.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
			break;
		case "DOWN":
			walkDown.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
			break;
		case "RIGHT":
			walkRight.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
			break;
		case "LEFT":
			walkLeft.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
			break;
		case "ATTACK_SWORD_START":
			g2d.drawImage(swordAttack[direction.getInteger()], drawX - width / 2, drawY - height / 2, width, height, null);
			break;
		case "ATTACK_SWORD":
			g2d.drawImage(swordAttack[direction.getInteger()], drawX - width / 2, drawY - height / 2, width, height, null);
			break;
		case "TRANSITION":
			switch(direction)
			{
			case UP:
				walkUp.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case RIGHT:
				walkRight.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case DOWN:
				walkDown.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			case LEFT:
				walkLeft.draw(g2d, drawX - width / 2, drawY - height / 2, width, height);
				break;
			default:
				break;
			}
			break;
		default:
			g2d.setColor(Color.RED);
			g2d.drawRect(drawX - width / 2, drawY - height / 2, width, height);
			break;
		}
	}

	//Check for movement that you have when you can do anything, i.e. IDLE, or LEFT/RIGHT/UP/DOWN
	private void checkFreeMovement()
	{
		if(inputUp) state = "UP";
		if(inputDown) state = "DOWN";
		if(inputLeft) state = "LEFT";
		if(inputRight) state = "RIGHT";
		if(inputAttack) state = "ATTACK_SWORD_START";
		if(!(inputUp || inputDown || inputLeft || inputRight || inputAttack)) state = "IDLE";

		if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
	}

	public void setKeyVariable(int key, boolean bool)
	{
		if(key == KeyEvent.VK_D) inputRight = bool;
		if(key == KeyEvent.VK_A) inputLeft = bool;
		if(key == KeyEvent.VK_W) inputUp = bool;
		if(key == KeyEvent.VK_S) inputDown = bool;
		if(key == KeyEvent.VK_SPACE) inputAttack = bool;
	}

	public Sword getSword()
	{
		return sword;
	}
}

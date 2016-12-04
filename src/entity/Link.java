package entity;

import main.GamePanel;
import map.TileMap;
import reference.Images;
import reference.MapHelper;
import reference.MathHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Link
{
	private TileMap tileMap;

	private int x;
	private int y;

	private double subPixelXVelocity;
	private double subPixelYVelocity;

	private double velX;
	private double velY;

	private double moveSpeed;

	private int width;
	private int height;

	private boolean left;
	private boolean up;
	private boolean right;
	private boolean down;
	private boolean attack;

	private Direction direction;

	private String state;

	private Sword sword;
	private int swordTimer;

	private Animation walkUp;
	private Animation walkRight;
	private Animation walkDown;
	private Animation walkLeft;

	private BufferedImage[] swordAttack;

	public Link(TileMap tileMap)
	{
		this.tileMap = tileMap;

		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;

		moveSpeed = 1.5;

		width = 16;
		height = 16;

		swordTimer = 0;

		walkUp = new Animation(5, Images.Link.LINK_UP, Images.Link.LINK_UP_2);
		walkRight = new Animation(5, Images.Link.LINK_RIGHT, Images.Link.LINK_RIGHT_2);
		walkDown = new Animation(5, Images.Link.LINK_DOWN, Images.Link.LINK_DOWN_2);
		walkLeft = new Animation(5, Images.Link.LINK_LEFT, Images.Link.LINK_LEFT_2);

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
				int[] drawingCoordinates = MathHelper.getSwordOffset(x - width / 2, y - height / 2, 12, direction);
				sword = new Sword(drawingCoordinates[0], drawingCoordinates[1], direction);
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
		default:
			System.out.println(state);
			break;
		}

		if(sword != null) sword.update();

		subPixelXVelocity += velX;
		subPixelYVelocity += velY;

		int newVelX = Math.round((float) subPixelXVelocity);
		int newVelY = Math.round((float) subPixelYVelocity);

		subPixelXVelocity = velX - newVelX;
		subPixelYVelocity = velY - newVelY;

		int collisionOffset = 6;

		if(newVelX > Math.ceil(moveSpeed)) newVelX = (int) Math.ceil(newVelX);
		if(newVelY > Math.ceil(moveSpeed)) newVelY = (int) Math.ceil(newVelY);

		for(int i = 0; i < Math.abs(newVelX); i++)
		{
			subPixelYVelocity = 0;
			int temporaryX = x + MathHelper.sign(velX);
			if(!MapHelper.checkCollisionWithTileMap(temporaryX, y, tileMap,
					width - collisionOffset, height - collisionOffset))
				x = temporaryX;
			else break;
		}

		for(int i = 0; i < Math.abs(newVelY); i++)
		{
			subPixelXVelocity = 0;
			int temporaryY = y + MathHelper.sign(velY);
			if(!MapHelper.checkCollisionWithTileMap(x, temporaryY, tileMap, width - collisionOffset, height - collisionOffset))
				y = temporaryY;
			else break;
		}
	}

	public void draw(Graphics2D g2d)
	{
		if(sword != null) sword.draw(g2d);

		switch(state)
		{
		case "IDLE":
			switch(direction)
			{
			case UP:
				walkUp.draw(g2d, x - width / 2, y - height / 2, width, height);
				break;
			case RIGHT:
				walkRight.draw(g2d, x - width / 2, y - height / 2, width, height);
				break;
			case DOWN:
				walkDown.draw(g2d, x - width / 2, y - height / 2, width, height);
				break;
			case LEFT:
				walkLeft.draw(g2d, x - width / 2, y - height / 2, width, height);
				break;
			default:
				break;
			}
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
		case "ATTACK_SWORD_START":
			g2d.drawImage(swordAttack[direction.getInteger()], x - width / 2, y - height / 2, width, height, null);
			break;
		case "ATTACK_SWORD":
			g2d.drawImage(swordAttack[direction.getInteger()], x - width / 2, y - height / 2, width, height, null);
			break;
		default:
			g2d.setColor(Color.RED);
			g2d.drawRect(x - width / 2, y - height / 2, width, height);
			break;
		}
	}

	//Check for movement that you have when you can do anything, i.e. IDLE, or LEFT/RIGHT/UP/DOWN
	private void checkFreeMovement()
	{
		if(up) state = "UP";
		if(down) state = "DOWN";
		if(left) state = "LEFT";
		if(right) state = "RIGHT";
		if(attack) state = "ATTACK_SWORD_START";
		if(!(up || down || left || right || attack)) state = "IDLE";
	}

	public void setKeyVariables(int key, boolean bool)
	{
		if(key == KeyEvent.VK_D) right = bool;
		if(key == KeyEvent.VK_A) left = bool;
		if(key == KeyEvent.VK_W) up = bool;
		if(key == KeyEvent.VK_S) down = bool;
		if(key == KeyEvent.VK_SPACE) attack = bool;
	}
}

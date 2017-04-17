package components.entity;

import components.items.player.BoomerangItem;
import components.items.player.Bow;
import components.items.player.Item;
import components.items.weapons.Arrow;
import components.items.weapons.Boomerang;
import utility.Animation;
import components.map.OverWorld;
import components.map.rooms.Room;
import components.map.rooms.SecretRoom;
import components.entity.enemies.Enemy;
import components.entity.enemies.Octorok;
import components.items.MapItem;
import components.map.WarpTile;
import components.items.collectibles.Collectible;
import components.items.weapons.Sword;
import utility.Data;
import utility.Images;
import utility.MathHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Link extends Entity
{
	private final OverWorld overWorld;

	private int transitionAmountX;
	private int transitionAmountY;

	private int transitionVelX;
	private int transitionVelY;

	private boolean inputLeft;
	private boolean inputUp;
	private boolean inputRight;
	private boolean inputDown;
	private boolean inputAttack;
	private boolean inputItem;

	private Sword sword;
	private int swordTimer;

	private Item item;
	private int itemTimer;

	private Arrow arrow;
	private Boomerang boomerang;

	private final Animation walkUp;
	private final Animation walkRight;
	private final Animation walkDown;
	private final Animation walkLeft;

	private final BufferedImage[] swordAttack;

	private int healthContainers;
	private final int maxHealthContainers;

	public Link(OverWorld overWorld)
	{
		this.overWorld = overWorld;
		this.room = overWorld.getCurrentRoom();

		x = 100;
		y = 96;

		drawX = (int) Math.round(x);
		drawY = (int) Math.round(y);

		moveSpeed = 1.5;

		width = 16;
		height = 16;

		swordTimer = 0;

		walkUp = new Animation(5, true, Images.Link.LINK_UP, Images.Link.LINK_UP_2);
		walkRight = new Animation(5, true, Images.Link.LINK_RIGHT, Images.Link.LINK_RIGHT_2);
		walkDown = new Animation(5, true, Images.Link.LINK_DOWN, Images.Link.LINK_DOWN_2);
		walkLeft = new Animation(5, true, Images.Link.LINK_LEFT, Images.Link.LINK_LEFT_2);

		swordAttack = new BufferedImage[] {Images.Link.LINK_ATTACK_SWORD_UP, Images.Link.LINK_ATTACK_SWORD_RIGHT, Images.Link.LINK_ATTACK_SWORD_DOWN, Images.Link.LINK_ATTACK_SWORD_LEFT};

		state = "IDLE";

		direction = Direction.UP;

		health = 24;
		healthContainers = 3;
		maxHealthContainers = 16;

		item = new BoomerangItem();
	}

	public void update()
	{
		if(!(this.room instanceof SecretRoom))
		{
			this.room = overWorld.getCurrentRoom();
		}

		switch(state)
		{
		case "IDLE":
			velX = 0;
			velY = 0;

			checkFreeMovement();
			break;
		case "UP":
			velX = alignToGrid(x, 8);
			velY = -moveSpeed;
			direction = Direction.UP;

			walkUp.update();

			checkFreeMovement();
			break;
		case "RIGHT":
			velX = moveSpeed;
			velY = alignToGrid(y, 8);
			direction = Direction.RIGHT;

			walkRight.update();

			checkFreeMovement();
			break;
		case "DOWN":
			velX = alignToGrid(x, 8);
			velY = moveSpeed;
			direction = Direction.DOWN;

			walkDown.update();

			checkFreeMovement();
			break;
		case "LEFT":
			velX = -moveSpeed;
			velY = alignToGrid(y, 8);
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
				int[] drawingCoordinates = MathHelper.getSwordOffset((int) Math.round(x), (int) Math.round(y), 12, direction);
				sword = new Sword(drawingCoordinates[0], drawingCoordinates[1], direction, overWorld.getCurrentRoom());
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
		case "ITEM_USE":
			velX = 0;
			velY = 0;

			if(item != null)
			{
				if(itemTimer == 0) itemTimer = 30;
				if(itemTimer == 20) item.action(this);

				if(itemTimer > 0) itemTimer--;
				if(itemTimer == 0) state = "IDLE";
			}
			else state = "IDLE";
			break;
		case "TRANSITION":
			velX = 0;
			velY = 0;

			x += transitionVelX;
			y += transitionVelY;

			transitionAmountX += transitionVelX;
			transitionAmountY += transitionVelY;

			if(Math.abs(transitionAmountX) == room.getMapWidth() - 20)
				transitionVelX = 0;
			if(Math.abs(transitionAmountY) == room.getMapHeight() - 20)
				transitionVelY = 0;

			if(transitionVelX == 0 && transitionVelY == 0)
			{
				transitionAmountX = 0;
				transitionAmountY = 0;

				if(!overWorld.isMoving())
				{
					this.state = "IDLE";
				}
			}
			break;
		default:
			System.out.println(state);
			break;
		}

		if(invincibilityFrames > 0) invincibilityFrames--;

		if(sword != null) sword.update();
		if(arrow != null)
		{
			arrow.update();

			Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
			if(!screen.intersects(arrow.getRectangle())) arrow = null;
		}

		if(boomerang != null)
		{
			boomerang.update();

			if(boomerang.getReturnTimer() == 0 && getRectangle().intersects(boomerang.getRectangle()))
			{
				boomerang = null;
			}
			else
			{
				Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
				if(!screen.intersects(boomerang.getRectangle())) boomerang = null;
			}
		}

		if(!state.equals("TRANSITION"))
		{
			handleTileCollisions();
			if(invincibilityFrames == 0) handleEnemyCollisions();
			handleMapItemCollisions();
		}
	}

	public void draw(Graphics2D g2d)
	{
		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			drawX = (int) Math.round(x) - width / 2;
			drawY = (int) Math.round(y) - height / 2;


			if(sword != null) sword.draw(g2d);
			if(arrow != null) arrow.draw(g2d);
			if(boomerang != null) boomerang.draw(g2d);

			switch(state)
			{
			case "IDLE":
				switch(direction)
				{
				case UP:
					walkUp.draw(g2d, drawX, drawY, width, height);
					break;
				case RIGHT:
					walkRight.draw(g2d, drawX, drawY, width, height);
					break;
				case DOWN:
					walkDown.draw(g2d, drawX, drawY, width, height);
					break;
				case LEFT:
					walkLeft.draw(g2d, drawX, drawY, width, height);
					break;
				default:
					break;
				}
				break;
			case "UP":
				walkUp.draw(g2d, drawX, drawY, width, height);
				break;
			case "DOWN":
				walkDown.draw(g2d, drawX, drawY, width, height);
				break;
			case "RIGHT":
				walkRight.draw(g2d, drawX, drawY, width, height);
				break;
			case "LEFT":
				walkLeft.draw(g2d, drawX, drawY, width, height);
				break;
			case "ATTACK_SWORD_START":
				g2d.drawImage(swordAttack[direction.getInteger()], drawX, drawY, width, height, null);
				break;
			case "ATTACK_SWORD":
				g2d.drawImage(swordAttack[direction.getInteger()], drawX, drawY, width, height, null);
				break;
			case "ITEM_USE":
				switch(direction)
				{
				case UP:
					g2d.drawImage(Images.Link.LINK_ITEM_UP, drawX, drawY, width, height, null);
					break;
				case RIGHT:
					g2d.drawImage(Images.Link.LINK_ITEM_RIGHT, drawX, drawY, width, height, null);
					break;
				case DOWN:
					g2d.drawImage(Images.Link.LINK_ITEM_DOWN, drawX, drawY, width, height, null);
					break;
				case LEFT:
					g2d.drawImage(Images.Link.LINK_ITEM_LEFT, drawX, drawY, width, height, null);
					break;
				default:
					break;
				}
				break;
			case "TRANSITION":
				switch(direction)
				{
				case UP:
					walkUp.draw(g2d, drawX, drawY, width, height);
					break;
				case RIGHT:
					walkRight.draw(g2d, drawX, drawY, width, height);
					break;
				case DOWN:
					walkDown.draw(g2d, drawX, drawY, width, height);
					break;
				case LEFT:
					walkLeft.draw(g2d, drawX, drawY, width, height);
					break;
				default:
					break;
				}
				break;
			default:
				g2d.setColor(Color.RED);
				g2d.drawRect(drawX, drawY, width, height);
				break;
			}
		}

//		drawDebug(g2d);
	}

	//Check for movement that you have when you can do anything, i.e. IDLE, or LEFT/RIGHT/UP/DOWN
	private void checkFreeMovement()
	{
		if(inputUp) state = "UP";
		if(inputDown) state = "DOWN";
		if(inputLeft) state = "LEFT";
		if(inputRight) state = "RIGHT";
		if(inputAttack && Data.hasSword) state = "ATTACK_SWORD_START";
		if(inputItem && item != null) state = "ITEM_USE";
		if(!(inputUp || inputDown || inputLeft || inputRight || inputAttack || inputItem))
			state = "IDLE";

		if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
	}

	private void handleEnemyCollisions()
	{
		ArrayList<Enemy> enemies = overWorld.getCurrentRoom().getEnemies();
		for(Enemy enemy : enemies)
		{
			if(checkCollisionWith(enemy))
			{
				health -= enemy.getDamage();
				invincibilityFrames = 30;
			}

			if(enemy instanceof Octorok)
			{
				Octorok octorok = (Octorok) enemy;

				if(octorok.getPelletCollisionBox() != null)
				{
					if(checkCollisionWith(octorok.getPelletCollisionBox()))
					{
						health -= octorok.getPelletDamage();
						invincibilityFrames = 30;
						octorok.removePellet();
					}
				}
			}
		}
	}

	private void handleMapItemCollisions()
	{
		ArrayList<MapItem> mapItems = overWorld.getCurrentRoom().getMapItems();
		Iterator iterator = mapItems.iterator();
		while(iterator.hasNext())
		{
			MapItem mapItem = (MapItem) iterator.next();
			if(mapItem instanceof Collectible)
			{
				Collectible collectible = (Collectible) mapItem;

				Rectangle collectiblePickUpRectangle = new Rectangle((int) this.x - this.width / 2,
						(int) this.y - height / 2, this.width * 2, this.height * 2);

				if(collectiblePickUpRectangle.intersects(collectible.getRectangle()))
				{
					if(collectible.action(this)) iterator.remove();
				}
			}
			else if(mapItem instanceof WarpTile)
			{
				if(checkCollisionWith(mapItem.getRectangle()))
				{
					WarpTile warpTile = (WarpTile) mapItem;

					if(warpTile.getDirection() != null)
					{
						if(this.direction == warpTile.getDirection())
						{
							this.x = warpTile.getDestColumn() * room.getWidthOfTile()
									+ room.getWidthOfTile() / 2;
							this.y = warpTile.getDestRow() * room.getHeightOfTile()
									+ room.getHeightOfTile() / 2;
						}
					}
					else
					{
						this.x = warpTile.getDestColumn() * room.getWidthOfTile()
								+ room.getWidthOfTile() / 2;
						this.y = warpTile.getDestRow() * room.getHeightOfTile()
								+ room.getHeightOfTile() / 2;
					}

					if(warpTile.getType().equals("CAVE"))
					{
						room = new SecretRoom(room.getId(), overWorld,
								room.getMetadata(),
								warpTile.getColumn(room.getWidthOfTile()),
								warpTile.getRow(room.getHeightOfTile()));
					}
				}
			}
		}
	}

	public void setKeyVariable(int key, boolean bool)
	{
		if(key == KeyEvent.VK_D) inputRight = bool;
		if(key == KeyEvent.VK_A) inputLeft = bool;
		if(key == KeyEvent.VK_W) inputUp = bool;
		if(key == KeyEvent.VK_S) inputDown = bool;
		if(key == KeyEvent.VK_SPACE) inputAttack = bool;
		if(key == KeyEvent.VK_SHIFT) inputItem = bool;
	}

	public Sword getSword()
	{
		return sword;
	}

	public void setArrow(Arrow arrow)
	{
		this.arrow = arrow;
	}

	public Arrow getArrow()
	{
		return arrow;
	}

	public void setBoomerang(Boomerang boomerang)
	{
		this.boomerang = boomerang;
	}

	public Boomerang getBoomerang()
	{
		return boomerang;
	}

	public int getHealthContainers()
	{
		return healthContainers;
	}
	public int getMaxHealthContainers()
	{
		return maxHealthContainers;
	}

	public void addHealth(int restorePoints)
	{
		health += restorePoints;
	}

	public void addHealthContainers(int containers)
	{
		healthContainers += containers;
	}

	public void setTransitionVector(int transitionVelX, int transitionVelY)
	{
		this.transitionVelX = transitionVelX;
		this.transitionVelY = transitionVelY;
	}

	public Room getRoom()
	{
		return this.room;
	}

	public void setRoom(Room room)
	{
		this.room = room;
	}
}

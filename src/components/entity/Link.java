package components.entity;

import components.MapItem;
import components.entity.enemies.Enemy;
import components.entity.enemies.ProjectileDeflectibleEnemy;
import components.entity.enemies.ProjectileEnemy;
import components.items.collectibles.Collectible;
import components.items.player.Item;
import components.items.weapons.Arrow;
import components.items.weapons.Boomerang;
import components.items.weapons.Sword;
import components.map.WarpTile;
import components.map.World;
import components.map.rooms.Room;
import components.map.rooms.SecretRoom;
import utility.Animation;
import utility.Data;
import utility.Images;
import utility.MathHelper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

//The player
public class Link extends Entity
{
	private final World world;                         //The current world that Link is in

	private boolean inputLeft, inputUp, inputRight,    //Variables that keep track of inputs
			inputDown, inputAttack, inputItem;

	private Sword sword;                               //Link's sword
	private int swordTimer;                            //Timer for Link extending and retracting his sword

	private Item item;                                 //The current item that Link has equipped
	private int itemTimer;                             //Timer for Link's item animation

	private Arrow arrow;                               //The arrow that Link has shot
	private Boomerang boomerang;                       //The boomerang that Link has thrown out

	private final Animation walkUp, walkRight,         //Link's animations
			walkDown, walkLeft;

	private final BufferedImage[] swordAttack;         //All of the possible images for Link's extending sprite

	private int healthContainers;                      //Link's total health containers
	private final int maxHealthContainers;             //Max amount of health containers Link can have

	private Collectible drawCollectible;               //The collectible link has just collected, so that it can be drawn
	private int getAnimationTimer;                     //The timer for the item get animation

	private int transitionAmountX, transitionAmountY;  //How far Link has moved in the transition
	private int transitionVelX, transitionVelY;        //How Link is moving for the transition

	//TODO Add in the knockback from hitting an enemy
	public Link(World world)
	{
		this.world = world;
		this.room = world.getCurrentRoom();

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

		health = 6;
		healthContainers = 3;
		maxHealthContainers = 16;

		getAnimationTimer = 0;

		item = null;
	}

	//Updates Link's position, items, projectiles, and animations
	public void update()
	{
		//Set up the current room
		if(!(this.room instanceof SecretRoom))
		{
			this.room = world.getCurrentRoom();
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
				//After 7 frames, Link should extend his sword
				if(swordTimer == 9)
				{
					int[] drawingCoordinates = MathHelper.getSwordOffset(
							(int) Math.round(x), (int) Math.round(y), 12, direction);
					sword = new Sword(drawingCoordinates[0], drawingCoordinates[1], direction, world.getCurrentRoom());
				}
				//After 14 frames, then Link should begin retracting his sword
				else if(swordTimer <= 2)
				{
					sword.retract();
				}

				if(swordTimer > 0) swordTimer--;
				//After 16 frames, Link's sword should disappear
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
					//After 10 frames, Link should use his item
					if(itemTimer == 20) item.action(this);

					if(itemTimer > 0) itemTimer--;
					//After 30 frames, Link should go back to idle
					if(itemTimer == 0) state = "IDLE";
				}
				else state = "IDLE";
				break;
			case "GET_ITEM":
				velX = 0;
				velY = 0;
				if(getAnimationTimer == 0) getAnimationTimer = 180;
				else getAnimationTimer--;

				//After 180 frames, then Link should go back to idle
				if(getAnimationTimer == 0) state = "IDLE";
				break;
			case "GET_TRIFORCE":
				velX = 0;
				velY = 0;
				if(getAnimationTimer == 0) getAnimationTimer = 180;
				else getAnimationTimer--;

				//After 180 frames, then Link should go back to idle
				if(getAnimationTimer == 0) state = "IDLE";
				break;
			case "TRANSITION":
				velX = 0;
				velY = 0;

				//Move Link with the transition
				x += transitionVelX;
				y += transitionVelY;

				//Store how far Link has moved
				transitionAmountX += transitionVelX;
				transitionAmountY += transitionVelY;

				//Move Link until he is 20 pixels away from the edge of the room
				if(Math.abs(transitionAmountX) == room.getMapWidth() - 20)
					transitionVelX = 0;
				if(Math.abs(transitionAmountY) == room.getMapHeight() - 20)
					transitionVelY = 0;

				if(transitionVelX == 0 && transitionVelY == 0)
				{
					//Reset the amount stored
					transitionAmountX = 0;
					transitionAmountY = 0;

					//Once Link and the world have both stopped moving, then set Link to idle
					if(!world.isMoving()) state = "IDLE";
				}
				break;
			default:
				System.out.println(state);
				break;
		}

		//Decrease any invincibility frames
		if(invincibilityFrames > 0) invincibilityFrames--;

		//Create a screen rectangle for checking if projectiles have gone off screen
		Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());

		//Update Link's sword
		if(sword != null) sword.update();
		//Update the arrow
		if(arrow != null)
		{
			arrow.update();

			//If the arrow has gone off the screen, remove it
			if(arrow != null) if(!screen.intersects(arrow.getRectangle())) arrow = null;
		}

		//Update the boomerang
		if(boomerang != null)
		{
			boomerang.update();

			//If the boomerang is coming back and it collides with Link, then remove the boomerang
			if(boomerang.getReturnTimer() == 0 && getRectangle().intersects(boomerang.getRectangle()))
			{
				boomerang = null;
			}
			//If the boomerang goes off screen, then remove it
			else if(!screen.intersects(boomerang.getRectangle())) boomerang = null;
		}

		//Check for collisions
		if(!state.equals("TRANSITION"))
		{
			handleTileCollisions();
			if(invincibilityFrames == 0) handleEnemyCollisions();
			handleMapItemCollisions();
		}
	}

	//Draw Link and all of his projectiles
	public void draw(Graphics2D g2d)
	{
		//If Link is invincible, make him flicker by only drawing him every third frame
		if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
		{
			//Set integer forms of the current x/y for drawing
			drawX = (int) Math.round(x) - width / 2;
			drawY = (int) Math.round(y) - height / 2;

			//Draw all of Link's items/projectiles
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
				case "GET_ITEM":
					g2d.drawImage(Images.Link.Items.LINK_GET_ITEM, drawX, drawY, width, height, null);
					//Draw the collectible on top of Link for Link holding it
					drawCollectible.draw(drawX + drawCollectible.getWidth() / 4,
							drawY - drawCollectible.getHeight() / 2, g2d);
					break;
				case "GET_TRIFORCE":
					g2d.drawImage(Images.Link.Items.LINK_GET_TRIFORCE, drawX, drawY, width, height, null);
					//TODO Draw a triforce
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
		if(inputAttack && Data.swordLevel > 0) state = "ATTACK_SWORD_START";
		if(inputItem && item != null) state = "ITEM_USE";
		if(!(inputUp || inputDown || inputLeft || inputRight || inputAttack || inputItem))
			state = "IDLE";

		if(transitionVelX != 0 || transitionVelY != 0) 	state = "TRANSITION";
	}

	//Checks for collisions with all enemies in the room
	private void handleEnemyCollisions()
	{
		ArrayList<Enemy> enemies = room.getEnemies();
		for(Enemy enemy : enemies)
		{
			//Checks for a direct collision with the enemy
			if(checkCollisionWith(enemy) && enemy.getDamage() > 0)
			{
				health -= enemy.getDamage();
				invincibilityFrames = 30;
			}

			//If the enemy has a possible projectile
			if(enemy instanceof ProjectileEnemy)
			{
				ProjectileEnemy projectileEnemy = (ProjectileEnemy) enemy;
				if(projectileEnemy.getProjectileCollisionBox() != null)
				{
					//If Link is touching the enemy's projectile
					if(checkCollisionWith(projectileEnemy.getProjectileCollisionBox()))
					{
						//If the enemy's projectile can be deflected
						if(projectileEnemy instanceof ProjectileDeflectibleEnemy)
						{
							ProjectileDeflectibleEnemy projectileDeflectibleEnemy =
									(ProjectileDeflectibleEnemy) projectileEnemy;

							//Check if Link has the required shield level to deflect and Link is facing the right way
							if((projectileDeflectibleEnemy.getShieldRequiredLevel() <=
									Data.shieldLevel && projectileDeflectibleEnemy.
									getProjectileDirection().getOpposite() == direction))
							{
								//Remove the projectile
								projectileEnemy.removeProjectile();
								return;
							}
						}

						//Cause Link to take damage from the projectile
						health -= projectileEnemy.getProjectileDamage();
						invincibilityFrames = 30;
						projectileEnemy.removeProjectile();
					}
				}
			}
		}
	}

	//Checks for collisions with items in the room
	private void handleMapItemCollisions()
	{
		//Go through every single map item
		ArrayList<MapItem> mapItems = room.getMapItems();
		Iterator iterator = mapItems.iterator();
		while(iterator.hasNext())
		{
			MapItem mapItem = (MapItem) iterator.next();

			//If the map item can be collected
			if(mapItem instanceof Collectible)
			{
				Collectible collectible = (Collectible) mapItem;

				Rectangle collectiblePickUpRectangle = new Rectangle((int) this.x - this.width / 2,
						(int) this.y - height / 2, this.width * 2, this.height * 2);

				//Check if Link is touching the item
				if(collectiblePickUpRectangle.intersects(collectible.getRectangle()))
				{
					//Activate the collectible's item and remove it if necessary
					if(collectible.action(this)) iterator.remove();
				}
			}
			//If the map item is a warp tile
			else if(mapItem instanceof WarpTile)
			{
				if(checkCollisionWith(mapItem.getRectangle()))
				{
					WarpTile warpTile = (WarpTile) mapItem;

					if(warpTile.getDirection() != null)
					{
						//If the warp tile has a direction, then make sure Link is going in that direction
						if(this.direction == warpTile.getDirection())
						{
							//Teleport to the warp tile's destination
							this.x = warpTile.getDestColumn() * room.getWidthOfTile()
									+ room.getWidthOfTile() / 2;
							this.y = warpTile.getDestRow() * room.getHeightOfTile()
									+ room.getHeightOfTile() / 2;
						}
					}
					else
					{
						//Teleport to the warp tile's destination
						this.x = warpTile.getDestColumn() * room.getWidthOfTile()
								+ room.getWidthOfTile() / 2;
						this.y = warpTile.getDestRow() * room.getHeightOfTile()
								+ room.getHeightOfTile() / 2;
					}

					//If the warp tile leads to a cave, then move Link to the cave
					if(warpTile.getType().equals("CAVE"))
					{
						room = new SecretRoom(room.getId(), world,
								room.getMetadata(),
								warpTile.getColumn(room.getWidthOfTile()),
								warpTile.getRow(room.getHeightOfTile()));
					}
				}
			}
		}
	}

	//Updates Link's input variables
	public void setKeyVariable(int key, boolean bool)
	{
		if(key == KeyEvent.VK_D) inputRight = bool;
		if(key == KeyEvent.VK_A) inputLeft = bool;
		if(key == KeyEvent.VK_W) inputUp = bool;
		if(key == KeyEvent.VK_S) inputDown = bool;
		if(key == KeyEvent.VK_SPACE) inputAttack = bool;
		if(key == KeyEvent.VK_SHIFT) inputItem = bool;
	}

	//Makes Link go into the collect state w/ the given collectible
	public void enterItemState(Collectible collectible)
	{
		this.state = "GET_ITEM";
		drawCollectible = collectible;
	}

	public void enterTriforceState()
	{
		this.state = "GET_TRIFORCE";
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

	public void setItem(Item item)
	{
		this.item = item;
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

	public boolean isTransitioning()
	{
		return state.equals("TRANSITION") || world.isMoving();
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

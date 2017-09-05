package components.entity.enemies.overworld;

import components.entity.Direction;
import components.entity.enemies.Enemy;
import components.entity.enemies.ProjectileDeflectibleEnemy;
import components.entity.enemies.ProjectileEnemy;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;
import utility.Tile;

import java.awt.*;

public class Lynel extends Enemy implements ProjectileEnemy, ProjectileDeflectibleEnemy
{
    private int shootingTimer;
    private int movementRefreshTimer;

    Animation up;
    Animation right;
    Animation down;
    Animation left;

    private LynelSword sword;

    public Lynel(int x, int y, Direction direction, Room room)
    {
        super(x, y, room, 8, 4, "MOVING", 16, 16);

        this.direction = direction;

        velX = 0;
        velY = 0;

        moveSpeed = 0.8;
        
        up = new Animation(10, true, Images.Enemies.Lynel.LYNEL_UP,
                Images.Enemies.Lynel.LYNEL_UP_2);
        right = new Animation(10, true, Images.Enemies.Lynel.LYNEL_RIGHT,
                Images.Enemies.Lynel.LYNEL_RIGHT_2);
        down = new Animation(10, true, Images.Enemies.Lynel.LYNEL_DOWN,
                Images.Enemies.Lynel.LYNEL_DOWN_2);
        left = new Animation(10, true, Images.Enemies.Lynel.LYNEL_LEFT,
                Images.Enemies.Lynel.LYNEL_LEFT_2);
    }

    public void update()
    {
        switch(state)
        {
            case "MOVING":
                double[] vector = direction.getVector(moveSpeed);
                velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
                velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

                if((Math.random() * 100) < 2) direction = Direction.getRandom();
                if((Math.random() * 200) < 2)
                {
                    state = "SHOOTING";
                    shootingTimer = 0;
                }
                if(x < 0) direction = Direction.RIGHT;
                if(y < 0) direction = Direction.DOWN;
                if(x > room.getMapWidth()) direction = Direction.LEFT;
                if(y > room.getMapHeight()) direction = Direction.UP;

                break;
            case "SHOOTING":
                velX = 0;
                velY = 0;

                if(shootingTimer < 90)
                {
                    shootingTimer++;
                    if(shootingTimer == 40)
                    {
                        sword = new LynelSword(x, y, direction);
                    }
                }
                else
                {
                    state = "MOVING";
                }
                break;
            default:
                break;
        }

        switch(direction)
        {
            case UP:
                up.update();
                break;
            case RIGHT:
                right.update();
                break;
            case DOWN:
                down.update();
                break;
            case LEFT:
                left.update();
                break;
        }

        if(handleTileCollisions() && movementRefreshTimer == 0)
        {
            movementRefreshTimer = 120;
            direction = Direction.getExcludedRandom(direction);
        }

        if(movementRefreshTimer > 0) movementRefreshTimer--;

        if(sword != null)
        {
            sword.update();

            Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
            if(!screen.intersects(sword.getRectangle())) sword = null;

            if(sword != null) handleSwordWallCollision();
        }

        super.update();
    }

    private void handleSwordWallCollision()
    {
        double x = sword.getRectangle().getCenterX();
        double y = sword.getRectangle().getCenterY();

        int width = (int) sword.getRectangle().getWidth();
        int height = (int) sword.getRectangle().getHeight();

        int leftColumn = (int) Math.round(x - width / 2) / room.getWidthOfTile();
        int rightColumn = (int) Math.round(x + width / 2) / room.getWidthOfTile();
        int topRow = (int) Math.round(y - height / 2) / room.getHeightOfTile();
        int bottomRow = (int) Math.round(y + height / 2) / room.getHeightOfTile();

        if(leftColumn < 0) leftColumn = 0;
        if(rightColumn > room.getNumOfColumns() - 1) rightColumn = room.getNumOfColumns() - 1;
        if(topRow < 0) topRow = 0;
        if(bottomRow > room.getNumOfRows() - 1) bottomRow = room.getNumOfRows() - 1;

        for(int i = leftColumn; i <= rightColumn; i++)
        {
            for(int j = topRow; j <= bottomRow; j++)
            {
                Tile tile = room.getTile(i, j);
                if(tile != null)
                {
                    Rectangle tileRectangle = new Rectangle(i * room.getWidthOfTile(),
                            j * room.getHeightOfTile(), room.getWidthOfTile(),
                            room.getHeightOfTile() / 2);

                    if(!tile.isPassible() && sword.getRectangle().intersects(tileRectangle))
                    {
                        sword = null;
                        break;
                    }
                }

                if(sword == null) break;
            }

            if(sword == null) break;
        }
    }

    public void draw(Graphics2D g2d)
    {
        drawX = (int) Math.round(x) - width / 2;
        drawY = (int) Math.round(y) - height / 2;

        if(sword != null) sword.draw(g2d);

        if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0))
        {
            switch(direction)
            {
                case UP:
                    up.draw(g2d, drawX, drawY, width, height);
                    break;
                case RIGHT:
                    right.draw(g2d, drawX, drawY, width, height);
                    break;
                case DOWN:
                    down.draw(g2d, drawX, drawY, width, height);
                    break;
                case LEFT:
                    left.draw(g2d, drawX, drawY, width, height);
                    break;
            }
        }
    }

    public void removeProjectile()
    {
        sword = null;
    }

    public Rectangle getProjectileCollisionBox()
    {
        if(sword != null)
        {
            return sword.getRectangle();
        }
        else
        {
            return null;
        }
    }

    public int getProjectileDamage()
    {
        if(sword != null)
        {
            return sword.getDamage();
        }
        else
        {
            return 0;
        }
    }

    public int getShieldRequiredLevel()
    {
        return 1;
    }

    public Direction getProjectileDirection()
    {
        return sword.getDirection();
    }
}

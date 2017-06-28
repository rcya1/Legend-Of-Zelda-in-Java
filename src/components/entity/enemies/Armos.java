package components.entity.enemies;

import components.entity.Direction;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Armos extends Enemy
{
    private int wakeUpTimer;
    private int movementRefreshTimer;
    private boolean facingUp = false;

    BufferedImage immobile;
    private Animation walkingUp;
    private Animation walkingDown;

    Armos(int x, int y, Room room)
    {
        this.x = x;
        this.y = y;

        direction = Direction.DOWN;

        this.room = room;

        velX = 0;
        velY = 0;

        width = 16;
        height = 16;

        state = "IMMOBILE";

        moveSpeed = Math.random() <= 0.5 ? 0.75 : 1.5;

        walkingUp = new Animation(20, true, Images.Enemies.Armos.ARMOS_BACK_1, Images.Enemies.Armos.ARMOS_BACK_2);
        walkingDown = new Animation(20, true, Images.Enemies.Armos.ARMOS_FRONT_1, Images.Enemies.Armos.ARMOS_FRONT_2);

        health = 6;
        damage = 2;
    }

    public void update()
    {
        switch(state)
        {
            case "IMMOBILE":
                if(Math.abs(room.getLink().getX() - x) <= room.getWidthOfTile() &&
                        Math.abs(room.getLink().getY() - y) <= room.getHeightOfTile())
                {
                    state = "WAKING";
                    wakeUpTimer = 120;
                }

                invincibilityFrames = 1;
                break;
            case "WAKING":
                wakeUpTimer--;
                if(wakeUpTimer == 0) state = "MOVING";

                invincibilityFrames = 1;
                break;
            case "MOVING":
                double[] vector = direction.getVector(moveSpeed);
                velX = (vector[0] != 0) ? vector[0] : alignToGrid(x, 8);
                velY = (vector[1] != 0) ? vector[1] : alignToGrid(y, 8);

                if(Math.random() * 100 < 1) direction = Direction.getRandom();

                facingUp = direction == Direction.UP;

                if(facingUp) walkingUp.update();
                else walkingDown.update();

                if(x < 0) direction = Direction.RIGHT;
                if(y < 0) direction = Direction.DOWN;
                if(x > room.getMapWidth()) direction = Direction.LEFT;
                if(y > room.getMapHeight()) direction = Direction.UP;
                break;
        }

        if(handleTileCollisions() && movementRefreshTimer == 0)
        {
            movementRefreshTimer = 120;
            direction = Direction.getExcludedRandom(direction);
        }

        if(movementRefreshTimer > 0) movementRefreshTimer--;

        super.update();
    }

    public void draw(Graphics2D g2d)
    {
        drawX = (int) Math.round(x) - width / 2;
        drawY = (int) Math.round(y) - height / 2;

        if(!(invincibilityFrames > 0 && invincibilityFrames % 3 == 0 && state.equals("MOVING")))
        {
            switch(state)
            {
                case "IMMOBILE":
                    g2d.drawImage(immobile, drawX, drawY, null);
                    break;
                case "WAKING":
                    if(wakeUpTimer % 4 < 2) walkingDown.draw(g2d, drawX, drawY, width, height);
                    else g2d.drawImage(immobile, drawX, drawY, null);
                    break;
                case "MOVING":
                    if(facingUp) walkingUp.draw(g2d, drawX, drawY, width, height);
                    else walkingDown.draw(g2d, drawX, drawY, width, height);
                break;
            }
        }
    }
}

package components.entity.enemies;

import components.map.rooms.Room;
import utility.Images;

import java.awt.*;

//TODO Fix problem where main ghihi moves faster than the fake ones
public class Ghini extends Enemy
{
    private boolean mainGhini;
    private int movementRefreshTimer;
    private int direction;
    private boolean facingUp = false;

    public Ghini(int x, int y, int direction, Room room, boolean mainGhini)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.room = room;
        this.mainGhini = mainGhini;

        velX = 0;
        velY = 0;

        width = 16;
        height = 16;

        moveSpeed = 0.5;
        movementRefreshTimer = 0;
        state = "MOVING";

        health = 18;
        damage = 2;
    }

    public void update()
    {
        switch(state)
        {
            case "MOVING":
            {
                if(mainGhini)
                {
                    if(Math.random() * 300 > 298)
                    {
                        int newDirection = ((int) (Math.random() * 4)) * 2;
                        while(newDirection == direction)
                        {
                            newDirection = ((int) (Math.random() * 4)) * 2;
                        }

                        direction = newDirection;
                    }
                }
                else
                {
                    if(Math.random() * 250 > 248)
                    {
                        int newDirection = (int) (Math.random() * 8);
                        while(newDirection == direction)
                        {
                            newDirection = (int) (Math.random() * 8);
                        }

                        direction = newDirection;
                    }
                }

                double angle = direction * Math.PI / 4;

                velX = Math.cos(angle) * moveSpeed;
                velY = Math.sin(angle) * moveSpeed;

                x += velX;
                y += velY;
            }
        }

        if(!mainGhini) invincibilityFrames = -1;
        else
        {
            if(handleTileCollisions() && movementRefreshTimer == 0)
            {
                movementRefreshTimer = 120;

                int newDirection = ((int) (Math.random() * 4)) * 2;
                while(newDirection == direction)
                {
                    newDirection = ((int) (Math.random() * 4)) * 2;
                }

                direction = newDirection;
            }

            if(movementRefreshTimer > 0) movementRefreshTimer--;
        }

        facingUp = 5 <= direction && direction <= 7;

        Rectangle screen = new Rectangle(room.getMapWidth(), room.getMapHeight());
        if(!screen.intersects(getRectangle()))
        {
            if(mainGhini) direction = (direction + 4) % 8;
            else direction = ((direction + 4) + (int) (Math.random() * 3 - 1.5)) % 8;
        }

        System.out.println(direction);

        super.update();
    }

    public void draw(Graphics2D g2d)
    {
        int drawX = (int) x - width / 2;
        int drawY = (int) y - height / 2;

        if(facingUp)
        {
            if(velX >= 0) g2d.drawImage(Images.Enemies.Ghini.GHINI_BOTTOM_RIGHT, drawX, drawY, width, height, null);
            if(velX < 0) g2d.drawImage(Images.Enemies.Ghini.GHINI_BOTTOM_LEFT, drawX, drawY, width, height, null);
        }
        else
        {
            if(velX >= 0) g2d.drawImage(Images.Enemies.Ghini.GHINI_TOP_RIGHT, drawX, drawY, width, height, null);
            if(velX < 0) g2d.drawImage(Images.Enemies.Ghini.GHINI_TOP_LEFT, drawX, drawY, width, height, null);
        }
    }
}

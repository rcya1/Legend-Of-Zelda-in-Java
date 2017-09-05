package components.entity.enemies.overworld;

import components.entity.enemies.Enemy;
import components.entity.enemies.KnockbackResistEnemy;
import components.map.rooms.Room;
import utility.Animation;
import utility.Images;

import java.awt.*;

public class Rock extends Enemy implements KnockbackResistEnemy
{
    private double targetX, targetY;
    private final double gravity;

    private int restartTimer;

    Animation animation;

    public Rock(Room room)
    {
        super(0, 0, room, 5000, 1, "ROLLING", 16, 16);

        animation = new Animation(6, true, Images.Enemies.Rock.ROCK_1, Images.Enemies.Rock.ROCK_2);

        init();
        gravity = -0.3;
    }

    private void init()
    {
        this.x = room.getWidthOfTile() * (2 + (int) (Math.random() * (room.getNumOfColumns() - 4)));
        this.y = 0;

        velX = 0;
        velY = 0;

        moveSpeed = 0;
        state = "ROLLING";

        restartTimer = 120;

        generateTargetTile();
    }

    public void update()
    {
        switch(state)
        {
            case "ROLLING":
                state = "FALLING";
                generateTargetTile();
                double distance = targetX - x;
                double framesForJump = 40;
                velX = distance / framesForJump;
                velY = framesForJump * gravity / 4.0;
                break;
            case "FALLING":
                x += velX;
                y += velY;

                velY -= gravity;

                if(Math.abs(targetX - x) < 10 && Math.abs(targetY - y) < 15)
                {
                    velX = 0;
                    velY = 0;

                    state = "ROLLING";
                }
                if(y - height / 2 > room.getMapHeight()) state = "RECOVERING";
                break;
            case "RECOVERING":
                if(restartTimer > 0) restartTimer--;
                else init();
                break;
        }

        animation.update();
        invincibilityFrames = 10;

        super.update();
    }

    private void generateTargetTile()
    {
        if((int)(Math.random() * 2) < 1)
        {
            targetX = x + room.getWidthOfTile();
        }
        else
        {
            targetX = x - room.getWidthOfTile() * 2;
        }

        if(targetX < 0 || targetX >= room.getMapWidth()) generateTargetTile();
        targetY = y + room.getHeightOfTile();
    }

    public void draw(Graphics2D g2d)
    {
        int drawX = (int) x - width / 2;
        int drawY = (int) y - height / 2;

        animation.draw(g2d, drawX, drawY, width, height);
    }
}

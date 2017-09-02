package components.entity.enemies;

import components.map.rooms.Room;

import java.awt.*;

public class GhiniSpawner extends Enemy
{
    private int spawnTimer;

    public GhiniSpawner(int x, int y, Room room)
    {
        this.x = x;
        this.y = y;

        this.room = room;

        velX = 0;
        velY = 0;

        width = 16;
        height = 16;

        state = "SPAWNING";
        spawnTimer = 0;

        health = -1;
        damage = 0;
    }

    public void update()
    {
        switch(state)
        {
            case "SPAWNING":
                if(Math.abs(room.getLink().getX() - x) <= room.getWidthOfTile() &&
                        Math.abs(room.getLink().getY() - y) <= room.getHeightOfTile() &&
                        spawnTimer == 0 && !room.getLink().isTransitioning())
                {
                    spawnTimer = 120;
                    room.addEnemy(new Ghini((int) x, (int) y, (int) (Math.random() * 8), room, false));
                }
                break;
        }
        if(spawnTimer > 0) spawnTimer--;
        invincibilityFrames = 1;
    }

    public void draw(Graphics2D g2d)
    {

    }
}

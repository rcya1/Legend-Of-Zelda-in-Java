package components.entity.enemies.overworld;

import components.entity.enemies.Enemy;
import components.entity.enemies.KnockbackResistEnemy;
import components.map.rooms.Room;

import java.awt.*;

public class GhiniSpawner extends Enemy implements KnockbackResistEnemy
{
    private int spawnTimer;

    public GhiniSpawner(int x, int y, Room room)
    {
        super(x, y, room, 5000, 0, "SPAWNING", 16, 16);

        velX = 0;
        velY = 0;

        spawnTimer = 0;
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
        invincibilityFrames = 10;

        super.update();
    }

    public void draw(Graphics2D g2d)
    {

    }
}

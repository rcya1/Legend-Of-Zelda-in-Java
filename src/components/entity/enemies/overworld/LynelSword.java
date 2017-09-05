package components.entity.enemies.overworld;

import components.entity.Direction;
import utility.Animation;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LynelSword
{
    private double x;
    private double y;

    private final double velX;
    private final double velY;

    private final int width;
    private final int height;

    private final Direction direction;

    private final int damage;
    private final Animation animation;

    LynelSword(double x, double y, Direction direction)
    {
        this.x = x;
        this.y = y;

        int[] vector = direction.getVector(4 );

        velX = vector[0];
        velY = vector[1];

        this.direction = direction;

        width = 16;
        height = 16;

        damage = 4;

        animation = new Animation(4, true, Images.Enemies.Lynel.LYNEL_SWORD_1, Images.Enemies.Lynel.LYNEL_SWORD_2,
                Images.Enemies.Lynel.LYNEL_SWORD_3, Images.Enemies.Lynel.LYNEL_SWORD_4,
                Images.Enemies.Lynel.LYNEL_SWORD_5, Images.Enemies.Lynel.LYNEL_SWORD_6);
    }

    void update()
    {
        x += velX;
        y += velY;

        animation.update();
    }

    void draw(Graphics2D g2d)
    {
        int drawX = (int) Math.round(x) - width / 2;
        int drawY = (int) Math.round(y) - height / 2;

        AffineTransform transform = g2d.getTransform();
        g2d.rotate(direction.getRadians(), x, y);
        animation.draw(g2d, drawX, drawY, width, height);
        g2d.setTransform(transform);
    }

    Rectangle getRectangle()
    {
        return new Rectangle((int) Math.round(x) - width / 2, (int) Math.round(y) - height / 2,
                width, height);
    }

    int getDamage()
    {
        return damage;
    }

    Direction getDirection()
        {
            return direction;
        }
}

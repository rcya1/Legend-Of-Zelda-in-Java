package components.entity.enemies;

import utility.Animation;
import utility.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ZolaFireball
{
	private double x;
	private double y;

	private final double velX;
	private final double velY;

	private final int width;
	private final int height;

	private final int damage;

	private final double angle;

	private final Animation animation;

	ZolaFireball(double x, double y, double angle)
	{
		this.x = x;
		this.y = y;

		this.angle = angle;

		velX = Math.cos(angle) * 2;
		velY = Math.sin(angle) * 2;

		width = 16;
		height = 16;

		damage = 4;

		animation = new Animation(1, true, Images.Enemies.Zola.ZOLA_FIREBALL_1,
				Images.Enemies.Zola.ZOLA_FIREBALL_2, Images.Enemies.Zola.ZOLA_FIREBALL_3,
				Images.Enemies.Zola.ZOLA_FIREBALL_4, Images.Enemies.Zola.ZOLA_FIREBALL_5,
				Images.Enemies.Zola.ZOLA_FIREBALL_5, Images.Enemies.Zola.ZOLA_FIREBALL_6,
				Images.Enemies.Zola.ZOLA_FIREBALL_7);
	}

	void update()
	{
		x += velX;
		y += velY;

		animation.update();
	}

	public void draw(Graphics2D g2d)
	{
		int drawX = (int) Math.round(x) - width / 2;
		int drawY = (int) Math.round(y) - height / 2;

		animation.draw(g2d, drawX, drawY, width, height);
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

	double getDirection()
	{
		return angle;
	}
}

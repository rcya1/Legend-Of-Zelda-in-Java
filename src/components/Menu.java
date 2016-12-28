package components;

import reference.Images;

import java.awt.*;

public class Menu
{
	private int drawX;
	private int drawY;
	private int drawVelX;
	private int drawVelY;

	private int width;
	private int height;

	public Menu()
	{
		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		width = 256;
		height = 232;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.MENU_TEMP, drawX, drawY, width, height, null);
	}

	public void updateDrawCoordinates()
	{
		drawX += drawVelX;
		drawY += drawVelY;
	}

	public void setDrawCoordinates(int drawX, int drawY)
	{
		this.drawX = drawX;
		this.drawY = drawY;
	}

	public int[] getDrawCoordinates()
	{
		return new int[] {drawX, drawY};
	}

	public void setDrawVector(int drawVelX, int drawVelY)
	{
		this.drawVelX = drawVelX;
		this.drawVelY = drawVelY;
	}
}

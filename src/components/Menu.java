package components;

import utility.Images;

import java.awt.*;

public class Menu
{
	private OverWorld overWorld;

	private int drawX;
	private int drawY;
	private int drawVelX;
	private int drawVelY;

	private int width;
	private int height;

	public Menu(OverWorld overWorld)
	{
		drawX = 0;
		drawY = 0;

		drawVelX = 0;
		drawVelY = 0;

		width = 256;
		height = 232;

		this.overWorld = overWorld;
	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(drawX, drawY, width, height);
		g2d.drawImage(Images.Menu.MENU_TEMP, drawX, drawY, width, height, null);

		//Draw Health Bar
		int xRelativeToMenuOrigin = 176;
		int yRelativeToMenuOrigin = 215;

		for(int i = 0; i < overWorld.getLink().getHealthContainers(); i++)
		{
			int heartImageIndex = 0;
			if(i > 7)
			{
				xRelativeToMenuOrigin = 112;
				yRelativeToMenuOrigin = 206;
			}

			if(overWorld.getLink().getHealth() >= (i + 1) * 8)
			{
				heartImageIndex = 2;
			}
			else if(overWorld.getLink().getHealth() >= (i + 0.5) * 8)
			{
				heartImageIndex = 1;
			}

			switch(heartImageIndex)
			{
			case 0:
				g2d.drawImage(Images.Menu.HEART_EMPTY, drawX + xRelativeToMenuOrigin + i * 8,
						drawY + yRelativeToMenuOrigin, null);
				break;
			case 1:
				g2d.drawImage(Images.Menu.HEART_HALF, drawX + xRelativeToMenuOrigin + i * 8,
						drawY + yRelativeToMenuOrigin, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.HEART_FULL	, drawX + xRelativeToMenuOrigin + i * 8,
						drawY + yRelativeToMenuOrigin, null);
				break;
			default:
				break;
			}
		}
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

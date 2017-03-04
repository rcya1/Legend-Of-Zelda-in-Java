package components;

import utility.Images;

import java.awt.*;

public class Menu
{
	private final OverWorld overWorld;

	private final int width;
	private final int height;

	public Menu(OverWorld overWorld)
	{
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
		g2d.fillRect(0, 0, width, height);
		g2d.drawImage(Images.Menu.MENU_TEMP, 0, 0, width, height, null);

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
				g2d.drawImage(Images.Menu.HEART_EMPTY, xRelativeToMenuOrigin + i * 8,
						yRelativeToMenuOrigin, null);
				break;
			case 1:
				g2d.drawImage(Images.Menu.HEART_HALF, xRelativeToMenuOrigin + i * 8,
						yRelativeToMenuOrigin, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.HEART_FULL, xRelativeToMenuOrigin + i * 8,
						yRelativeToMenuOrigin, null);
				break;
			default:
				break;
			}
		}
	}
}

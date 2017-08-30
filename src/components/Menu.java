package components;

import components.entity.Link;
import components.items.player.BoomerangItem;
import components.items.player.BowItem;
import components.map.World;
import utility.Data;
import utility.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

//Screen that displays health, the triforce and manages the inventory
public class Menu
{
	private final World world;     //The current world that the menu is associated with
	private final int width, height;       //The width/height of the menu
	private int selectedIndex;             //The current index for the selected inventory item

	public Menu(World world)
	{
		//Constants for the menu dimensions
		width = 256;
		height = 232;

		this.world = world;

		selectedIndex = 0;
	}

	//Updates the inventory and sets Link's item
	public void update()
	{
		//Grab the associated world's Link
		Link link = world.getLink();

		//Set Link's item depending on the current stats and the selected index
		switch(selectedIndex)
		{
		case 0:
			if(Data.boomerangLevel == 0) link.setItem(null);
			else if(Data.boomerangLevel == 1) link.setItem(new BoomerangItem());
			else if(Data.boomerangLevel == 2) link.setItem(null); //Placeholder
			break;
		case 1:
			if(Data.hasBombs) link.setItem(null); //Placeholder
			else link.setItem(null);
			break;
		case 2:
			if(Data.hasBow) link.setItem(new BowItem());
			else link.setItem(null);
			break;
		case 3:
			if(Data.candleLevel == 0) link.setItem(null); //Placeholder
			else if(Data.candleLevel == 1) link.setItem(null); //Placeholder
			else if(Data.candleLevel == 2) link.setItem(null); //Placeholder
			break;
		case 4:
			if(Data.hasWhistle) link.setItem(null); //Placeholder
			else link.setItem(null);
			break;
		case 5:
			if(Data.hasBait) link.setItem(null);  //Placeholder
			else link.setItem(null);
			break;
		case 6:
			if(Data.potionLevel == 0) link.setItem(null);
			else if(Data.potionLevel == 1) link.setItem(null); //Placeholder
			else if(Data.potionLevel == 2) link.setItem(null); //Placeholder
			else if(Data.potionLevel == 3) link.setItem(null); //Placeholder
			break;
		case 7:
			if(Data.hasMagicWand) link.setItem(null); //Placeholder
			else link.setItem(null);
			break;
		}
	}

	//Draw the menu
	public void draw(Graphics2D g2d)
	{
		//Draw the background
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, width, height);
		g2d.drawImage(Images.Menu.MENU, 0, 0, width, height, null);

		drawHealthBar(g2d);
		drawInventory(g2d);
	}

	//Draws the health bars
	private void drawHealthBar(Graphics2D g2d)
	{
		int xOrigin = 176;
		int yOrigin = 215;

		for(int i = 0; i < world.getLink().getHealthContainers(); i++)
		{
			int heartImageIndex = 0;
			if(i > 7)
			{
				xOrigin = 112;
				yOrigin = 206;
			}

			if(world.getLink().getHealth() >= (i + 1) * 2)
			{
				heartImageIndex = 2;
			}
			else if(world.getLink().getHealth() >= (i + 0.5) * 2)
			{
				heartImageIndex = 1;
			}

			switch(heartImageIndex)
			{
				case 0:
					g2d.drawImage(Images.Menu.Hearts.HEART_EMPTY, xOrigin + i * 8,
							yOrigin, null);
					break;
				case 1:
					g2d.drawImage(Images.Menu.Hearts.HEART_HALF, xOrigin + i * 8,
							yOrigin, null);
					break;
				case 2:
					g2d.drawImage(Images.Menu.Hearts.HEART_FULL, xOrigin + i * 8,
							yOrigin, null);
					break;
				default:
					break;
			}
		}
	}

	//Draws all inventory items
	private void drawInventory(Graphics2D g2d)
	{
		int xOrigin = 128;
		int yOrigin = 47;

		switch(Data.boomerangLevel)
		{
			case 1:
				g2d.drawImage(Images.Menu.Items.BOOMERANG, xOrigin + 2, yOrigin + 2, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.Items.BOOMERANG_MAGIC, xOrigin + 2, yOrigin + 2, null);
				break;
		}

		if(Data.hasBombs)
		{
			g2d.drawImage(Images.Menu.Items.BOMBS, xOrigin + 26, yOrigin + 2, null);
		}

		if(Data.hasBow)
		{
			g2d.drawImage(Images.Menu.Items.BOW, xOrigin + 50, yOrigin + 2, null);
		}

		switch(Data.arrowLevel)
		{
			case 1:
				g2d.drawImage(Images.Menu.Items.ARROW, xOrigin + 50, yOrigin + 2, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.Items.ARROW_SILVER, xOrigin + 50, yOrigin + 2, null);
				break;
		}

		switch(Data.candleLevel)
		{
			case 1:
				g2d.drawImage(Images.Menu.Items.CANDLE_BLUE, xOrigin + 74, yOrigin + 2, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.Items.CANDLE_RED, xOrigin + 74, yOrigin + 2, null);
				break;
		}

		if(Data.hasWhistle)
		{
			g2d.drawImage(Images.Menu.Items.WHISTLE, xOrigin + 2, yOrigin + 18, null);
		}

		if(Data.hasBait)
		{
			g2d.drawImage(Images.Menu.Items.BAIT, xOrigin + 26, yOrigin + 18, null);
		}

		switch(Data.potionLevel)
		{
			case 1:
				g2d.drawImage(Images.Menu.Items.LETTER, xOrigin + 50, yOrigin + 18, null);
				break;
			case 2:
				g2d.drawImage(Images.Menu.Items.POTION_BLUE, xOrigin + 50, yOrigin + 18, null);
				break;
			case 3:
				g2d.drawImage(Images.Menu.Items.POTION_RED, xOrigin + 50, yOrigin + 18, null);
				break;
		}

		if(Data.hasMagicWand)
		{
			g2d.drawImage(Images.Menu.Items.MAGIC_WAND, xOrigin + 74, yOrigin + 18, null);
		}

		if(selectedIndex < 4)
		{
			g2d.drawImage(Images.Menu.Items.SELECTOR, xOrigin + 2 + selectedIndex * 24,
					yOrigin + 2, null);
		}
		else
		{
			g2d.drawImage(Images.Menu.Items.SELECTOR, xOrigin + 2 + (selectedIndex - 4) * 24,
					yOrigin + 18, null);
		}
	}

	//Handles key inputs
	//W and S move across rows, so the index is moved by 4
	public void keyPressed(int key)
	{
		switch(key)
		{
		case KeyEvent.VK_W:
			if(selectedIndex >= 4) selectedIndex -= 4;
			break;
		case KeyEvent.VK_S:
			if(selectedIndex < 4) selectedIndex += 4;
			break;
		case KeyEvent.VK_A:
			if(selectedIndex > 0) selectedIndex--;
			break;
		case KeyEvent.VK_D:
			if(selectedIndex < 7) selectedIndex++;
			break;
		}
	}
}

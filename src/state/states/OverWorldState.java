package state.states;

import components.Menu;
import components.OverWorld;
import entity.Link;
import main.GamePanel;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class OverWorldState extends State
{
	private Link link;
	private OverWorld overWorld;
	private Menu menu;

	private String state;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		state = "OVERWORLD";

		overWorld = new OverWorld(32, 12);
		overWorld.loadTiles("/tileMaps/test.txt");
		overWorld.loadEnemies("/tileMaps/testE.txt");
		overWorld.setDrawCoordinates(0, 48);

		menu = new Menu(overWorld);
		menu.setDrawCoordinates(0, -184);

		link = overWorld.getLink();
	}

	public void update()
	{
		overWorld.updateDrawCoordinates();
		menu.updateDrawCoordinates();

		switch(state)
		{
		case "OVERWORLD":
			overWorld.update();
			break;
		case "MENU":
			menu.update();
			break;
		case "TRANSITION":
			if(overWorld.getDrawCoordinates()[1] == 48)
			{
				state = "OVERWORLD";
				overWorld.setDrawVector(0, 0);
				menu.setDrawVector(0, 0);
			}
			else if(overWorld.getDrawCoordinates()[1] == 240)
			{
				state = "MENU";
				overWorld.setDrawVector(0, 0);
				menu.setDrawVector(0, 0);
			}
			break;
		default:
			break;
		}
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, -GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.HEIGHT * 2);

		overWorld.draw(g2d);
		menu.draw(g2d);
	}

	public void keyPressed(int key)
	{
		link.setKeyVariable(key, true);

		if(key == KeyEvent.VK_ENTER)
		{
			int transitionSpeed = 4;

			switch(state)
			{
			case "OVERWORLD":
				overWorld.setDrawVector(0, transitionSpeed);
				menu.setDrawVector(0, transitionSpeed);
				state = "TRANSITION";
				break;
			case "MENU":
				overWorld.setDrawVector(0, -transitionSpeed);
				menu.setDrawVector(0, -transitionSpeed);
				state = "TRANSITION";
				break;
			default:
				break;
			}
		}
	}

	public void keyReleased(int key)
	{
		link.setKeyVariable(key, false);
	}
}

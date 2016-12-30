package state.states;

import main.GamePanel;
import reference.Images;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleState extends State
{
	private int timer;

	public TitleState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		timer = 0;
	}

	public void update()
	{
		if(timer >= 5 * GamePanel.FPS)
		{
			stateManager.setState(StateManager.DISPLAY_ITEMS_STATE);
		}

		timer++;
	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.Beginning.TITLE, 0, 0, null);
	}

	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER)
		{
			stateManager.setState(StateManager.SELECT_SAVE_STATE);
		}
	}

	public void keyReleased(int key)
	{

	}
}

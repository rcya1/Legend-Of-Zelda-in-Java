package state.states;

import reference.Images;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SelectSaveState extends State
{
	public SelectSaveState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{

	}

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.Beginning.SELECT_SAVE, 0, 0, null);
	}

	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER)
		{
			stateManager.setState(StateManager.OVER_WORLD_STATE);
		}
	}

	public void keyReleased(int key)
	{

	}
}

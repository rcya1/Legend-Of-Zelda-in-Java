package state.states;

import main.GamePanel;
import state.State;
import state.StateManager;
import utility.Images;
import utility.SoundPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DisplayItemsState extends State
{
	private int timer;

	public DisplayItemsState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{

	}

	public void update()
	{
		if(timer >= 10 * GamePanel.FPS)
		{
			stateManager.setState(StateManager.TITLE_STATE);
		}

		timer++;
	}

	public void draw(Graphics2D g2d)
	{
		g2d.drawImage(Images.Beginning.DISPLAY_ITEMS, 0, 0, null);
	}

	public void keyPressed(int key)
	{
		if(key == KeyEvent.VK_ENTER)
		{
			stateManager.setState(StateManager.SELECT_SAVE_STATE);
			SoundPlayer.INTRO.stop();
		}
	}

	public void keyReleased(int key)
	{

	}
}

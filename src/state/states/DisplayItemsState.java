package state.states;

import entity.Animation;
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

	private Animation animation;
	private int imageX;
	private int imageY;

	public DisplayItemsState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		animation = new Animation(6, true, Images.Beginning.DISPLAY_ITEMS_1,
				Images.Beginning.DISPLAY_ITEMS_2);

		imageX = 0;
		imageY = GamePanel.HEIGHT;
	}

	public void update()
	{
		if(!SoundPlayer.INTRO.isPlaying())
		{
			stateManager.setState(StateManager.TITLE_STATE);
		}

		timer++;

		animation.update();
		if(timer >= 150 && imageY > 0)
		{
			if(timer % 2 == 0) imageY--;
		}
		if(timer >= 900 && imageY > GamePanel.HEIGHT - animation.getHeight())
		{
			if(timer % 2 == 0) imageY--;
		}
	}

	public void draw(Graphics2D g2d)
	{
		animation.draw(g2d, imageX, imageY, animation.getWidth(), animation.getHeight());
		//Don't resize the image just draw it and make some parts go offscreen
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

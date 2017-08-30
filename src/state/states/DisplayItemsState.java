package state.states;

import main.GamePanel;
import state.State;
import state.StateManager;
import utility.Animation;
import utility.Images;
import utility.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DisplayItemsState extends State
{
	private int timer;                 //Timer for when to cut back to the title screen and when to start moving the image

	private Animation animation;       //Main image
	private int imageX, imageY;        //The coordinates for where the animation should be drawn to move it

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
		//Play music
		if(!SoundManager.INTRO.isPlaying())
		{
			stateManager.setState(StateManager.TITLE_STATE);
		}

		timer++;

		animation.update();

		//Moves the image down after pauses.
		//First condition stops when the image gets pass the story
		//Second starts after the story, and continues throughout the entire image
		if((timer >= 150 && imageY > 0) || (timer >= 900 && imageY > GamePanel.HEIGHT - animation.getHeight()))
		{
			if(timer % 2 == 0) imageY--;
		}
	}

	public void draw(Graphics2D g2d)
	{
		//Draws the animation with some parts offscreen
		animation.draw(g2d, imageX, imageY, animation.getWidth(), animation.getHeight());
		//Don't resize the image just draw it and make some parts go offscreen
	}

	public void keyPressed(int key)
	{
		//Check if the player presses start
		if(key == KeyEvent.VK_ENTER)
		{
			stateManager.setState(StateManager.SELECT_SAVE_STATE);
			SoundManager.INTRO.stop();
		}
	}

	public void keyReleased(int key)
	{

	}
}

package state.states;

import main.GamePanel;
import state.State;
import state.StateManager;
import utility.Animation;
import utility.Images;
import utility.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleState extends State
{
	private int timer;                //Timer for when the screen will fade to black
	private int fade;                 //Alpha level for the black fade

	private Animation mainAnimation, waterfallAnimation, wavesAnimation; //Animations

	public TitleState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		timer = 0;
		fade = 0;

		mainAnimation = new Animation(9, true, Images.Beginning.Title.TITLE_MAIN_1,
				Images.Beginning.Title.TITLE_MAIN_2, Images.Beginning.Title.TITLE_MAIN_3,
				Images.Beginning.Title.TITLE_MAIN_4, Images.Beginning.Title.TITLE_MAIN_5,
				Images.Beginning.Title.TITLE_MAIN_6);
		waterfallAnimation = new Animation(5, true, Images.Beginning.Title.WATERFALL_1,
				Images.Beginning.Title.WATERFALL_2, Images.Beginning.Title.WATERFALL_3,
				Images.Beginning.Title.WATERFALL_4);
		wavesAnimation = new Animation(0, true, Images.Beginning.Title.WAVES_1,
				Images.Beginning.Title.WAVES_2, Images.Beginning.Title.WAVES_3,
				Images.Beginning.Title.WAVES_4, Images.Beginning.Title.WAVES_5,
				Images.Beginning.Title.WAVES_6, Images.Beginning.Title.WAVES_7,
				Images.Beginning.Title.WAVES_8, Images.Beginning.Title.WAVES_9);

		if(!SoundManager.INTRO.isPlaying()) SoundManager.INTRO.play();
		SoundManager.INTRO.setVolume(-10);
	}

	public void update()
	{
		//Checks if screen has been active for 9 seconds
		if(timer >= (9 * GamePanel.FPS))
		{
			//If the fade is not black, then add to it
			if(fade < 255) fade += 4;
			//If the timer reaches 11 seconds, then move to the display items
			else if(timer >= (11 * GamePanel.FPS))
			{
				stateManager.setState(StateManager.DISPLAY_ITEMS_STATE);
			}
		}

		//Constrain fade values
		if(fade < 0) fade = 0;
		if(fade > 255) fade = 255;

		timer++;

		mainAnimation.update();
		waterfallAnimation.update();
		wavesAnimation.update();
	}

	public void draw(Graphics2D g2d)
	{
		//Draw all animations
		mainAnimation.draw(g2d, 0, 0, Images.Beginning.Title.TITLE_MAIN_WIDTH,
				Images.Beginning.Title.TITLE_MAIN_HEIGHT);
		waterfallAnimation.draw(g2d, 80, 178, Images.Beginning.Title.WATERFALL_WIDTH,
				Images.Beginning.Title.WATERFALL_HEIGHT);
		wavesAnimation.draw(g2d, 80, 178, Images.Beginning.Title.WATERFALL_WIDTH,
				Images.Beginning.Title.WATERFALL_HEIGHT);

		//Draw the fade
		g2d.setColor(new Color(0, 0, 0, fade));
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}

	public void keyPressed(int key)
	{
		//Check if the player has pressed start
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

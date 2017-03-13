package state.states;

import utility.Animation;
import main.GamePanel;
import state.State;
import state.StateManager;
import utility.Images;
import utility.SoundPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleState extends State
{
	private int timer;
	private int fade;

	private Animation mainAnimation;
	private Animation waterfallAnimation;
	private Animation wavesAnimation;

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

		if(!SoundPlayer.INTRO.isPlaying()) SoundPlayer.INTRO.play();
		SoundPlayer.INTRO.setVolume(-10);
	}

	public void update()
	{
		if(timer >= (9 * GamePanel.FPS))
		{
			if(fade < 255) fade += 4;
			else if(timer >= (11 * GamePanel.FPS))
			{
				stateManager.setState(StateManager.DISPLAY_ITEMS_STATE);
			}
		}

		if(fade < 0) fade = 0;
		if(fade > 255) fade = 255;

		timer++;

		mainAnimation.update();
		waterfallAnimation.update();
		wavesAnimation.update();
	}

	public void draw(Graphics2D g2d)
	{
		mainAnimation.draw(g2d, 0, 0, Images.Beginning.Title.TITLE_MAIN_WIDTH,
				Images.Beginning.Title.TITLE_MAIN_HEIGHT);
		waterfallAnimation.draw(g2d, 80, 178, Images.Beginning.Title.WATERFALL_WIDTH,
				Images.Beginning.Title.WATERFALL_HEIGHT);
		wavesAnimation.draw(g2d, 80, 178, Images.Beginning.Title.WATERFALL_WIDTH,
				Images.Beginning.Title.WATERFALL_HEIGHT);

		g2d.setColor(new Color(0, 0, 0, fade));
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
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

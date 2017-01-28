package state;

import state.states.DisplayItemsState;
import state.states.OverWorldState;
import state.states.SelectSaveState;
import state.states.TitleState;

import java.awt.*;

public class StateManager
{
	private static final int NUMBER_OF_STATES = 4;

	public static final int TITLE_STATE = 0;
	public static final int DISPLAY_ITEMS_STATE = 1;
	public static final int SELECT_SAVE_STATE = 2;
	public static final int OVER_WORLD_STATE = 3;

	/* Beginning - TitleScreen
	 * Wait - Items
	 * Press Start - Select Save
	 * Press Start - OldOverWorld
	 */

	private final State[] states;
	private int currentState;

	public StateManager()
	{
		states = new State[NUMBER_OF_STATES];
		currentState = TITLE_STATE;
		loadState(currentState);
	}

	public void update()
	{
		if(states[currentState] != null) states[currentState].update();
	}

	public void draw(Graphics2D g2d)
	{
		if(states[currentState] != null) states[currentState].draw(g2d);
	}

	private void loadState(int state)
	{
		if(state == TITLE_STATE) states[state] = new TitleState(this);
		if(state == DISPLAY_ITEMS_STATE) states[state] = new DisplayItemsState(this);
		if(state == SELECT_SAVE_STATE) states[state] = new SelectSaveState(this);
		if(state == OVER_WORLD_STATE) states[state] = new OverWorldState(this);
	}

	public void setState(int state)
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		states[currentState].init();
	}

	private void unloadState(int state)
	{
		states[state] = null;
	}

	public void keyPressed(int key)
	{
		states[currentState].keyPressed(key);
	}

	public void keyReleased(int key)
	{
		states[currentState].keyReleased(key);
	}
}

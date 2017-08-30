package state;

import state.states.DisplayItemsState;
import state.states.GameState;
import state.states.SelectSaveState;
import state.states.TitleState;

import java.awt.*;

//Manages the state for the game
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

	private final State[] states;     //An array holding the states
	private int currentState;         //ID of the current state of the game

	public StateManager()
	{
		states = new State[NUMBER_OF_STATES];
		currentState = TITLE_STATE;
		loadState(currentState);
	}

	//Updates the current state
	public void update()
	{
		if(states[currentState] != null) states[currentState].update();
	}

	//Draws the current state
	public void draw(Graphics2D g2d)
	{
		if(states[currentState] != null) states[currentState].draw(g2d);
	}

	//Sets the state depending on the given id
	public void setState(int state)
	{
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}

	//Deallocates the removed state
	private void unloadState(int state)
	{
		states[state] = null;
	}

	//Loads a different state depending on the given id
	private void loadState(int state)
	{
		if(state == TITLE_STATE) states[state] = new TitleState(this);
		if(state == DISPLAY_ITEMS_STATE) states[state] = new DisplayItemsState(this);
		if(state == SELECT_SAVE_STATE) states[state] = new SelectSaveState(this);
		if(state == OVER_WORLD_STATE) states[state] = new GameState(this);
	}

	//Sends the key press to the current state
	public void keyPressed(int key)
	{
		states[currentState].keyPressed(key);
	}
	public void keyReleased(int key)
	{
		states[currentState].keyReleased(key);
	}
}

package state;

import state.states.MenuState;
import state.states.OverWorldState;

import java.awt.*;

public class StateManager
{
	private static final int NUMBER_OF_STATES = 3;

	private static final int MENU_STATE = 0;
	private static final int OVER_WORLD_STATE = 1;

	private State[] states;
	private int currentState;

	public StateManager()
	{
		states = new State[NUMBER_OF_STATES];
		currentState = OVER_WORLD_STATE;
		loadState(currentState);
	}

	public void draw(Graphics2D g2d)
	{
		if(states[currentState] != null) states[currentState].draw(g2d);
	}

	public void keyPressed(int k)
	{
		states[currentState].keyPressed(k);
	}

	public void keyReleased(int k)
	{
		states[currentState].keyReleased(k);
	}

	private void loadState(int state)
	{
		if(state == MENU_STATE) states[state] = new MenuState(this);
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

	public void update()
	{
		if(states[currentState] != null) states[currentState].update();
	}
}

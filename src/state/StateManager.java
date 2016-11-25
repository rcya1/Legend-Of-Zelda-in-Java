package state;

import state.states.MenuState;

import java.awt.*;

public class StateManager
{
	public static final int NUMBER_OF_STATES = 1;

	public static final int MENU_STATE = 0;

	private State[] states;
	private int currentState;

	public StateManager()
	{
		states = new State[NUMBER_OF_STATES];
		currentState = MENU_STATE;
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

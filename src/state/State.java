package state;

import java.awt.*;

public abstract class State
{
	protected StateManager stateManager;

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);

	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

}

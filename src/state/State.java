package state;

import java.awt.*;

public abstract class State
{
	protected StateManager stateManager;

	public abstract void draw(Graphics2D g2d);
	public abstract void init();
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	public abstract void update();
}

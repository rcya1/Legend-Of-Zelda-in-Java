package state.states;

import entity.Link;
import main.GamePanel;
import state.State;
import state.StateManager;

import java.awt.*;

public class OverWorldState extends State
{
	private Link link;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		link.draw(g2d);
	}

	public void init()
	{
		link = new Link();
	}

	public void keyPressed(int key)
	{
		link.keyPressed(key);
	}

	public void keyReleased(int key)
	{
		link.keyReleased(key);
	}

	public void update()
	{
		link.update();
	}
}

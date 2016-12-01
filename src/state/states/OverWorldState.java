package state.states;

import entity.Link;
import main.GamePanel;
import map.TileMap;
import state.State;
import state.StateManager;

import java.awt.*;

public class OverWorldState extends State
{
	private Link link;

	private TileMap tileMap;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		tileMap = new TileMap(4, 4);
		tileMap.loadTiles("/tileMaps/test.txt");
		link = new Link();
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		tileMap.draw(g2d);
		link.draw(g2d);
	}



	public void keyPressed(int key)
	{
		link.setKeyVariables(key, true);
	}

	public void keyReleased(int key)
	{
		link.setKeyVariables(key, false);
	}

	public void update()
	{
		link.update();
	}
}

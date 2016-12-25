package state.states;

import entity.Link;
import main.GamePanel;
import map.TileMap;
import state.State;
import state.StateManager;

import java.awt.*;
import java.awt.event.KeyEvent;

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
		tileMap = new TileMap(32, 12);
		tileMap.loadTiles("/tileMaps/test.txt");
		tileMap.loadEnemies("/tileMaps/testE.txt");

		link = tileMap.getLink();
	}

	public void update()
	{
		tileMap.update();
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		tileMap.draw(g2d);
	}

	public void keyPressed(int key)
	{
		link.setKeyVariable(key, true);
		if(key == KeyEvent.VK_PERIOD)
		{
			tileMap.setVector(4, 0);
			link.setTransitionVector(1, 0);
		}
		if(key == KeyEvent.VK_SLASH)
		{
			tileMap.setVector(-4, 0);
			link.setTransitionVector(-1, 0);
		}
	}

	public void keyReleased(int key)
	{
		link.setKeyVariable(key, false);
	}
}

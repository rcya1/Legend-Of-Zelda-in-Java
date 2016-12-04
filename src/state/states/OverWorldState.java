package state.states;

import entity.Direction;
import entity.Link;
import entity.enemies.Enemy;
import entity.enemies.Octorok;
import main.GamePanel;
import map.TileMap;
import state.State;
import state.StateManager;

import java.awt.*;
import java.util.ArrayList;

public class OverWorldState extends State
{
	private Link link;

	private TileMap tileMap;

	private ArrayList<Enemy> enemies;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		tileMap = new TileMap(12, 12);
		tileMap.loadTiles("/tileMaps/test.txt");
		link = new Link(tileMap);

		enemies = new ArrayList<>();
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

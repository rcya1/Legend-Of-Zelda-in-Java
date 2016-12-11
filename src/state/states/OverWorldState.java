package state.states;

import entity.Animation;
import entity.AnimationObject;
import entity.Link;
import entity.enemies.Enemy;
import main.GamePanel;
import map.TileMap;
import reference.Images;
import state.State;
import state.StateManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class OverWorldState extends State
{
	private Link link;

	private TileMap tileMap;

	private ArrayList<Enemy> enemies;
	private ArrayList<AnimationObject> animations;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		tileMap = new TileMap(16, 12);
		tileMap.loadTiles("/tileMaps/test.txt");
		tileMap.loadEnemies("/tileMaps/testE.txt");

		link = new Link(tileMap);

		enemies = tileMap.getEnemies();
		animations = tileMap.getAnimations();
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
		tileMap.setVector(-2, 0);
	}

	public void keyReleased(int key)
	{
		link.setKeyVariables(key, false);
	}

	public void update()
	{
		tileMap.update();

		link.update();

		Iterator enemyIterator = enemies.iterator();
		while(enemyIterator.hasNext())
		{
			Enemy enemy = (Enemy) enemyIterator.next();

			if(link.getSword() != null) enemy.setSword(link.getSword());
			else enemy.setSword(null);

			enemy.update();

			if(enemy.getDestroyFlag())
			{
				enemyIterator.remove();
				animations.add(new AnimationObject(enemy.getX() - enemy.getWidth() / 2, enemy.getY() - enemy.getHeight() / 2,
						new Animation(3, false, Images.Enemies.ENEMY_DEATH, 16, 16)
				));
			}
		}

		Iterator animationIterator = animations.iterator();
		while(animationIterator.hasNext())
		{
			AnimationObject animationObject = (AnimationObject) animationIterator.next();
			animationObject.update();

			if(animationObject.getAnimation().getIndex() == -1)
			{
				animationIterator.remove();
			}
		}
	}
}

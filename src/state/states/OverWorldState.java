package state.states;

import components.Menu;
import components.entity.Link;
import components.map.OverWorld;
import components.map.rooms.SecretRoom;
import main.GamePanel;
import state.State;
import state.StateManager;
import utility.SoundPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class OverWorldState extends State
{
	private Link link;
	private OverWorld overWorld;
	private Menu menu;

	private String state;

	private int midline;
	private int midlineVel;

	public OverWorldState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		state = "OVERWORLD";

		overWorld = new OverWorld(88, "/tileMaps/Overworld.txt", "/tileMaps/Overworld.xml",
				256, 88);

		menu = new Menu(overWorld);

		midline = 48;

		link = overWorld.getLink();
	}

	public void update()
	{
		midline += midlineVel;

		switch(state)
		{
		case "OVERWORLD":
			if(link.getRoom() instanceof SecretRoom)
			{
				if(SoundPlayer.OVERWORLD.isPlaying()) SoundPlayer.OVERWORLD.stop();
				((SecretRoom) link.getRoom()).update();
			}
			else overWorld.update();
			break;
		case "MENU":
			menu.update();
			break;
		case "TRANSITION":
			if(midline == 48)
			{
				state = "OVERWORLD";
				midlineVel = 0;
			}
			else if(midline == 216)
			{
				state = "MENU";
				midlineVel = 0;
			}
			break;
		default:
			break;
		}
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, -GamePanel.HEIGHT, GamePanel.WIDTH, GamePanel.HEIGHT * 2);

		AffineTransform transform = g2d.getTransform();

		g2d.translate(0, midline);
		if(link.getRoom() instanceof SecretRoom)
		{
			((SecretRoom) link.getRoom()).draw(g2d);
		}
		else overWorld.draw(g2d);

		g2d.setTransform(transform);

		g2d.translate(0, midline - 232);
		menu.draw(g2d);

		g2d.setTransform(transform);
	}
	
	public void keyPressed(int key)
	{
		link.setKeyVariable(key, true);

		if(key == KeyEvent.VK_ENTER)
		{
			switch(state)
			{
			case "OVERWORLD":
				midlineVel = 4;
				state = "TRANSITION";
				break;
			case "MENU":
				midlineVel = -4;
				state = "TRANSITION";
				break;
			default:
				break;
			}
		}

		if(state.equals("MENU")) menu.keyPressed(key);
	}

	public void keyReleased(int key)
	{
		link.setKeyVariable(key, false);
	}
}

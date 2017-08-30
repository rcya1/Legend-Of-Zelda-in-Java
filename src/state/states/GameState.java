package state.states;

import components.Menu;
import components.entity.Link;
import components.map.World;
import components.map.rooms.SecretRoom;
import main.GamePanel;
import state.State;
import state.StateManager;
import utility.SoundManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class GameState extends State
{
	private Link link;
	private World world;
	private Menu menu;

	private String state;                //Whether the game is in OVERWORLD, MENU, or TRANSITION

	private int midline;                 //Location of the seam between the menu and the overworld
	private int midlineVel;              //Velocity of the seam

	public GameState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		state = "OVERWORLD";

		world = new World(88, "/tileMaps/Overworld.txt", "/tileMaps/Overworld.xml",
				256, 88);
		menu = new Menu(world);

		midline = 48;

		link = world.getLink();
	}

	public void update()
	{
		midline += midlineVel;

		switch(state)
		{
		case "OVERWORLD":
			//Checks if link is within a secret room
			if(link.getRoom() instanceof SecretRoom)
			{
				//Stops the overworld music
				if(SoundManager.OVERWORLD.isPlaying()) SoundManager.OVERWORLD.stop();
				//Updates the room that link is within
				((SecretRoom) link.getRoom()).update();
			}
			else world.update();
			break;
		case "MENU":
			menu.update();
			break;
		case "TRANSITION":
			//If the midline is in a certain position, then the state is set, and the midline stops moving
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

		//Draws either the overworld or the room link is in at the correct posotion
		g2d.translate(0, midline);
		if(link.getRoom() instanceof SecretRoom)
		{
			((SecretRoom) link.getRoom()).draw(g2d);
		}
		else world.draw(g2d);

		g2d.setTransform(transform);

		//Draws the menu in the correct position
		g2d.translate(0, midline - 232);
		menu.draw(g2d);

		g2d.setTransform(transform);
	}
	
	public void keyPressed(int key)
	{
		//Tells Link which keys are being pressed
		link.setKeyVariable(key, true);

		//If the menu button is pressed, then start moving the midline and adjust the state
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

		//Tells the mnu which keys are being pressed
		if(state.equals("MENU")) menu.keyPressed(key);
	}

	public void keyReleased(int key)
	{
		//Tels Link which keys are released
		link.setKeyVariable(key, false);
	}
}

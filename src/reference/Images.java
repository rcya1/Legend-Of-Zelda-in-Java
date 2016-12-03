package reference;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images
{
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;

	private static final BufferedImage LINK_WALK = loadSingleImage("/sprites/link/LinkWalk.png");
	private static final BufferedImage LINK_ATTACK_SWORD = loadSingleImage("/sprites/link/LinkAttackSword.png");

	public static final BufferedImage SWORD = loadSingleImage("/sprites/link/items/Sword.png");

	public static final BufferedImage LINK_DOWN = LINK_WALK != null ?
			LINK_WALK.getSubimage(0, 0, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_DOWN_2 = LINK_WALK != null ?
			LINK_WALK.getSubimage(WIDTH, 0, WIDTH, HEIGHT) :
			null;


	public static final BufferedImage LINK_UP = LINK_WALK != null ?
			LINK_WALK.getSubimage(0, HEIGHT, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_UP_2 = LINK_WALK != null ?
			LINK_WALK.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT) :
			null;


	public static final BufferedImage LINK_RIGHT = LINK_WALK != null ?
			LINK_WALK.getSubimage(0, HEIGHT * 2, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_RIGHT_2 = LINK_WALK != null ?
			LINK_WALK.getSubimage(WIDTH, HEIGHT * 2, WIDTH, HEIGHT) :
			null;


	public static final BufferedImage LINK_LEFT = LINK_WALK != null ?
			LINK_WALK.getSubimage(0, HEIGHT * 3, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_LEFT_2 = LINK_WALK != null ?
			LINK_WALK.getSubimage(WIDTH, HEIGHT * 3, WIDTH, HEIGHT) :
			null;



	public static final BufferedImage LINK_ATTACK_SWORD_UP = LINK_ATTACK_SWORD != null ?
			LINK_ATTACK_SWORD.getSubimage(0, 0, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_ATTACK_SWORD_RIGHT = LINK_ATTACK_SWORD != null ?
			LINK_ATTACK_SWORD.getSubimage(WIDTH, 0, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_ATTACK_SWORD_DOWN = LINK_ATTACK_SWORD != null ?
			LINK_ATTACK_SWORD.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT) :
			null;
	public static final BufferedImage LINK_ATTACK_SWORD_LEFT = LINK_ATTACK_SWORD != null ?
			LINK_ATTACK_SWORD.getSubimage(WIDTH * 3, 0, WIDTH, HEIGHT) :
			null;



	private static BufferedImage loadSingleImage(String string)
	{
		try
		{
			Image image = ImageIO.read(Images.class.getResourceAsStream(string));
			return (BufferedImage) image;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static class Blocks
	{
		private static final BufferedImage TILE_ROCKS = loadSingleImage("/sprites/blocks/RockTiles.png");

		public static final BufferedImage EMPTY = loadSingleImage("/sprites/blocks/Empty.png");

		public static final BufferedImage ROCKS_UP_LEFT = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(0, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_UP = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(WIDTH, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_UP_RIGHT = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_DOWN_LEFT = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(0, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_DOWN = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_DOWN_RIGHT = TILE_ROCKS != null ?
				TILE_ROCKS.getSubimage(WIDTH * 2, HEIGHT, WIDTH, HEIGHT) :
				null;

	}
}

package reference;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images
{
	private static final int WIDTH = 16;
	private static final int HEIGHT = 16;

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

	public static class Link
	{
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
	}

	public static class Blocks
	{
		public static final BufferedImage EMPTY = loadSingleImage("/sprites/blocks/Empty.png");

		private static final BufferedImage TILE_ROCKS_GREEN = loadSingleImage("/sprites/blocks/rocks/RockGreenTiles.png");
		private static final BufferedImage TILE_ROCKS_ORANGE = loadSingleImage("/sprites/blocks/rocks/RockOrangeTiles.png");

		public static final BufferedImage ROCKS_GREEN_UP_LEFT = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(0, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_GREEN_UP = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(WIDTH, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_GREEN_UP_RIGHT = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_GREEN_DOWN_LEFT = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(0, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_GREEN_DOWN = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_GREEN_DOWN_RIGHT = TILE_ROCKS_GREEN != null ?
				TILE_ROCKS_GREEN.getSubimage(WIDTH * 2, HEIGHT, WIDTH, HEIGHT) :
				null;

		public static final BufferedImage ROCKS_ORANGE_UP_LEFT = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(0, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_ORANGE_UP = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(WIDTH, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_ORANGE_UP_RIGHT = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_ORANGE_DOWN_LEFT = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(0, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_ORANGE_DOWN = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT) :
				null;
		public static final BufferedImage ROCKS_ORANGE_DOWN_RIGHT = TILE_ROCKS_ORANGE != null ?
				TILE_ROCKS_ORANGE.getSubimage(WIDTH * 2, HEIGHT, WIDTH, HEIGHT) :
				null;

		public static final BufferedImage ROCKS_GREEN_SINGLE = loadSingleImage("/sprites/blocks/rocks/RockGreenSingle.png");
		public static final BufferedImage ROCKS_ORANGE_SINGLE = loadSingleImage("/sprites/blocks/rocks/RockOrangeSingle.png");
	}

	public static class Enemies
	{
		public static final BufferedImage ENEMY_DEATH = loadSingleImage("/sprites/enemies/Death.png");

		private static final BufferedImage OCTOROK_SPRITES = loadSingleImage("/sprites/enemies/octorok/OctorokSprites.png");

		public static final BufferedImage OCTOROK = OCTOROK_SPRITES.getSubimage(0, 0, WIDTH, HEIGHT);
		public static final BufferedImage OCTOROK_2 = OCTOROK_SPRITES.getSubimage(WIDTH, 0, WIDTH, HEIGHT);

		public static final BufferedImage OCTOROK_PELLET = loadSingleImage("/sprites/enemies/octorok/Pellet.png");
	}
}

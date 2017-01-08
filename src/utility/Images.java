package utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images
{
	private static final int TILE_WIDTH = 16;
	private static final int TILE_HEIGHT = 16;

	private static final int HEART_WIDTH = 8;
	private static final int HEART_HEIGHT = 8;

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

	public static class Beginning
	{
		public static final BufferedImage DISPLAY_ITEMS_1 = loadSingleImage("/textures/beginning/displayItems/DisplayItems1.png");
		public static final BufferedImage DISPLAY_ITEMS_2 = loadSingleImage("/textures/beginning/displayItems/DisplayItems2.png");

		public static final BufferedImage SELECT_SAVE = loadSingleImage("/textures/beginning/SelectSave.png");

		public static class Title
		{
			public static final int TITLE_MAIN_WIDTH = 256;
			public static final int TITLE_MAIN_HEIGHT = 240;

			private static final BufferedImage TITLE_MAIN = loadSingleImage("/textures/beginning/title/TitleMain.png");
			public static final BufferedImage TITLE_MAIN_1 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(0, 0, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;
			public static final BufferedImage TITLE_MAIN_2 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(TITLE_MAIN_WIDTH, 0, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;
			public static final BufferedImage TITLE_MAIN_3 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(TITLE_MAIN_WIDTH * 2, 0, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;
			public static final BufferedImage TITLE_MAIN_4 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(0, TITLE_MAIN_HEIGHT, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;
			public static final BufferedImage TITLE_MAIN_5 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;
			public static final BufferedImage TITLE_MAIN_6 = TITLE_MAIN != null ?
					TITLE_MAIN.getSubimage(TITLE_MAIN_WIDTH * 2, TITLE_MAIN_HEIGHT, TITLE_MAIN_WIDTH, TITLE_MAIN_HEIGHT) :
					null;

			public static final int WATERFALL_WIDTH = 32;
			public static final int WATERFALL_HEIGHT = 64;

			private static final BufferedImage WATERFALL = loadSingleImage("/textures/beginning/title/Waterfall.png");
			public static final BufferedImage WATERFALL_1 = WATERFALL != null ?
					WATERFALL.getSubimage(0, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WATERFALL_2 = WATERFALL != null ?
					WATERFALL.getSubimage(WATERFALL_WIDTH, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WATERFALL_3 = WATERFALL != null ?
					WATERFALL.getSubimage(WATERFALL_WIDTH * 2, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WATERFALL_4 = WATERFALL != null ?
					WATERFALL.getSubimage(WATERFALL_WIDTH * 3, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;

			private static final BufferedImage WAVES = loadSingleImage("/textures/beginning/title/Waves.png");
			public static final BufferedImage WAVES_1 = WAVES != null ?
					WAVES.getSubimage(0, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_2 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_3 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH * 2, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_4 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH * 3, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_5 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH * 4, 0, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_6 = WAVES != null ?
					WAVES.getSubimage(0, WATERFALL_HEIGHT, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_7 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH, WATERFALL_HEIGHT, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_8 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH * 2, WATERFALL_HEIGHT, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
			public static final BufferedImage WAVES_9 = WAVES != null ?
					WAVES.getSubimage(WATERFALL_WIDTH * 3, WATERFALL_HEIGHT, WATERFALL_WIDTH, WATERFALL_HEIGHT) :
					null;
		}
	}

	public static class Link
	{
		private static final BufferedImage LINK_WALK = loadSingleImage("/textures/sprites/link/LinkWalk.png");
		private static final BufferedImage LINK_ATTACK_SWORD = loadSingleImage("/textures/sprites/link/LinkAttackSword.png");

		public static final BufferedImage SWORD = loadSingleImage("/textures/sprites/link/items/Sword.png");

		public static final BufferedImage LINK_DOWN = LINK_WALK != null ?
				LINK_WALK.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_DOWN_2 = LINK_WALK != null ?
				LINK_WALK.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;


		public static final BufferedImage LINK_UP = LINK_WALK != null ?
				LINK_WALK.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_UP_2 = LINK_WALK != null ?
				LINK_WALK.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
				null;


		public static final BufferedImage LINK_RIGHT = LINK_WALK != null ?
				LINK_WALK.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_RIGHT_2 = LINK_WALK != null ?
				LINK_WALK.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
				null;


		public static final BufferedImage LINK_LEFT = LINK_WALK != null ?
				LINK_WALK.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_LEFT_2 = LINK_WALK != null ?
				LINK_WALK.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
				null;



		public static final BufferedImage LINK_ATTACK_SWORD_UP = LINK_ATTACK_SWORD != null ?
				LINK_ATTACK_SWORD.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ATTACK_SWORD_RIGHT = LINK_ATTACK_SWORD != null ?
				LINK_ATTACK_SWORD.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ATTACK_SWORD_DOWN = LINK_ATTACK_SWORD != null ?
				LINK_ATTACK_SWORD.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ATTACK_SWORD_LEFT = LINK_ATTACK_SWORD != null ?
				LINK_ATTACK_SWORD.getSubimage(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
	}

	public static class Blocks
	{
		public static final BufferedImage EMPTY = loadSingleImage("/textures/sprites/blocks/Empty.png");
		public static final BufferedImage ENTRANCE = loadSingleImage("/textures/sprites/blocks/Entrance.png");

		public static class Rocks
		{
			private static final BufferedImage TILE_ROCKS_GREEN = loadSingleImage("/textures/sprites/blocks/rocks/RockGreenTiles.png");
			private static final BufferedImage TILE_ROCKS_ORANGE = loadSingleImage("/textures/sprites/blocks/rocks/RockOrangeTiles.png");

			public static final BufferedImage ROCKS_GREEN_UP_LEFT = TILE_ROCKS_GREEN != null ?
							TILE_ROCKS_GREEN.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_GREEN_UP = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ROCKS_GREEN_UP_RIGHT = TILE_ROCKS_GREEN != null ?
							TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_GREEN_DOWN_LEFT = TILE_ROCKS_GREEN != null ?
							TILE_ROCKS_GREEN.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_GREEN_DOWN = TILE_ROCKS_GREEN != null ?
							TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_GREEN_DOWN_RIGHT = TILE_ROCKS_GREEN != null ?
							TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;

			public static final BufferedImage ROCKS_ORANGE_UP_LEFT = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_ORANGE_UP = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_ORANGE_UP_RIGHT = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_ORANGE_DOWN_LEFT = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_ORANGE_DOWN = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;
			public static final BufferedImage ROCKS_ORANGE_DOWN_RIGHT = TILE_ROCKS_ORANGE != null ?
							TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
							null;

			public static final BufferedImage ROCKS_GREEN_SINGLE = loadSingleImage("/textures/sprites/blocks/rocks/RockGreenSingle.png");
			public static final BufferedImage ROCKS_ORANGE_SINGLE = loadSingleImage("/textures/sprites/blocks/rocks/RockOrangeSingle.png");
		}

		public static class Green
		{
			public static class Water
			{
				private static BufferedImage WATER = loadSingleImage("/textures/sprites/blocks/green/Water.png");
				
				public static BufferedImage WATER_UP_LEFT = WATER != null ? 
						WATER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) : 
						null;
				public static BufferedImage WATER_UP = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CENTER = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;

				public static BufferedImage WATER_CORNER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
			}

			public static BufferedImage BUSH = loadSingleImage("/textures/sprites/blocks/green/Bush.png");
			public static BufferedImage STAIRS = loadSingleImage("/textures/sprites/blocks/green/Stairs.png");
			public static BufferedImage LADDER = loadSingleImage("/textures/sprites/blocks/green/Ladder.png");
		}

		public static class Orange
		{
			public static class Water
			{
				private static BufferedImage WATER = loadSingleImage("/textures/sprites/blocks/orange/Water.png");

				public static BufferedImage WATER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_UP = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CENTER = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;

				public static BufferedImage WATER_CORNER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
				public static BufferedImage WATER_CORNER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
			}

			public static BufferedImage BUSH = loadSingleImage("/textures/sprites/blocks/orange/Bush.png");
			public static BufferedImage STAIRS = loadSingleImage("/textures/sprites/blocks/orange/Stairs.png");
			public static BufferedImage LADDER = loadSingleImage("/textures/sprites/blocks/orange/Ladder.png");
		}
	}

	public static class Enemies
	{
		public static final BufferedImage ENEMY_DEATH = loadSingleImage("/textures/sprites/enemies/Death.png");

		private static final BufferedImage OCTOROK_SPRITES = loadSingleImage("/textures/sprites/enemies/octorok/OctorokSprites.png");

		public static final BufferedImage OCTOROK = OCTOROK_SPRITES != null ?
				OCTOROK_SPRITES.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage OCTOROK_2 = OCTOROK_SPRITES != null ?
				OCTOROK_SPRITES.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;

		public static final BufferedImage OCTOROK_PELLET = loadSingleImage("/textures/sprites/enemies/octorok/Pellet.png");
	}

	public static class Menu
	{
		public static final BufferedImage MENU_TEMP = loadSingleImage("/textures/menu/TEMP.png");

		private static final BufferedImage HEARTS = loadSingleImage("/textures/menu/hearts/Hearts.png");

		public static final BufferedImage HEART_EMPTY = HEARTS != null ?
				HEARTS.getSubimage(0, 0, HEART_WIDTH, HEART_HEIGHT) :
				null;
		public static final BufferedImage HEART_HALF = HEARTS != null ?
				HEARTS.getSubimage(HEART_WIDTH, 0, HEART_WIDTH, HEART_HEIGHT) :
				null;
		public static final BufferedImage HEART_FULL = HEARTS != null ?
				HEARTS.getSubimage(HEART_WIDTH * 2, 0, HEART_WIDTH, HEART_HEIGHT) :
				null;
		public static final BufferedImage HEART_CONTAINER = loadSingleImage("/textures/menu/hearts/HeartContainer.png");
	}
}

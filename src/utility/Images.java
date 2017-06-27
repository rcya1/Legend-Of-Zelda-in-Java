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
		private static final BufferedImage LINK_ITEM = loadSingleImage("/textures/sprites/link/LinkItem.png");

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


		public static final BufferedImage LINK_ITEM_UP = LINK_ITEM != null ?
				LINK_ITEM.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ITEM_RIGHT = LINK_ITEM != null ?
				LINK_ITEM.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ITEM_DOWN = LINK_ITEM != null ?
				LINK_ITEM.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;
		public static final BufferedImage LINK_ITEM_LEFT = LINK_ITEM != null ?
				LINK_ITEM.getSubimage(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT) :
				null;

		public static final BufferedImage SWORD = loadSingleImage("/textures/sprites/link/items/Sword.png");

		public static class Items
		{
			public static final BufferedImage LINK_GET_ITEM = loadSingleImage("/textures/sprites/link/items/GetItem.png");
			public static final BufferedImage LINK_GET_TRIFORCE = loadSingleImage("/textures/sprites/link/items/GetTriforce.png");

			public static final BufferedImage ARROW = loadSingleImage("/textures/sprites/link/items/Arrow.png");
			public static final BufferedImage BOOMERANG = loadSingleImage("/textures/sprites/link/items/Boomerang.png");
		}
	}

	public static class Blocks
	{
		static final BufferedImage EMPTY = loadSingleImage("/textures/sprites/blocks/Empty.png");
		static final BufferedImage ENTRANCE = loadSingleImage("/textures/sprites/blocks/Entrance.png");
		static final BufferedImage EMPTY_GRAY = loadSingleImage("/textures/sprites/blocks/EmptyGray.png");

		public static class Secret
		{
			private static final BufferedImage FIRE = loadSingleImage("/textures/sprites/blocks/secret/Fire.png");
			public static final BufferedImage FIRE_1 = FIRE != null ?
					FIRE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage FIRE_2 = FIRE != null ?
					FIRE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage OLD_MAN_1 = loadSingleImage("/textures/sprites/blocks/secret/OldMan1.png");
		}

		static class Rocks
		{
			private static final BufferedImage TILE_ROCKS_GREEN = loadSingleImage("/textures/sprites/blocks/rocks/RockGreenTiles.png");
			private static final BufferedImage TILE_ROCKS_ORANGE = loadSingleImage("/textures/sprites/blocks/rocks/RockOrangeTiles.png");
			private static final BufferedImage TILE_ROCKS_DARK_ORANGE = loadSingleImage("/textures/sprites/blocks/rocks/RockDarkOrangeTiles.png");
			private static final BufferedImage TILE_ROCKS_WHITE = loadSingleImage("/textures/sprites/blocks/rocks/RockWhiteTiles.png");

			static final BufferedImage ROCKS_GREEN_UP_LEFT = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_GREEN_UP = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_GREEN_UP_RIGHT = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_GREEN_DOWN_LEFT = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_GREEN_DOWN = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_GREEN_DOWN_RIGHT = TILE_ROCKS_GREEN != null ?
					TILE_ROCKS_GREEN.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			static final BufferedImage ROCKS_ORANGE_UP_LEFT = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_ORANGE_UP = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_ORANGE_UP_RIGHT = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_ORANGE_DOWN_LEFT = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_ORANGE_DOWN = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_ORANGE_DOWN_RIGHT = TILE_ROCKS_ORANGE != null ?
					TILE_ROCKS_ORANGE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			static final BufferedImage ROCKS_DARK_ORANGE_UP_LEFT = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_DARK_ORANGE_UP = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_DARK_ORANGE_UP_RIGHT = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_DARK_ORANGE_DOWN_LEFT = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_DARK_ORANGE_DOWN = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_DARK_ORANGE_DOWN_RIGHT = TILE_ROCKS_DARK_ORANGE != null ?
					TILE_ROCKS_DARK_ORANGE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			static final BufferedImage ROCKS_WHITE_UP_LEFT = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_WHITE_UP = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_WHITE_UP_RIGHT = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_WHITE_DOWN_LEFT = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_WHITE_DOWN = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			static final BufferedImage ROCKS_WHITE_DOWN_RIGHT = TILE_ROCKS_WHITE != null ?
					TILE_ROCKS_WHITE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			static final BufferedImage ROCKS_GREEN_SINGLE = loadSingleImage("/textures/sprites/blocks/rocks/RockGreenSingle.png");
			static final BufferedImage ROCKS_ORANGE_SINGLE = loadSingleImage("/textures/sprites/blocks/rocks/RockOrangeSingle.png");
			static final BufferedImage ROCKS_WHITE_SINGLE = loadSingleImage("/textures/sprites/blocks/rocks/RockWhiteSingle.png");
		}

		static class Green
		{
			static class Water
			{
				private static final BufferedImage WATER = loadSingleImage("/textures/sprites/blocks/green/Water.png");

				static final BufferedImage WATER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CENTER = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage WATER_CORNER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
			}

			static class Entrances
			{
				private static final BufferedImage TREE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/green/TreeEntrance.png");

				static final BufferedImage TREE_ENTRANCE_UP_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				private static final BufferedImage STATUE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/green/StatueEntrance.png");

				static final BufferedImage STATUE_ENTRANCE_UP_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage STATUE_ENTRANCE_UP_ALT = loadSingleImage("/textures/sprites/blocks/green/StatueEntranceAlt.png");
			}

			static final BufferedImage BUSH = loadSingleImage("/textures/sprites/blocks/green/Bush.png");
			static final BufferedImage STAIRS = loadSingleImage("/textures/sprites/blocks/green/Stairs.png");
			static final BufferedImage LADDER = loadSingleImage("/textures/sprites/blocks/green/Ladder.png");
			static final BufferedImage BRIDGE = loadSingleImage("/textures/sprites/blocks/green/Bridge.png");
			static final BufferedImage SAND = loadSingleImage("/textures/sprites/blocks/green/Sand.png");
			static final BufferedImage WATERFALL = loadSingleImage("/textures/sprites/blocks/green/Waterfall.png");
		}

		static class Orange
		{
			static class Water
			{
				private static final BufferedImage WATER = loadSingleImage("/textures/sprites/blocks/orange/Water.png");

				static final BufferedImage WATER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CENTER = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage WATER_CORNER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
			}

			static class Entrances
			{
				private static final BufferedImage TREE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/orange/TreeEntrance.png");

				static final BufferedImage TREE_ENTRANCE_UP_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				private static final BufferedImage STATUE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/orange/StatueEntrance.png");

				static final BufferedImage STATUE_ENTRANCE_UP_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage STATUE_ENTRANCE_UP_ALT = loadSingleImage("/textures/sprites/blocks/orange/StatueEntranceAlt.png");
			}

			static final BufferedImage BUSH = loadSingleImage("/textures/sprites/blocks/orange/Bush.png");
			static final BufferedImage STAIRS = loadSingleImage("/textures/sprites/blocks/orange/Stairs.png");
			static final BufferedImage LADDER = loadSingleImage("/textures/sprites/blocks/orange/Ladder.png");
			static final BufferedImage BRIDGE = loadSingleImage("/textures/sprites/blocks/orange/Bridge.png");
			static final BufferedImage SAND = loadSingleImage("/textures/sprites/blocks/orange/Sand.png");
			static final BufferedImage WATERFALL = loadSingleImage("/textures/sprites/blocks/orange/Waterfall.png");
		}

		static class White
		{
			static class Water
			{
				private static final BufferedImage WATER = loadSingleImage("/textures/sprites/blocks/white/Water.png");

				static final BufferedImage WATER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CENTER = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage WATER_CORNER_UP_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_UP_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_LEFT = WATER != null ?
						WATER.getSubimage(0, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage WATER_CORNER_DOWN_RIGHT = WATER != null ?
						WATER.getSubimage(TILE_WIDTH, TILE_HEIGHT * 4, TILE_WIDTH, TILE_HEIGHT) :
						null;
			}

			static class Entrances
			{
				private static final BufferedImage TREE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/white/TreeEntrance.png");

				static final BufferedImage TREE_ENTRANCE_UP_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_UP_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_LEFT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage TREE_ENTRANCE_DOWN_RIGHT = TREE_ENTRANCE != null ?
						TREE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				private static final BufferedImage STATUE_ENTRANCE = loadSingleImage("/textures/sprites/blocks/white/StatueEntrance.png");

				static final BufferedImage STATUE_ENTRANCE_UP_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_UP_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_LEFT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;
				static final BufferedImage STATUE_ENTRANCE_DOWN_RIGHT = STATUE_ENTRANCE != null ?
						STATUE_ENTRANCE.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
						null;

				static final BufferedImage STATUE_ENTRANCE_UP_ALT = loadSingleImage("/textures/sprites/blocks/white/StatueEntranceAlt.png");
			}

			static final BufferedImage GRAVE = loadSingleImage("/textures/sprites/blocks/white/Grave.png");
			static final BufferedImage STAIRS = loadSingleImage("/textures/sprites/blocks/white/Stairs.png");
			static final BufferedImage LADDER = loadSingleImage("/textures/sprites/blocks/white/Ladder.png");
			static final BufferedImage BRIDGE = loadSingleImage("/textures/sprites/blocks/white/Bridge.png");
			static final BufferedImage SAND = loadSingleImage("/textures/sprites/blocks/white/Sand.png");
			static final BufferedImage BUSH = loadSingleImage("/textures/sprites/blocks/white/Bush.png");
			static final BufferedImage WATERFALL = loadSingleImage("/textures/sprites/blocks/white/Waterfall.png");
		}
	}

	public static class Enemies
	{
		public static final BufferedImage ENEMY_DEATH = loadSingleImage("/textures/sprites/enemies/Death.png");

		public static class Octorok
		{
			private static final BufferedImage OCTOROK = loadSingleImage("/textures/sprites/enemies/octorok/Octorok.png");

			public static final BufferedImage OCTOROK_1 = OCTOROK != null ?
					OCTOROK.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage OCTOROK_2 = OCTOROK != null ?
					OCTOROK.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage OCTOROK_BLUE = loadSingleImage("/textures/sprites/enemies/octorok/OctorokBlue.png");

			public static final BufferedImage OCTOROK_BLUE_1 = OCTOROK_BLUE != null ?
					OCTOROK_BLUE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage OCTOROK_BLUE_2 = OCTOROK_BLUE != null ?
					OCTOROK_BLUE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage OCTOROK_PELLET = loadSingleImage("/textures/sprites/enemies/octorok/Pellet.png");
		}

		public static class Tektite
		{
			private static final BufferedImage TEKTITE = loadSingleImage("/textures/sprites/enemies/tektite/Tektite.png");

			public static final BufferedImage TEKTITE_1 = TEKTITE != null ?
					TEKTITE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage TEKTITE_2 = TEKTITE != null ?
					TEKTITE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage TEKTITE_BLUE = loadSingleImage("/textures/sprites/enemies/tektite/TektiteBlue.png");

			public static final BufferedImage TEKTITE_BLUE_1 = TEKTITE_BLUE != null ?
					TEKTITE_BLUE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage TEKTITE_BLUE_2 = TEKTITE_BLUE != null ?
					TEKTITE_BLUE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
		}

		public static class Leever
		{
			private static final BufferedImage LEEVER = loadSingleImage("/textures/sprites/enemies/leever/Leever.png");

			public static final BufferedImage LEEVER_1 = LEEVER != null ?
					LEEVER.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_2 = LEEVER != null ?
					LEEVER.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_MIDBURROW = LEEVER != null ?
					LEEVER.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage LEEVER_BURROW_1 = LEEVER != null ?
					LEEVER.getSubimage(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_BURROW_2 = LEEVER != null ?
					LEEVER.getSubimage(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage LEEVER_BLUE = loadSingleImage("/textures/sprites/enemies/leever/LeeverBlue.png");

			public static final BufferedImage LEEVER_BLUE_1 = LEEVER_BLUE != null ?
					LEEVER_BLUE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_BLUE_2 = LEEVER_BLUE != null ?
					LEEVER_BLUE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_BLUE_MIDBURROW = LEEVER_BLUE != null ?
					LEEVER_BLUE.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage LEEVER_BLUE_BURROW_1 = LEEVER_BLUE != null ?
					LEEVER_BLUE.getSubimage(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage LEEVER_BLUE_BURROW_2 = LEEVER_BLUE != null ?
					LEEVER_BLUE.getSubimage(TILE_WIDTH * 4, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
		}

		public static class Peahat
		{
			private static final BufferedImage PEAHAT = loadSingleImage("/textures/sprites/enemies/peahat/Peahat.png");

			public static final BufferedImage PEAHAT_1 = PEAHAT != null ?
					PEAHAT.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage PEAHAT_2 = PEAHAT != null ?
					PEAHAT.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
		}

		public static class Zola
		{
			private static final BufferedImage ZOLA = loadSingleImage("/textures/sprites/enemies/zola/Zola.png");

			public static final BufferedImage ZOLA_FRONT = ZOLA != null ?
					ZOLA.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_BACK = ZOLA != null ?
					ZOLA.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage ZOLA_WARP = loadSingleImage("/textures/sprites/enemies/zola/ZolaWarp.png");

			public static final BufferedImage ZOLA_WARP_1 = ZOLA_WARP != null ?
					ZOLA_WARP.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_WARP_2 = ZOLA_WARP != null ?
					ZOLA_WARP.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage ZOLA_FIREBALL = loadSingleImage("/textures/sprites/enemies/zola/ZolaFireball.png");

			public static final BufferedImage ZOLA_FIREBALL_1 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_2 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_3 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(TILE_WIDTH * 2, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_4 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(TILE_WIDTH * 3, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_5 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_6 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage ZOLA_FIREBALL_7 = ZOLA_FIREBALL != null ?
					ZOLA_FIREBALL.getSubimage(TILE_WIDTH * 2, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
		}

		public static class Molblin
		{
			private static final BufferedImage MOLBLIN = loadSingleImage("/textures/sprites/enemies/molblin/Molblin.png");

			public static final BufferedImage MOLBLIN_UP = MOLBLIN != null ?
					MOLBLIN.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_UP_2 = MOLBLIN != null ?
					MOLBLIN.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_RIGHT = MOLBLIN != null ?
					MOLBLIN.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_RIGHT_2 = MOLBLIN != null ?
					MOLBLIN.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_DOWN = MOLBLIN != null ?
					MOLBLIN.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_DOWN_2 = MOLBLIN != null ?
					MOLBLIN.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_LEFT = MOLBLIN != null ?
					MOLBLIN.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_LEFT_2 = MOLBLIN != null ?
					MOLBLIN.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
					null;

			private static final BufferedImage MOLBLIN_BLUE = loadSingleImage("/textures/sprites/enemies/molblin/MolblinBlue.png");

			public static final BufferedImage MOLBLIN_BLUE_UP = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(0, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_BLUE_UP_2 = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_BLUE_RIGHT = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(0, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_BLUE_RIGHT_2 = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(TILE_WIDTH, TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_BLUE_DOWN = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(0, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_BLUE_DOWN_2 = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(TILE_WIDTH, TILE_HEIGHT * 2, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_BLUE_LEFT = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(0, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
					null;
			public static final BufferedImage MOLBLIN_BLUE_LEFT_2 = MOLBLIN_BLUE != null ?
					MOLBLIN_BLUE.getSubimage(TILE_WIDTH, TILE_HEIGHT * 3, TILE_WIDTH, TILE_HEIGHT) :
					null;

			public static final BufferedImage MOLBLIN_SPEAR = loadSingleImage("/textures/sprites/enemies/molblin/Spear.png");
		}
	}

	public static class Menu
	{
		public static final BufferedImage MENU = loadSingleImage("/textures/menu/menu.png");

		public static class Items
		{
			public static BufferedImage SELECTOR = loadSingleImage("/textures/menu/items/selector.png");
			public static BufferedImage BOOMERANG = loadSingleImage("/textures/menu/items/boomerang.png");
			public static BufferedImage BOOMERANG_MAGIC = loadSingleImage("/textures/menu/items/boomerangMagic.png");
			public static BufferedImage BOMBS = loadSingleImage("/textures/menu/items/bombs.png");
			public static BufferedImage BOW = loadSingleImage("/textures/menu/items/bow.png");
			public static BufferedImage ARROW = loadSingleImage("/textures/menu/items/arrow.png");
			public static BufferedImage ARROW_SILVER = loadSingleImage("/textures/menu/items/arrowSilver.png");
			public static BufferedImage CANDLE_BLUE = loadSingleImage("/textures/menu/items/candleBlue.png");
			public static BufferedImage CANDLE_RED = loadSingleImage("/textures/menu/items/candleRed.png");
			public static BufferedImage WHISTLE = loadSingleImage("/textures/menu/items/whistle.png");
			public static BufferedImage BAIT = loadSingleImage("/textures/menu/items/bait.png");
			public static BufferedImage LETTER = loadSingleImage("/textures/menu/items/letter.png");
			public static BufferedImage POTION_BLUE = loadSingleImage("/textures/menu/items/potionBlue.png");
			public static BufferedImage POTION_RED = loadSingleImage("/textures/menu/items/potionRed.png");
			public static BufferedImage MAGIC_WAND = loadSingleImage("/textures/menu/items/magicWand.png");

		}

		public static class Hearts
		{
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
}

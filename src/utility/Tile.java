package utility;

import java.awt.image.BufferedImage;

public enum Tile
{
	CBOX,
	EMPTY,

	ROCKS_GREEN_UP_LEFT,
	ROCKS_GREEN_UP,
	ROCKS_GREEN_UP_RIGHT,
	ROCKS_GREEN_DOWN_LEFT,
	ROCKS_GREEN_DOWN,
	ROCKS_GREEN_DOWN_RIGHT,
	ROCKS_GREEN_SINGLE,

	ROCKS_ORANGE_UP_LEFT,
	ROCKS_ORANGE_UP,
	ROCKS_ORANGE_UP_RIGHT,
	ROCKS_ORANGE_DOWN_LEFT,
	ROCKS_ORANGE_DOWN,
	ROCKS_ORANGE_DOWN_RIGHT,
	ROCKS_ORANGE_SINGLE,

	GREEN_BUSH,
	GREEN_LADDER,
	GREEN_STAIRS,

	GREEN_WATER_UP_LEFT,
	GREEN_WATER_UP,
	GREEN_WATER_UP_RIGHT,
	GREEN_WATER_LEFT,
	GREEN_WATER_CENTER,
	GREEN_WATER_RIGHT,
	GREEN_WATER_DOWN_LEFT,
	GREEN_WATER_DOWN,
	GREEN_WATER_DOWN_RIGHT,

	GREEN_WATER_CORNER_UP_LEFT,
	GREEN_WATER_CORNER_UP_RIGHT,
	GREEN_WATER_CORNER_DOWN_LEFT,
	GREEN_WATER_CORNER_DOWN_RIGHT,


	ORANGE_BUSH,
	ORANGE_LADDER,
	ORANGE_STAIRS,

	ORANGE_WATER_UP_LEFT,
	ORANGE_WATER_UP,
	ORANGE_WATER_UP_RIGHT,
	ORANGE_WATER_LEFT,
	ORANGE_WATER_CENTER,
	ORANGE_WATER_RIGHT,
	ORANGE_WATER_DOWN_LEFT,
	ORANGE_WATER_DOWN,
	ORANGE_WATER_DOWN_RIGHT,

	ORANGE_WATER_CORNER_UP_LEFT,
	ORANGE_WATER_CORNER_UP_RIGHT,
	ORANGE_WATER_CORNER_DOWN_LEFT,
	ORANGE_WATER_CORNER_DOWN_RIGHT,

	ENTRANCE,

	GREEN_BRIDGE,
	ORANGE_BRIDGE,

	GREEN_TREE_ENTRANCE_UP_LEFT,
	GREEN_TREE_ENTRANCE_UP,
	GREEN_TREE_ENTRANCE_UP_RIGHT,
	GREEN_TREE_ENTRANCE_DOWN_LEFT,
	GREEN_TREE_ENTRANCE_DOWN_RIGHT,

	GREEN_STATUE_ENTRANCE_UP_LEFT,
	GREEN_STATUE_ENTRANCE_UP,
	GREEN_STATUE_ENTRANCE_UP_RIGHT,
	GREEN_STATUE_ENTRANCE_DOWN_LEFT,
	GREEN_STATUE_ENTRANCE_DOWN_RIGHT,

	ORANGE_TREE_ENTRANCE_UP_LEFT,
	ORANGE_TREE_ENTRANCE_UP,
	ORANGE_TREE_ENTRANCE_UP_RIGHT,
	ORANGE_TREE_ENTRANCE_DOWN_LEFT,
	ORANGE_TREE_ENTRANCE_DOWN_RIGHT,

	ORANGE_STATUE_ENTRANCE_UP_LEFT,
	ORANGE_STATUE_ENTRANCE_UP,
	ORANGE_STATUE_ENTRANCE_UP_RIGHT,
	ORANGE_STATUE_ENTRANCE_DOWN_LEFT,
	ORANGE_STATUE_ENTRANCE_DOWN_RIGHT,

	GREEN_STATUE_ENTRANCE_UP_ALT,
	ORANGE_STATUE_ENTRANCE_UP_ALT,

	ROCKS_DARK_ORANGE_UP_LEFT,
	ROCKS_DARK_ORANGE_UP,
	ROCKS_DARK_ORANGE_UP_RIGHT,
	ROCKS_DARK_ORANGE_DOWN_LEFT,
	ROCKS_DARK_ORANGE_DOWN,
	ROCKS_DARK_ORANGE_DOWN_RIGHT,

	ROCKS_WHITE_UP_LEFT,
	ROCKS_WHITE_UP,
	ROCKS_WHITE_UP_RIGHT,
	ROCKS_WHITE_DOWN_LEFT,
	ROCKS_WHITE_DOWN,
	ROCKS_WHITE_DOWN_RIGHT,
	ROCKS_WHITE_SINGLE,

	WHITE_GRAVE,
	WHITE_LADDER,
	WHITE_STAIRS,

	WHITE_WATER_UP_LEFT,
	WHITE_WATER_UP,
	WHITE_WATER_UP_RIGHT,
	WHITE_WATER_LEFT,
	WHITE_WATER_CENTER,
	WHITE_WATER_RIGHT,
	WHITE_WATER_DOWN_LEFT,
	WHITE_WATER_DOWN,
	WHITE_WATER_DOWN_RIGHT,

	WHITE_WATER_CORNER_UP_LEFT,
	WHITE_WATER_CORNER_UP_RIGHT,
	WHITE_WATER_CORNER_DOWN_LEFT,
	WHITE_WATER_CORNER_DOWN_RIGHT,

	WHITE_TREE_ENTRANCE_UP_LEFT,
	WHITE_TREE_ENTRANCE_UP,
	WHITE_TREE_ENTRANCE_UP_RIGHT,
	WHITE_TREE_ENTRANCE_DOWN_LEFT,
	WHITE_TREE_ENTRANCE_DOWN_RIGHT,

	WHITE_STATUE_ENTRANCE_UP_LEFT,
	WHITE_STATUE_ENTRANCE_UP,
	WHITE_STATUE_ENTRANCE_UP_RIGHT,
	WHITE_STATUE_ENTRANCE_DOWN_LEFT,
	WHITE_STATUE_ENTRANCE_DOWN_RIGHT,

	WHITE_STATUE_ENTRANCE_UP_ALT,

	GREEN_SAND,
	ORANGE_SAND,
	WHITE_SAND,

	EMPTY_GRAY,

	WHITE_BRIDGE,
	WHITE_BUSH;

	public static Tile parseID(int id)
	{
		switch(id)
		{
		case -1:
			return CBOX;
		case 0:
			return EMPTY;

		case 1:
			return ROCKS_GREEN_UP_LEFT;
		case 2:
			return ROCKS_GREEN_UP;
		case 3:
			return ROCKS_GREEN_UP_RIGHT;
		case 4:
			return ROCKS_GREEN_DOWN_LEFT;
		case 5:
			return ROCKS_GREEN_DOWN;
		case 6:
			return ROCKS_GREEN_DOWN_RIGHT;
		case 7:
			return ROCKS_GREEN_SINGLE;

		case 8:
			return ROCKS_ORANGE_UP_LEFT;
		case 9:
			return ROCKS_ORANGE_UP;
		case 10:
			return ROCKS_ORANGE_UP_RIGHT;
		case 11:
			return ROCKS_ORANGE_DOWN_LEFT;
		case 12:
			return ROCKS_ORANGE_DOWN;
		case 13:
			return ROCKS_ORANGE_DOWN_RIGHT;
		case 14:
			return ROCKS_ORANGE_SINGLE;

		case 15:
			return GREEN_BUSH;
		case 16:
			return GREEN_LADDER;
		case 17:
			return GREEN_STAIRS;
		case 18:

			return GREEN_WATER_UP_LEFT;
		case 19:
			return GREEN_WATER_UP;
		case 20:
			return GREEN_WATER_UP_RIGHT;
		case 21:
			return GREEN_WATER_LEFT;
		case 22:
			return GREEN_WATER_CENTER;
		case 23:
			return GREEN_WATER_RIGHT;
		case 24:
			return GREEN_WATER_DOWN_LEFT;
		case 25:
			return GREEN_WATER_DOWN;
		case 26:
			return GREEN_WATER_DOWN_RIGHT;
		case 27:
			return GREEN_WATER_CORNER_UP_LEFT;
		case 28:
			return GREEN_WATER_CORNER_UP_RIGHT;
		case 29:
			return GREEN_WATER_CORNER_DOWN_LEFT;
		case 30:
			return GREEN_WATER_CORNER_DOWN_RIGHT;

		case 31:
			return ORANGE_BUSH;
		case 32:
			return ORANGE_LADDER;
		case 33:
			return ORANGE_STAIRS;

		case 34:
			return ORANGE_WATER_UP_LEFT;
		case 35:
			return ORANGE_WATER_UP;
		case 36:
			return ORANGE_WATER_UP_RIGHT;
		case 37:
			return ORANGE_WATER_LEFT;
		case 38:
			return ORANGE_WATER_CENTER;
		case 39:
			return ORANGE_WATER_RIGHT;
		case 40:
			return ORANGE_WATER_DOWN_LEFT;
		case 41:
			return ORANGE_WATER_DOWN;
		case 42:
			return ORANGE_WATER_DOWN_RIGHT;
		case 43:
			return ORANGE_WATER_CORNER_UP_LEFT;
		case 44:
			return ORANGE_WATER_CORNER_UP_RIGHT;
		case 45:
			return ORANGE_WATER_CORNER_DOWN_LEFT;
		case 46:
			return ORANGE_WATER_CORNER_DOWN_RIGHT;

		case 47:
			return ENTRANCE;
		case 48:
			return GREEN_BRIDGE;
		case 49:
			return ORANGE_BRIDGE;

		case 50:
			return GREEN_TREE_ENTRANCE_UP_LEFT;
		case 51:
			return GREEN_TREE_ENTRANCE_UP;
		case 52:
			return GREEN_TREE_ENTRANCE_UP_RIGHT;
		case 53:
			return GREEN_TREE_ENTRANCE_DOWN_LEFT;
		case 54:
			return GREEN_TREE_ENTRANCE_DOWN_RIGHT;

		case 55:
			return GREEN_STATUE_ENTRANCE_UP_LEFT;
		case 56:
			return GREEN_STATUE_ENTRANCE_UP;
		case 57:
			return GREEN_STATUE_ENTRANCE_UP_RIGHT;
		case 58:
			return GREEN_STATUE_ENTRANCE_DOWN_LEFT;
		case 59:
			return GREEN_STATUE_ENTRANCE_DOWN_RIGHT;

		case 60:
			return ORANGE_TREE_ENTRANCE_UP_LEFT;
		case 61:
			return ORANGE_TREE_ENTRANCE_UP;
		case 62:
			return ORANGE_TREE_ENTRANCE_UP_RIGHT;
		case 63:
			return ORANGE_TREE_ENTRANCE_DOWN_LEFT;
		case 64:
			return ORANGE_TREE_ENTRANCE_DOWN_RIGHT;

		case 65:
			return ORANGE_STATUE_ENTRANCE_UP_LEFT;
		case 66:
			return ORANGE_STATUE_ENTRANCE_UP;
		case 67:
			return ORANGE_STATUE_ENTRANCE_UP_RIGHT;
		case 68:
			return ORANGE_STATUE_ENTRANCE_DOWN_LEFT;
		case 69:
			return ORANGE_STATUE_ENTRANCE_DOWN_RIGHT;

		case 70:
			return GREEN_STATUE_ENTRANCE_UP_ALT;
		case 71:
			return ORANGE_STATUE_ENTRANCE_UP_ALT;

		case 72:
			return ROCKS_DARK_ORANGE_UP_LEFT;
		case 73:
			return ROCKS_DARK_ORANGE_UP;
		case 74:
			return ROCKS_DARK_ORANGE_UP_RIGHT;
		case 75:
			return ROCKS_DARK_ORANGE_DOWN_LEFT;
		case 76:
			return ROCKS_DARK_ORANGE_DOWN;
		case 77:
			return ROCKS_DARK_ORANGE_DOWN_RIGHT;

		case 78:
			return ROCKS_WHITE_UP_LEFT;
		case 79:
			return ROCKS_WHITE_UP;
		case 80:
			return ROCKS_WHITE_UP_RIGHT;
		case 81:
			return ROCKS_WHITE_DOWN_LEFT;
		case 82:
			return ROCKS_WHITE_DOWN;
		case 83:
			return ROCKS_WHITE_DOWN_RIGHT;
		case 84:
			return ROCKS_WHITE_SINGLE;

		case 85:
			return WHITE_GRAVE;
		case 86:
			return WHITE_LADDER;
		case 87:
			return WHITE_STAIRS;

		case 88:
			return WHITE_WATER_UP_LEFT;
		case 89:
			return WHITE_WATER_UP;
		case 90:
			return WHITE_WATER_UP_RIGHT;
		case 91:
			return WHITE_WATER_LEFT;
		case 92:
			return WHITE_WATER_CENTER;
		case 93:
			return WHITE_WATER_RIGHT;
		case 94:
			return WHITE_WATER_DOWN_LEFT;
		case 95:
			return WHITE_WATER_DOWN;
		case 96:
			return WHITE_WATER_DOWN_RIGHT;
		case 97:
			return WHITE_WATER_CORNER_UP_LEFT;
		case 98:
			return WHITE_WATER_CORNER_UP_RIGHT;
		case 99:
			return WHITE_WATER_CORNER_DOWN_LEFT;
		case 100:
			return WHITE_WATER_CORNER_DOWN_RIGHT;

		case 101:
			return WHITE_TREE_ENTRANCE_UP_LEFT;
		case 102:
			return WHITE_TREE_ENTRANCE_UP;
		case 103:
			return WHITE_TREE_ENTRANCE_UP_RIGHT;
		case 104:
			return WHITE_TREE_ENTRANCE_DOWN_LEFT;
		case 105:
			return WHITE_TREE_ENTRANCE_DOWN_RIGHT;

		case 106:
			return WHITE_STATUE_ENTRANCE_UP_LEFT;
		case 107:
			return WHITE_STATUE_ENTRANCE_UP;
		case 108:
			return WHITE_STATUE_ENTRANCE_UP_RIGHT;
		case 109:
			return WHITE_STATUE_ENTRANCE_DOWN_LEFT;
		case 110:
			return WHITE_STATUE_ENTRANCE_DOWN_RIGHT;
		case 111:
			return WHITE_STATUE_ENTRANCE_UP_ALT;

		case 112:
			return GREEN_SAND;
		case 113:
			return ORANGE_SAND;
		case 114:
			return WHITE_SAND;

		case 115:
			return EMPTY_GRAY;

		case 116:
			return WHITE_BRIDGE;
		case 117:
			return WHITE_BUSH;

		default:
			return null;
		}
	}

	public int toID()
	{
		switch(this)
		{
		case EMPTY:
			return 0;
		case CBOX:
			return -1;

		case ROCKS_GREEN_UP_LEFT:
			return 1;
		case ROCKS_GREEN_UP:
			return 2;
		case ROCKS_GREEN_UP_RIGHT:
			return 3;
		case ROCKS_GREEN_DOWN_LEFT:
			return 4;
		case ROCKS_GREEN_DOWN:
			return 5;
		case ROCKS_GREEN_DOWN_RIGHT:
			return 6;
		case ROCKS_GREEN_SINGLE:
			return 7;

		case ROCKS_ORANGE_UP_LEFT:
			return 8;
		case ROCKS_ORANGE_UP:
			return 9;
		case ROCKS_ORANGE_UP_RIGHT:
			return 10;
		case ROCKS_ORANGE_DOWN_LEFT:
			return 11;
		case ROCKS_ORANGE_DOWN:
			return 12;
		case ROCKS_ORANGE_DOWN_RIGHT:
			return 13;
		case ROCKS_ORANGE_SINGLE:
			return 14;
		case GREEN_BUSH:
			return 15;
		case GREEN_LADDER:
			return 16;
		case GREEN_STAIRS:
			return 17;

		case GREEN_WATER_UP_LEFT:
			return 18;
		case GREEN_WATER_UP:
			return 19;
		case GREEN_WATER_UP_RIGHT:
			return 20;
		case GREEN_WATER_LEFT:
			return 21;
		case GREEN_WATER_CENTER:
			return 22;
		case GREEN_WATER_RIGHT:
			return 23;
		case GREEN_WATER_DOWN_LEFT:
			return 24;
		case GREEN_WATER_DOWN:
			return 25;
		case GREEN_WATER_DOWN_RIGHT:
			return 26;
		case GREEN_WATER_CORNER_UP_LEFT:
			return 27;
		case GREEN_WATER_CORNER_UP_RIGHT:
			return 28;
		case GREEN_WATER_CORNER_DOWN_LEFT:
			return 29;
		case GREEN_WATER_CORNER_DOWN_RIGHT:
			return 30;

		case ORANGE_BUSH:
			return 31;
		case ORANGE_LADDER:
			return 32;
		case ORANGE_STAIRS:
			return 33;
		case ORANGE_WATER_UP_LEFT:
			return 34;
		case ORANGE_WATER_UP:
			return 35;
		case ORANGE_WATER_UP_RIGHT:
			return 36;
		case ORANGE_WATER_LEFT:
			return 37;
		case ORANGE_WATER_CENTER:
			return 38;
		case ORANGE_WATER_RIGHT:
			return 39;
		case ORANGE_WATER_DOWN_LEFT:
			return 40;
		case ORANGE_WATER_DOWN:
			return 41;
		case ORANGE_WATER_DOWN_RIGHT:
			return 42;
		case ORANGE_WATER_CORNER_UP_LEFT:
			return 43;
		case ORANGE_WATER_CORNER_UP_RIGHT:
			return 44;
		case ORANGE_WATER_CORNER_DOWN_LEFT:
			return 45;
		case ORANGE_WATER_CORNER_DOWN_RIGHT:
			return 46;

		case ENTRANCE:
			return 47;

		case GREEN_BRIDGE:
			return 48;
		case ORANGE_BRIDGE:
			return 49;

		case GREEN_TREE_ENTRANCE_UP_LEFT:
			return 50;
		case GREEN_TREE_ENTRANCE_UP:
			return 51;
		case GREEN_TREE_ENTRANCE_UP_RIGHT:
			return 52;
		case GREEN_TREE_ENTRANCE_DOWN_LEFT:
			return 53;
		case GREEN_TREE_ENTRANCE_DOWN_RIGHT:
			return 54;

		case GREEN_STATUE_ENTRANCE_UP_LEFT:
			return 55;
		case GREEN_STATUE_ENTRANCE_UP:
			return 56;
		case GREEN_STATUE_ENTRANCE_UP_RIGHT:
			return 57;
		case GREEN_STATUE_ENTRANCE_DOWN_LEFT:
			return 58;
		case GREEN_STATUE_ENTRANCE_DOWN_RIGHT:
			return 59;

		case ORANGE_TREE_ENTRANCE_UP_LEFT:
			return 60;
		case ORANGE_TREE_ENTRANCE_UP:
			return 61;
		case ORANGE_TREE_ENTRANCE_UP_RIGHT:
			return 62;
		case ORANGE_TREE_ENTRANCE_DOWN_LEFT:
			return 63;
		case ORANGE_TREE_ENTRANCE_DOWN_RIGHT:
			return 64;

		case ORANGE_STATUE_ENTRANCE_UP_LEFT:
			return 65;
		case ORANGE_STATUE_ENTRANCE_UP:
			return 66;
		case ORANGE_STATUE_ENTRANCE_UP_RIGHT:
			return 67;
		case ORANGE_STATUE_ENTRANCE_DOWN_LEFT:
			return 68;
		case ORANGE_STATUE_ENTRANCE_DOWN_RIGHT:
			return 69;

		case GREEN_STATUE_ENTRANCE_UP_ALT:
			return 70;
		case ORANGE_STATUE_ENTRANCE_UP_ALT:
			return 71;

		case ROCKS_DARK_ORANGE_UP_LEFT:
			return 72;
		case ROCKS_DARK_ORANGE_UP:
			return 73;
		case ROCKS_DARK_ORANGE_UP_RIGHT:
			return 74;
		case ROCKS_DARK_ORANGE_DOWN_LEFT:
			return 75;
		case ROCKS_DARK_ORANGE_DOWN:
			return 76;
		case ROCKS_DARK_ORANGE_DOWN_RIGHT:
			return 77;

		case ROCKS_WHITE_UP_LEFT:
			return 78;
		case ROCKS_WHITE_UP:
			return 79;
		case ROCKS_WHITE_UP_RIGHT:
			return 80;
		case ROCKS_WHITE_DOWN_LEFT:
			return 81;
		case ROCKS_WHITE_DOWN:
			return 82;
		case ROCKS_WHITE_DOWN_RIGHT:
			return 83;
		case ROCKS_WHITE_SINGLE:
			return 84;

		case WHITE_GRAVE:
			return 85;
		case WHITE_LADDER:
			return 86;
		case WHITE_STAIRS:
			return 87;

		case WHITE_WATER_UP_LEFT:
			return 88;
		case WHITE_WATER_UP:
			return 89;
		case WHITE_WATER_UP_RIGHT:
			return 90;
		case WHITE_WATER_LEFT:
			return 91;
		case WHITE_WATER_CENTER:
			return 92;
		case WHITE_WATER_RIGHT:
			return 93;
		case WHITE_WATER_DOWN_LEFT:
			return 94;
		case WHITE_WATER_DOWN:
			return 95;
		case WHITE_WATER_DOWN_RIGHT:
			return 96;
		case WHITE_WATER_CORNER_UP_LEFT:
			return 97;
		case WHITE_WATER_CORNER_UP_RIGHT:
			return 98;
		case WHITE_WATER_CORNER_DOWN_LEFT:
			return 99;
		case WHITE_WATER_CORNER_DOWN_RIGHT:
			return 100;

		case WHITE_TREE_ENTRANCE_UP_LEFT:
			return 101;
		case WHITE_TREE_ENTRANCE_UP:
			return 102;
		case WHITE_TREE_ENTRANCE_UP_RIGHT:
			return 103;
		case WHITE_TREE_ENTRANCE_DOWN_LEFT:
			return 104;
		case WHITE_TREE_ENTRANCE_DOWN_RIGHT:
			return 105;

		case WHITE_STATUE_ENTRANCE_UP_LEFT:
			return 106;
		case WHITE_STATUE_ENTRANCE_UP:
			return 107;
		case WHITE_STATUE_ENTRANCE_UP_RIGHT:
			return 108;
		case WHITE_STATUE_ENTRANCE_DOWN_LEFT:
			return 109;
		case WHITE_STATUE_ENTRANCE_DOWN_RIGHT:
			return 110;
		case WHITE_STATUE_ENTRANCE_UP_ALT:
			return 111;

		case GREEN_SAND:
			return 112;
		case ORANGE_SAND:
			return 113;
		case WHITE_SAND:
			return 114;

		case EMPTY_GRAY:
			return 115;

		case WHITE_BRIDGE:
			return 116;
		case WHITE_BUSH:
			return 117;

		default:
			return 0;
		}
	}

	public static BufferedImage getSprite(Tile tile)
	{
		switch(tile)
		{
		case CBOX:
			return new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		case EMPTY:
			return Images.Blocks.EMPTY;

		case ROCKS_GREEN_UP_LEFT:
			return Images.Blocks.Rocks.ROCKS_GREEN_UP_LEFT;
		case ROCKS_GREEN_UP:
			return Images.Blocks.Rocks.ROCKS_GREEN_UP;
		case ROCKS_GREEN_UP_RIGHT:
			return Images.Blocks.Rocks.ROCKS_GREEN_UP_RIGHT;
		case ROCKS_GREEN_DOWN_LEFT:
			return Images.Blocks.Rocks.ROCKS_GREEN_DOWN_LEFT;
		case ROCKS_GREEN_DOWN:
			return Images.Blocks.Rocks.ROCKS_GREEN_DOWN;
		case ROCKS_GREEN_DOWN_RIGHT:
			return Images.Blocks.Rocks.ROCKS_GREEN_DOWN_RIGHT;
		case ROCKS_GREEN_SINGLE:
			return Images.Blocks.Rocks.ROCKS_GREEN_SINGLE;

		case ROCKS_ORANGE_UP_LEFT:
			return Images.Blocks.Rocks.ROCKS_ORANGE_UP_LEFT;
		case ROCKS_ORANGE_UP:
			return Images.Blocks.Rocks.ROCKS_ORANGE_UP;
		case ROCKS_ORANGE_UP_RIGHT:
			return Images.Blocks.Rocks.ROCKS_ORANGE_UP_RIGHT;
		case ROCKS_ORANGE_DOWN_LEFT:
			return Images.Blocks.Rocks.ROCKS_ORANGE_DOWN_LEFT;
		case ROCKS_ORANGE_DOWN:
			return Images.Blocks.Rocks.ROCKS_ORANGE_DOWN;
		case ROCKS_ORANGE_DOWN_RIGHT:
			return Images.Blocks.Rocks.ROCKS_ORANGE_DOWN_RIGHT;
		case ROCKS_ORANGE_SINGLE:
			return Images.Blocks.Rocks.ROCKS_ORANGE_SINGLE;

		case GREEN_BUSH:
			return Images.Blocks.Green.BUSH;
		case GREEN_LADDER:
			return Images.Blocks.Green.LADDER;
		case GREEN_STAIRS:
			return Images.Blocks.Green.STAIRS;

		case GREEN_WATER_UP_LEFT:
			return Images.Blocks.Green.Water.WATER_UP_LEFT;
		case GREEN_WATER_UP:
			return Images.Blocks.Green.Water.WATER_UP;
		case GREEN_WATER_UP_RIGHT:
			return Images.Blocks.Green.Water.WATER_UP_RIGHT;
		case GREEN_WATER_LEFT:
			return Images.Blocks.Green.Water.WATER_LEFT;
		case GREEN_WATER_CENTER:
			return Images.Blocks.Green.Water.WATER_CENTER;
		case GREEN_WATER_RIGHT:
			return Images.Blocks.Green.Water.WATER_RIGHT;
		case GREEN_WATER_DOWN_LEFT:
			return Images.Blocks.Green.Water.WATER_DOWN_LEFT;
		case GREEN_WATER_DOWN:
			return Images.Blocks.Green.Water.WATER_DOWN;
		case GREEN_WATER_DOWN_RIGHT:
			return Images.Blocks.Green.Water.WATER_DOWN_RIGHT;
		case GREEN_WATER_CORNER_UP_LEFT:
			return Images.Blocks.Green.Water.WATER_CORNER_UP_LEFT;
		case GREEN_WATER_CORNER_UP_RIGHT:
			return Images.Blocks.Green.Water.WATER_CORNER_UP_RIGHT;
		case GREEN_WATER_CORNER_DOWN_LEFT:
			return Images.Blocks.Green.Water.WATER_CORNER_DOWN_LEFT;
		case GREEN_WATER_CORNER_DOWN_RIGHT:
			return Images.Blocks.Green.Water.WATER_CORNER_DOWN_RIGHT;

		case ORANGE_BUSH:
			return Images.Blocks.Orange.BUSH;
		case ORANGE_LADDER:
			return Images.Blocks.Orange.LADDER;
		case ORANGE_STAIRS:
			return Images.Blocks.Orange.STAIRS;

		case ORANGE_WATER_UP_LEFT:
			return Images.Blocks.Orange.Water.WATER_UP_LEFT;
		case ORANGE_WATER_UP:
			return Images.Blocks.Orange.Water.WATER_UP;
		case ORANGE_WATER_UP_RIGHT:
			return Images.Blocks.Orange.Water.WATER_UP_RIGHT;
		case ORANGE_WATER_LEFT:
			return Images.Blocks.Orange.Water.WATER_LEFT;
		case ORANGE_WATER_CENTER:
			return Images.Blocks.Orange.Water.WATER_CENTER;
		case ORANGE_WATER_RIGHT:
			return Images.Blocks.Orange.Water.WATER_RIGHT;
		case ORANGE_WATER_DOWN_LEFT:
			return Images.Blocks.Orange.Water.WATER_DOWN_LEFT;
		case ORANGE_WATER_DOWN:
			return Images.Blocks.Orange.Water.WATER_DOWN;
		case ORANGE_WATER_DOWN_RIGHT:
			return Images.Blocks.Orange.Water.WATER_DOWN_RIGHT;
		case ORANGE_WATER_CORNER_UP_LEFT:
			return Images.Blocks.Orange.Water.WATER_CORNER_UP_LEFT;
		case ORANGE_WATER_CORNER_UP_RIGHT:
			return Images.Blocks.Orange.Water.WATER_CORNER_UP_RIGHT;
		case ORANGE_WATER_CORNER_DOWN_LEFT:
			return Images.Blocks.Orange.Water.WATER_CORNER_DOWN_LEFT;
		case ORANGE_WATER_CORNER_DOWN_RIGHT:
			return Images.Blocks.Orange.Water.WATER_CORNER_DOWN_RIGHT;

		case ENTRANCE:
			return Images.Blocks.ENTRANCE;

		case GREEN_BRIDGE:
			return Images.Blocks.Green.BRIDGE;
		case ORANGE_BRIDGE:
			return Images.Blocks.Orange.BRIDGE;

		case GREEN_TREE_ENTRANCE_UP_LEFT:
			return Images.Blocks.Green.Entrances.TREE_ENTRANCE_UP_LEFT;
		case GREEN_TREE_ENTRANCE_UP:
			return Images.Blocks.Green.Entrances.TREE_ENTRANCE_UP;
		case GREEN_TREE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.Green.Entrances.TREE_ENTRANCE_UP_RIGHT;
		case GREEN_TREE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.Green.Entrances.TREE_ENTRANCE_DOWN_LEFT;
		case GREEN_TREE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.Green.Entrances.TREE_ENTRANCE_DOWN_RIGHT;

		case GREEN_STATUE_ENTRANCE_UP_LEFT:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_UP_LEFT;
		case GREEN_STATUE_ENTRANCE_UP:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_UP;
		case GREEN_STATUE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_UP_RIGHT;
		case GREEN_STATUE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_DOWN_LEFT;
		case GREEN_STATUE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_DOWN_RIGHT;

		case ORANGE_TREE_ENTRANCE_UP_LEFT:
			return Images.Blocks.Orange.Entrances.TREE_ENTRANCE_UP_LEFT;
		case ORANGE_TREE_ENTRANCE_UP:
			return Images.Blocks.Orange.Entrances.TREE_ENTRANCE_UP;
		case ORANGE_TREE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.Orange.Entrances.TREE_ENTRANCE_UP_RIGHT;
		case ORANGE_TREE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.Orange.Entrances.TREE_ENTRANCE_DOWN_LEFT;
		case ORANGE_TREE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.Orange.Entrances.TREE_ENTRANCE_DOWN_RIGHT;

		case ORANGE_STATUE_ENTRANCE_UP_LEFT:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_UP_LEFT;
		case ORANGE_STATUE_ENTRANCE_UP:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_UP;
		case ORANGE_STATUE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_UP_RIGHT;
		case ORANGE_STATUE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_DOWN_LEFT;
		case ORANGE_STATUE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_DOWN_RIGHT;

		case GREEN_STATUE_ENTRANCE_UP_ALT:
			return Images.Blocks.Green.Entrances.STATUE_ENTRANCE_UP_ALT;
		case ORANGE_STATUE_ENTRANCE_UP_ALT:
			return Images.Blocks.Orange.Entrances.STATUE_ENTRANCE_UP_ALT;

		case ROCKS_DARK_ORANGE_UP_LEFT:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_UP_LEFT;
		case ROCKS_DARK_ORANGE_UP:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_UP;
		case ROCKS_DARK_ORANGE_UP_RIGHT:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_UP_RIGHT;
		case ROCKS_DARK_ORANGE_DOWN_LEFT:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_DOWN_LEFT;
		case ROCKS_DARK_ORANGE_DOWN:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_DOWN;
		case ROCKS_DARK_ORANGE_DOWN_RIGHT:
			return Images.Blocks.Rocks.ROCKS_DARK_ORANGE_DOWN_RIGHT;

		case ROCKS_WHITE_UP_LEFT:
			return Images.Blocks.Rocks.ROCKS_WHITE_UP_LEFT;
		case ROCKS_WHITE_UP:
			return Images.Blocks.Rocks.ROCKS_WHITE_UP;
		case ROCKS_WHITE_UP_RIGHT:
			return Images.Blocks.Rocks.ROCKS_WHITE_UP_RIGHT;
		case ROCKS_WHITE_DOWN_LEFT:
			return Images.Blocks.Rocks.ROCKS_WHITE_DOWN_LEFT;
		case ROCKS_WHITE_DOWN:
			return Images.Blocks.Rocks.ROCKS_WHITE_DOWN;
		case ROCKS_WHITE_DOWN_RIGHT:
			return Images.Blocks.Rocks.ROCKS_WHITE_DOWN_RIGHT;
		case ROCKS_WHITE_SINGLE:
			return Images.Blocks.Rocks.ROCKS_WHITE_SINGLE;
		case WHITE_GRAVE:
			return Images.Blocks.White.GRAVE;
		case WHITE_LADDER:
			return Images.Blocks.White.LADDER;
		case WHITE_STAIRS:
			return Images.Blocks.White.STAIRS;
		case WHITE_WATER_UP_LEFT:
			return Images.Blocks.White.Water.WATER_UP_LEFT;
		case WHITE_WATER_UP:
			return Images.Blocks.White.Water.WATER_UP;
		case WHITE_WATER_UP_RIGHT:
			return Images.Blocks.White.Water.WATER_UP_RIGHT;
		case WHITE_WATER_LEFT:
			return Images.Blocks.White.Water.WATER_LEFT;
		case WHITE_WATER_CENTER:
			return Images.Blocks.White.Water.WATER_CENTER;
		case WHITE_WATER_RIGHT:
			return Images.Blocks.White.Water.WATER_RIGHT;
		case WHITE_WATER_DOWN_LEFT:
			return Images.Blocks.White.Water.WATER_DOWN_LEFT;
		case WHITE_WATER_DOWN:
			return Images.Blocks.White.Water.WATER_DOWN;
		case WHITE_WATER_DOWN_RIGHT:
			return Images.Blocks.White.Water.WATER_DOWN_RIGHT;
		case WHITE_WATER_CORNER_UP_LEFT:
			return Images.Blocks.White.Water.WATER_CORNER_UP_LEFT;
		case WHITE_WATER_CORNER_UP_RIGHT:
			return Images.Blocks.White.Water.WATER_CORNER_UP_RIGHT;
		case WHITE_WATER_CORNER_DOWN_LEFT:
			return Images.Blocks.White.Water.WATER_CORNER_DOWN_LEFT;
		case WHITE_WATER_CORNER_DOWN_RIGHT:
			return Images.Blocks.White.Water.WATER_CORNER_DOWN_RIGHT;
		case WHITE_TREE_ENTRANCE_UP_LEFT:
			return Images.Blocks.White.Entrances.TREE_ENTRANCE_UP_LEFT;
		case WHITE_TREE_ENTRANCE_UP:
			return Images.Blocks.White.Entrances.TREE_ENTRANCE_UP;
		case WHITE_TREE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.White.Entrances.TREE_ENTRANCE_UP_RIGHT;
		case WHITE_TREE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.White.Entrances.TREE_ENTRANCE_DOWN_LEFT;
		case WHITE_TREE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.White.Entrances.TREE_ENTRANCE_DOWN_RIGHT;
		case WHITE_STATUE_ENTRANCE_UP_LEFT:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_UP_LEFT;
		case WHITE_STATUE_ENTRANCE_UP:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_UP;
		case WHITE_STATUE_ENTRANCE_UP_RIGHT:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_UP_RIGHT;
		case WHITE_STATUE_ENTRANCE_DOWN_LEFT:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_DOWN_LEFT;
		case WHITE_STATUE_ENTRANCE_DOWN_RIGHT:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_DOWN_RIGHT;
		case WHITE_STATUE_ENTRANCE_UP_ALT:
			return Images.Blocks.White.Entrances.STATUE_ENTRANCE_UP_ALT;

		case GREEN_SAND:
			return Images.Blocks.Green.SAND;
		case ORANGE_SAND:
			return Images.Blocks.Orange.SAND;
		case WHITE_SAND:
			return Images.Blocks.White.SAND;

		case EMPTY_GRAY:
			return Images.Blocks.EMPTY_GRAY;

		case WHITE_BRIDGE:
			return Images.Blocks.White.BRIDGE;
		case WHITE_BUSH:
			return Images.Blocks.White.BUSH;
		}
		return null;
	}

	public boolean isWater()
	{
		switch(this)
		{
		case CBOX:
			return false;
		case EMPTY:
			return false;

		case ROCKS_GREEN_UP_LEFT:
			return false;
		case ROCKS_GREEN_UP:
			return false;
		case ROCKS_GREEN_UP_RIGHT:
			return false;
		case ROCKS_GREEN_DOWN_LEFT:
			return false;
		case ROCKS_GREEN_DOWN:
			return false;
		case ROCKS_GREEN_DOWN_RIGHT:
			return false;
		case ROCKS_GREEN_SINGLE:
			return false;

		case ROCKS_ORANGE_UP_LEFT:
			return false;
		case ROCKS_ORANGE_UP:
			return false;
		case ROCKS_ORANGE_UP_RIGHT:
			return false;
		case ROCKS_ORANGE_DOWN_LEFT:
			return false;
		case ROCKS_ORANGE_DOWN:
			return false;
		case ROCKS_ORANGE_DOWN_RIGHT:
			return false;
		case ROCKS_ORANGE_SINGLE:
			return false;

		case GREEN_BUSH:
			return false;
		case GREEN_LADDER:
			return false;
		case GREEN_STAIRS:
			return false;

		case GREEN_WATER_UP_LEFT:
			return false;
		case GREEN_WATER_UP:
			return false;
		case GREEN_WATER_UP_RIGHT:
			return false;
		case GREEN_WATER_LEFT:
			return false;
		case GREEN_WATER_CENTER:
			return true;
		case GREEN_WATER_RIGHT:
			return false;
		case GREEN_WATER_DOWN_LEFT:
			return false;
		case GREEN_WATER_DOWN:
			return false;
		case GREEN_WATER_DOWN_RIGHT:
			return false;
		case GREEN_WATER_CORNER_UP_LEFT:
			return false;
		case GREEN_WATER_CORNER_UP_RIGHT:
			return false;
		case GREEN_WATER_CORNER_DOWN_LEFT:
			return false;
		case GREEN_WATER_CORNER_DOWN_RIGHT:
			return false;

		case ORANGE_BUSH:
			return false;
		case ORANGE_LADDER:
			return false;
		case ORANGE_STAIRS:
			return false;

		case ORANGE_WATER_UP_LEFT:
			return false;
		case ORANGE_WATER_UP:
			return false;
		case ORANGE_WATER_UP_RIGHT:
			return false;
		case ORANGE_WATER_LEFT:
			return false;
		case ORANGE_WATER_CENTER:
			return true;
		case ORANGE_WATER_RIGHT:
			return false;
		case ORANGE_WATER_DOWN_LEFT:
			return false;
		case ORANGE_WATER_DOWN:
			return false;
		case ORANGE_WATER_DOWN_RIGHT:
			return false;
		case ORANGE_WATER_CORNER_UP_LEFT:
			return false;
		case ORANGE_WATER_CORNER_UP_RIGHT:
			return false;
		case ORANGE_WATER_CORNER_DOWN_LEFT:
			return false;
		case ORANGE_WATER_CORNER_DOWN_RIGHT:
			return false;

		case ENTRANCE:
			return false;

		case GREEN_BRIDGE:
			return false;
		case ORANGE_BRIDGE:
			return false;

		case GREEN_TREE_ENTRANCE_UP_LEFT:
			return false;
		case GREEN_TREE_ENTRANCE_UP:
			return false;
		case GREEN_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case GREEN_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case GREEN_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case GREEN_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case GREEN_STATUE_ENTRANCE_UP:
			return false;
		case GREEN_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case GREEN_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case GREEN_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;

		case ORANGE_TREE_ENTRANCE_UP_LEFT:
			return false;
		case ORANGE_TREE_ENTRANCE_UP:
			return false;
		case ORANGE_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case ORANGE_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case ORANGE_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case ORANGE_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case ORANGE_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case ORANGE_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;

		case GREEN_STATUE_ENTRANCE_UP_ALT:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP_ALT:
			return false;

		case ROCKS_DARK_ORANGE_UP_LEFT:
			return false;
		case ROCKS_DARK_ORANGE_UP:
			return false;
		case ROCKS_DARK_ORANGE_UP_RIGHT:
			return false;
		case ROCKS_DARK_ORANGE_DOWN_LEFT:
			return false;
		case ROCKS_DARK_ORANGE_DOWN:
			return false;
		case ROCKS_DARK_ORANGE_DOWN_RIGHT:
			return false;

		case ROCKS_WHITE_UP_LEFT:
			return false;
		case ROCKS_WHITE_UP:
			return false;
		case ROCKS_WHITE_UP_RIGHT:
			return false;
		case ROCKS_WHITE_DOWN_LEFT	:
			return false;
		case ROCKS_WHITE_DOWN:
			return false;
		case ROCKS_WHITE_DOWN_RIGHT:
			return false;
		case ROCKS_WHITE_SINGLE:
			return false;

		case WHITE_GRAVE:
			return false;
		case WHITE_LADDER:
			return false;
		case WHITE_STAIRS:
			return false;

		case WHITE_WATER_UP_LEFT:
			return false;
		case WHITE_WATER_UP:
			return false;
		case WHITE_WATER_UP_RIGHT:
			return false;
		case WHITE_WATER_LEFT:
			return false;
		case WHITE_WATER_CENTER:
			return true;
		case WHITE_WATER_RIGHT:
			return false;
		case WHITE_WATER_DOWN_LEFT:
			return false;
		case WHITE_WATER_DOWN:
			return false;
		case WHITE_WATER_DOWN_RIGHT:
			return false;
		case WHITE_WATER_CORNER_UP_LEFT:
			return false;
		case WHITE_WATER_CORNER_UP_RIGHT:
			return false;
		case WHITE_WATER_CORNER_DOWN_LEFT:
			return false;
		case WHITE_WATER_CORNER_DOWN_RIGHT:
			return false;

		case WHITE_TREE_ENTRANCE_UP_LEFT:
			return false;
		case WHITE_TREE_ENTRANCE_UP:
			return false;
		case WHITE_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case WHITE_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case WHITE_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case WHITE_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case WHITE_STATUE_ENTRANCE_UP:
			return false;
		case WHITE_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case WHITE_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case WHITE_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;
		case WHITE_STATUE_ENTRANCE_UP_ALT:
			return false;

		case GREEN_SAND:
			return false;
		case ORANGE_SAND:
			return false;
		case WHITE_SAND:
			return false;

		case EMPTY_GRAY:
			return false;

		case WHITE_BRIDGE:
			return false;
		case WHITE_BUSH:
			return false;

		default:
			return false;
		}
	}

	public boolean isPassible()
	{
		switch(this)
		{
		case CBOX:
			return false;
		case EMPTY:
			return true;

		case ROCKS_GREEN_UP_LEFT:
			return false;
		case ROCKS_GREEN_UP:
			return false;
		case ROCKS_GREEN_UP_RIGHT:
			return false;
		case ROCKS_GREEN_DOWN_LEFT:
			return false;
		case ROCKS_GREEN_DOWN:
			return false;
		case ROCKS_GREEN_DOWN_RIGHT:
			return false;
		case ROCKS_GREEN_SINGLE:
			return false;

		case ROCKS_ORANGE_UP_LEFT:
			return false;
		case ROCKS_ORANGE_UP:
			return false;
		case ROCKS_ORANGE_UP_RIGHT:
			return false;
		case ROCKS_ORANGE_DOWN_LEFT:
			return false;
		case ROCKS_ORANGE_DOWN:
			return false;
		case ROCKS_ORANGE_DOWN_RIGHT:
			return false;
		case ROCKS_ORANGE_SINGLE:
			return false;

		case GREEN_BUSH:
			return false;
		case GREEN_LADDER:
			return true;
		case GREEN_STAIRS:
			return false;

		case GREEN_WATER_UP_LEFT:
			return false;
		case GREEN_WATER_UP:
			return false;
		case GREEN_WATER_UP_RIGHT:
			return false;
		case GREEN_WATER_LEFT:
			return false;
		case GREEN_WATER_CENTER:
			return false;
		case GREEN_WATER_RIGHT:
			return false;
		case GREEN_WATER_DOWN_LEFT:
			return false;
		case GREEN_WATER_DOWN:
			return false;
		case GREEN_WATER_DOWN_RIGHT:
			return false;
		case GREEN_WATER_CORNER_UP_LEFT:
			return true;
		case GREEN_WATER_CORNER_UP_RIGHT:
			return true;
		case GREEN_WATER_CORNER_DOWN_LEFT:
			return true;
		case GREEN_WATER_CORNER_DOWN_RIGHT:
			return true;

		case ORANGE_BUSH:
			return false;
		case ORANGE_LADDER:
			return true;
		case ORANGE_STAIRS:
			return false;

		case ORANGE_WATER_UP_LEFT:
			return false;
		case ORANGE_WATER_UP:
			return false;
		case ORANGE_WATER_UP_RIGHT:
			return false;
		case ORANGE_WATER_LEFT:
			return false;
		case ORANGE_WATER_CENTER:
			return false;
		case ORANGE_WATER_RIGHT:
			return false;
		case ORANGE_WATER_DOWN_LEFT:
			return false;
		case ORANGE_WATER_DOWN:
			return false;
		case ORANGE_WATER_DOWN_RIGHT:
			return false;
		case ORANGE_WATER_CORNER_UP_LEFT:
			return true;
		case ORANGE_WATER_CORNER_UP_RIGHT:
			return true;
		case ORANGE_WATER_CORNER_DOWN_LEFT:
			return true;
		case ORANGE_WATER_CORNER_DOWN_RIGHT:
			return true;

		case ENTRANCE:
			return false;

		case GREEN_BRIDGE:
			return true;
		case ORANGE_BRIDGE:
			return true;

		case GREEN_TREE_ENTRANCE_UP_LEFT:
			return false;
		case GREEN_TREE_ENTRANCE_UP:
			return false;
		case GREEN_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case GREEN_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case GREEN_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case GREEN_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case GREEN_STATUE_ENTRANCE_UP:
			return false;
		case GREEN_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case GREEN_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case GREEN_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;

		case ORANGE_TREE_ENTRANCE_UP_LEFT:
			return false;
		case ORANGE_TREE_ENTRANCE_UP:
			return false;
		case ORANGE_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case ORANGE_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case ORANGE_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case ORANGE_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case ORANGE_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case ORANGE_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;

		case GREEN_STATUE_ENTRANCE_UP_ALT:
			return false;
		case ORANGE_STATUE_ENTRANCE_UP_ALT:
			return false;

		case ROCKS_DARK_ORANGE_UP_LEFT:
			return false;
		case ROCKS_DARK_ORANGE_UP:
			return false;
		case ROCKS_DARK_ORANGE_UP_RIGHT:
			return false;
		case ROCKS_DARK_ORANGE_DOWN_LEFT:
			return false;
		case ROCKS_DARK_ORANGE_DOWN:
			return false;
		case ROCKS_DARK_ORANGE_DOWN_RIGHT:
			return false;

		case ROCKS_WHITE_UP_LEFT:
			return false;
		case ROCKS_WHITE_UP:
			return false;
		case ROCKS_WHITE_UP_RIGHT:
			return false;
		case ROCKS_WHITE_DOWN_LEFT	:
			return false;
		case ROCKS_WHITE_DOWN:
			return false;
		case ROCKS_WHITE_DOWN_RIGHT:
			return false;
		case ROCKS_WHITE_SINGLE:
			return false;

		case WHITE_GRAVE:
			return false;
		case WHITE_LADDER:
			return true;
		case WHITE_STAIRS:
			return false;

		case WHITE_WATER_UP_LEFT:
			return false;
		case WHITE_WATER_UP:
			return false;
		case WHITE_WATER_UP_RIGHT:
			return false;
		case WHITE_WATER_LEFT:
			return false;
		case WHITE_WATER_CENTER:
			return false;
		case WHITE_WATER_RIGHT:
			return false;
		case WHITE_WATER_DOWN_LEFT:
			return false;
		case WHITE_WATER_DOWN:
			return false;
		case WHITE_WATER_DOWN_RIGHT:
			return false;
		case WHITE_WATER_CORNER_UP_LEFT:
			return false;
		case WHITE_WATER_CORNER_UP_RIGHT:
			return false;
		case WHITE_WATER_CORNER_DOWN_LEFT:
			return false;
		case WHITE_WATER_CORNER_DOWN_RIGHT:
			return false;

		case WHITE_TREE_ENTRANCE_UP_LEFT:
			return false;
		case WHITE_TREE_ENTRANCE_UP:
			return false;
		case WHITE_TREE_ENTRANCE_UP_RIGHT:
			return false;
		case WHITE_TREE_ENTRANCE_DOWN_LEFT:
			return false;
		case WHITE_TREE_ENTRANCE_DOWN_RIGHT:
			return false;

		case WHITE_STATUE_ENTRANCE_UP_LEFT:
			return false;
		case WHITE_STATUE_ENTRANCE_UP:
			return false;
		case WHITE_STATUE_ENTRANCE_UP_RIGHT:
			return false;
		case WHITE_STATUE_ENTRANCE_DOWN_LEFT:
			return false;
		case WHITE_STATUE_ENTRANCE_DOWN_RIGHT:
			return false;
		case WHITE_STATUE_ENTRANCE_UP_ALT:
			return false;

		case GREEN_SAND:
			return true;
		case ORANGE_SAND:
			return true;
		case WHITE_SAND:
			return true;

		case EMPTY_GRAY:
			return true;

		case WHITE_BRIDGE:
			return true;
		case WHITE_BUSH:
			return false;

		default:
			return false;
		}
	}
}
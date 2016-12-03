package map;

import reference.Images;

import java.awt.image.BufferedImage;

public enum Tile
{
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
	ROCKS_ORANGE_SINGLE;

	public static Tile parseID(int id)
	{
		switch(id)
		{
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
		}
		return null;
	}

	public static BufferedImage getSprite(Tile tile)
	{
		switch(tile)
		{
		case EMPTY:
			return Images.Blocks.EMPTY;
			
		case ROCKS_GREEN_UP_LEFT:
			return Images.Blocks.ROCKS_GREEN_UP_LEFT;
		case ROCKS_GREEN_UP:
			return Images.Blocks.ROCKS_GREEN_UP;
		case ROCKS_GREEN_UP_RIGHT:
			return Images.Blocks.ROCKS_GREEN_UP_RIGHT;
		case ROCKS_GREEN_DOWN_LEFT:
			return Images.Blocks.ROCKS_GREEN_DOWN_LEFT;
		case ROCKS_GREEN_DOWN:
			return Images.Blocks.ROCKS_GREEN_DOWN;
		case ROCKS_GREEN_DOWN_RIGHT:
			return Images.Blocks.ROCKS_GREEN_DOWN_RIGHT;
		case ROCKS_GREEN_SINGLE:
			return Images.Blocks.ROCKS_GREEN_SINGLE;
			
		case ROCKS_ORANGE_UP_LEFT:
			return Images.Blocks.ROCKS_ORANGE_UP_LEFT;
		case ROCKS_ORANGE_UP:
			return Images.Blocks.ROCKS_ORANGE_UP;
		case ROCKS_ORANGE_UP_RIGHT:
			return Images.Blocks.ROCKS_ORANGE_UP_RIGHT;
		case ROCKS_ORANGE_DOWN_LEFT:
			return Images.Blocks.ROCKS_ORANGE_DOWN_LEFT;
		case ROCKS_ORANGE_DOWN:
			return Images.Blocks.ROCKS_ORANGE_DOWN;
		case ROCKS_ORANGE_DOWN_RIGHT:
			return Images.Blocks.ROCKS_ORANGE_DOWN_RIGHT;
		case ROCKS_ORANGE_SINGLE:
			return Images.Blocks.ROCKS_ORANGE_SINGLE;
		}
		return null;
	}

	public boolean isPassible()
	{
		switch(this)
		{
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
		}

		return true;
	}
}

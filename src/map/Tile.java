package map;

import reference.Images;

import java.awt.image.BufferedImage;

public enum Tile
{
	EMPTY,
	ROCKS_UP_LEFT,
	ROCKS_UP,
	ROCKS_UP_RIGHT,
	ROCKS_DOWN_LEFT,
	ROCKS_DOWN,
	ROCKS_DOWN_RIGHT;

	public static Tile parseID(int id)
	{
		switch(id)
		{
		case 0:
			return EMPTY;
		case 1:
			return ROCKS_UP_LEFT;
		case 2:
			return ROCKS_UP;
		case 3:
			return ROCKS_UP_RIGHT;
		case 4:
			return ROCKS_DOWN_LEFT;
		case 5:
			return ROCKS_DOWN;
		case 6:
			return ROCKS_DOWN_RIGHT;
		}
		return null;
	}

	public static BufferedImage getSprite(Tile tile)
	{
		switch(tile)
		{
		case EMPTY:
			return Images.Blocks.EMPTY;
		case ROCKS_UP_LEFT:
			return Images.Blocks.ROCKS_UP_LEFT;
		case ROCKS_UP:
			return Images.Blocks.ROCKS_UP;
		case ROCKS_UP_RIGHT:
			return Images.Blocks.ROCKS_UP_RIGHT;
		case ROCKS_DOWN_LEFT:
			return Images.Blocks.ROCKS_DOWN_LEFT;
		case ROCKS_DOWN:
			return Images.Blocks.ROCKS_DOWN;
		case ROCKS_DOWN_RIGHT:
			return Images.Blocks.ROCKS_DOWN_RIGHT;
		}
		return null;
	}
}

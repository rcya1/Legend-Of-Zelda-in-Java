package components.tiles;

public class WarpTile
{
	private final int x;
	private final int y;

	private final int width;
	private final int height;

	private final char id;

	//All warp tiles cannot warp to tiles that are on the same row or column as it

	public WarpTile(int x, int y, int width, int height, char id)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getColumn(int tileWidth)
	{
		return x / tileWidth;
	}

	public int getRow(int tileHeight)
	{
		return y / tileHeight;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public char getId()
	{
		return id;
	}
}

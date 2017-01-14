package components.map;

import java.awt.*;

public class WarpTile extends MapItem
{
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

	public void update()
	{

	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.RED);
		g2d.fill(getRectangle());
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
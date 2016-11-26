package reference;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images
{
	public static final BufferedImage LINK_RIGHT = loadSingleImage("/sprites/link/LinkRight.png");
	public static final BufferedImage LINK_LEFT = loadSingleImage("/sprites/link/LinkLeft.png");
	public static final BufferedImage LINK_UP = loadSingleImage("/sprites/link/LinkUp.png");
	public static final BufferedImage LINK_DOWN = loadSingleImage("/sprites/link/LinkDown.png");

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
}

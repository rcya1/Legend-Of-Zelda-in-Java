package utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer
{
	private final Object synchronizationLock = new Object();
	private Clip clip;

	public static final SoundPlayer INTRO = new SoundPlayer("/audio/beginning/Intro.wav");
	public static final SoundPlayer OVERWORLD = new SoundPlayer("/audio/Overworld.wav");

	private SoundPlayer(String string)
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					SoundPlayer.class.getResource(string));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void play()
	{
		try
		{
			if(clip != null)
			{
				new Thread(() ->
				{
					synchronized(synchronizationLock)
					{
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}).start();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void stop()
	{
		if(clip == null) return;
		clip.stop();
	}

	public void loop()
	{
		try
		{
			if(clip != null)
			{
				new Thread(() ->
				{
					synchronized(synchronizationLock)
					{
						clip.stop();
						clip.setFramePosition(0);
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
				}).start();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setVolume(int relativeVolume)
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(relativeVolume);
	}

	public boolean isPlaying()
	{
		return clip.isActive();
	}
}

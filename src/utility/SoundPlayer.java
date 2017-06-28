package utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer
{
	private final Object synchronizationLock = new Object();
	private Clip clip;
	private boolean isLooping;

	public static final SoundPlayer INTRO = new SoundPlayer("/audio/beginning/Intro.wav");
	public static final SoundPlayer OVERWORLD = new SoundPlayer("/audio/Overworld.wav");
	public static final SoundPlayer ITEM = new SoundPlayer("/audio/Item.wav");

	private static final SoundPlayer[] soundPlayers = new SoundPlayer[]
			{INTRO, OVERWORLD, ITEM};

	private SoundPlayer(String path)
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					SoundPlayer.class.getResource(path));
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
				isLooping = false;
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
				isLooping = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void stopAll()
	{
		for(SoundPlayer soundPlayer : soundPlayers)
		{
			soundPlayer.stop();
		}
	}

	//Returns the first sound player it finds that is playing
	public static SoundPlayer getPlaying()
	{
		for(SoundPlayer soundPlayer : soundPlayers)
		{
			if(soundPlayer.isPlaying()) return soundPlayer;
		}

		return null;
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

	public boolean isFinished()
	{
		return clip.getMicrosecondPosition() == clip.getMicrosecondLength();
	}

	public boolean isLooping()
	{
		return isLooping;
	}
}

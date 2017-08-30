package utility;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

//An object that plays a given song
public class SoundPlayer
{
	private final Object synchronizationLock = new Object();    //Object that is used as a lock to prevent synchronization issues
	private Clip clip;                                          //The clip that stores the song
	private boolean isLooping;                                  //Whether the player is currently looping

	SoundPlayer(String path)
	{
		//Try to load in the clip
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

	//Plays the clip w/o looping
	public void play()
	{
		try
		{
			if(clip != null)
			{
				//Create a new thread to begin playing the clip
				new Thread(() ->
				{
					synchronized(synchronizationLock)
					{
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}).start();
				//Make sure the player does not think the clip is looping
				isLooping = false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//Stops the current clip
	public void stop()
	{
		if(clip == null) return;
		clip.stop();
	}

	//Loops the current clip
	public void loop()
	{
		try
		{
			if(clip != null)
			{
				//Create a new thread for looping the current clip
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

	//Sets the volume of the clip
	public void setVolume(int relativeVolume)
	{
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(relativeVolume);
	}

	//Returns whether the clip is playing
	public boolean isPlaying()
	{
		return clip.isActive();
	}

	//Returns whether the clip has finished playing
	public boolean isFinished()
	{
		return clip.getMicrosecondPosition() == clip.getMicrosecondLength();
	}

	//Returns whether the clip is looping
	public boolean isLooping()
	{
		return isLooping;
	}
}

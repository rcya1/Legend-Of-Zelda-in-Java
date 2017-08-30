package utility;

//Used to manage all of the sound clips
public class SoundManager
{
    //All of the sound players
    public static final SoundPlayer INTRO = new SoundPlayer("/audio/beginning/Intro.wav");
    public static final SoundPlayer OVERWORLD = new SoundPlayer("/audio/Overworld.wav");
    public static final SoundPlayer ITEM = new SoundPlayer("/audio/Item.wav");

    //Array that stores all of the sound players
    private static final SoundPlayer[] soundPlayers = new SoundPlayer[]
            {INTRO, OVERWORLD, ITEM};

    //Stops every sound player
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
}

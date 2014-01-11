package cons;

import java.io.File;
import java.io.Serializable;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

class SoundPlayer implements Serializable
{
	Clip clip;
	static double volume = 0;
	String path;

	static SoundPlayer soundBeep = new SoundPlayer("sounds/beep.wav");
	static SoundPlayer soundDoorOpen = new SoundPlayer("sounds/door_open.wav");
	static SoundPlayer soundDoorClose = new SoundPlayer("sounds/door_close.wav");
	static SoundPlayer soundBackground = new SoundPlayer("sounds/background.wav");
	static SoundPlayer soundStairs = new SoundPlayer("sounds/stairs.wav");
	static SoundPlayer soundMenu = new SoundPlayer("sounds/menu.wav");
	static SoundPlayer soundCity1 = new SoundPlayer("sounds/city1.wav"); // Wissenschaftlerstadt
	static SoundPlayer soundCity2 = new SoundPlayer("sounds/city2.wav"); // lebensfrohe Stadt
	static SoundPlayer soundCity3 = new SoundPlayer("sounds/city3.wav"); // Anfangsstadt
	
	public SoundPlayer(String path)
	{
		this.path = path;
	}
	
	public static void setVolume(double parVolume)
	{
		volume = (parVolume - 100) / 100 * 80;
	}
	
	public void play()
	{
		try
		{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
            AudioFormat af = audioInputStream.getFormat();
            int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
            byte[] audio = new byte[size];
            DataLine.Info info = new DataLine.Info(Clip.class, af, size);
            audioInputStream.read(audio, 0, size);

            clip = (Clip) AudioSystem.getLine(info);
            clip.open(af, audio, 0, size);
            
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue((float) volume); // Reduce volume by 10 decibels.
            
            clip.start();
        }
		catch(Exception e)
        {
        	e.printStackTrace(); 
        }
	}
	
	public void stop()
	{
		try
		{
            clip.stop();
        }
		catch(Exception e)
        {
        	e.printStackTrace(); 
        }
	}
}

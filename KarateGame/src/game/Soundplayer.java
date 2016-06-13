package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Soundplayer {
	
	private Clip clip;
	private FloatControl volume;

	public Soundplayer(String path) {
		AudioInputStream ais;
		try
			{
				ais = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResource(path));
				clip = AudioSystem.getClip();
				clip.open(ais);
				volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}			

	public void play(){
		
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	
	public void setVolume(float value){
		volume.setValue(value);
	}

	public boolean isPlaying()
	{
		return clip.isRunning();
	}
}

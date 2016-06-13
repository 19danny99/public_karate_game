package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Soundplayer {
	
	private Clip clip;
	private FloatControl volume;
	/**
	 * Konstruktor
	 * @param path
	 */
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
	/**
	 * Diese Methode startet den Sound
	 */
	public void play(){
		
		clip.setMicrosecondPosition(0);
		clip.start();
	}
	/*
	 *Diese Methode stoppt den Sound 
	 */
	public void stop()
	{
		clip.stop();
	}
	/**
	 * Diese Methode wiederholt den Sound
	 */
	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}
	/**
	 * set-Methode
	 * @param value
	 */
	public void setVolume(float value){
		volume.setValue(value);
	}
	/**
	 * Diese Methode schaut ob ein Sound gerade spielt
	 * @return
	 */
	public boolean isPlaying()
	{
		return clip.isRunning();
	}
}

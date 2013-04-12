package net.teamwraith.testgame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer {

	private int stage = 0; 
	Play screen;
	File soundFile = new File("res/music/Test" + stage +".wav");
	AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
	SourceDataLine.Info info = new SourceDataLine.Info(Clip.class, sound.getFormat());
	SourceDataLine clip = (SourceDataLine) AudioSystem.getLine(info);
	
	private int count = 0;
	public MusicPlayer(Play playScreen) throws Exception {
		screen = playScreen;
		clip.open(sound.getFormat(), 22000);

		// play the sound clip (Initial run!!!)
		((Clip) clip).loop(count);
		((Clip) clip).setLoopPoints(1, -1);
	    clip.addLineListener(new LineListener() {
	    	public void update(LineEvent event) {
	    		if (event.getType() ==LineEvent.Type.STOP) {
	    			stage++;
					try {
						resetSounds();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			try {
						clip.open(sound);
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			clip.loop(count);
	    			
	    		}
	    	}
	    });
	    
	}
	
	public int getCount() {
		return count;
	}
	
	public void resetSounds() throws Exception {
		soundFile = new File("res/music/Test" + stage +".wav");
		sound = AudioSystem.getAudioInputStream(soundFile);
		info = new DataLine.Info(Clip.class, sound.getFormat());
		clip = (Clip) AudioSystem.getLine(info);
		count++;
		//Play the sound clip (progressionstate)
		clip.addLineListener(new LineListener() {
	    	public void update(LineEvent event) {
	    		if (event.getType() ==LineEvent.Type.STOP) {
	    			stage++;
					try {
						resetSounds();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			try {
						clip.open(sound);
					} catch (LineUnavailableException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			clip.loop(count);
	    			
	    		}
	    	}
	    });
	}
	
}

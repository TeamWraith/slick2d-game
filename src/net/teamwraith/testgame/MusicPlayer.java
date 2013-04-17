package net.teamwraith.testgame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {

	private int stage = 0; 
	private int count = 0;
	
	Play screen;
	File soundFile = new File("res/music/Test" + stage +".wav");
	AudioInputStream sound;
	SourceDataLine.Info info = new SourceDataLine.Info(Clip.class, sound.getFormat());
	SourceDataLine clip;
	
	public MusicPlayer(Play playScreen) {
		try {
			sound = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		screen = playScreen;
		try {
			clip = (SourceDataLine) AudioSystem.getLine(info);
			clip.open(sound.getFormat(), 22000);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}

		// play the sound clip (Initial run!)
		((Clip) clip).loop(count);
		((Clip) clip).setLoopPoints(1, -1);
	    clip.addLineListener(new LineListener() {
	    	public void update(LineEvent event) {
	    		if (event.getType() ==LineEvent.Type.STOP) {
	    			stage++;
					try {
						resetSounds();
						clip.open(sound);
					} catch (LineUnavailableException | IOException e) {
						e.printStackTrace();
					}
	    			
	    			((Clip) clip).loop(count);
	    			
	    		}
	    	}
	    });
	}
	
	public int getCount() {
		return count;
	}
	
	public void resetSounds() {
		soundFile = new File("res/music/Test" + stage +".wav");
		
		try {
			sound = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		
		try {
			clip = (SourceDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		count++;
		
		//Play the sound clip (progressionstate)
		clip.addLineListener(new LineListener() {
	    	public void update(LineEvent event) {
	    		if (event.getType() ==LineEvent.Type.STOP) {
	    			stage++;
					resetSounds();
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

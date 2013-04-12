package net.teamwraith.testgame;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{

	Graphics g;
	Image player;
	
	MusicPlayer mp;
	
	float playerX = 200;
	float playerY = 200;
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Image("res/textures/player.png");
		try {
			mp = new MusicPlayer(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		this.g = g;
		g.drawImage(player, playerX, playerY);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		// --- Movement ---
		//UP
		if (input.isKeyDown(Input.KEY_W)) { if (input.isKeyDown(Input.KEY_LSHIFT)){ playerY -= .8;} else { playerY -= .6;}}
		if (input.isKeyDown(Input.KEY_S)) { if (input.isKeyDown(Input.KEY_LSHIFT)){ playerY += .8;} else { playerY += .6;}}
		if (input.isKeyDown(Input.KEY_A)) { if (input.isKeyDown(Input.KEY_LSHIFT)){ playerX -= .8;} else { playerX -= .6;}}
		if (input.isKeyDown(Input.KEY_D)) { if (input.isKeyDown(Input.KEY_LSHIFT)){ playerX += .8;} else { playerX += .6;}}
		
		if (playerX >= gc.getScreenWidth())
			playerX = 0;
		if (playerY >= 720)
			playerY = 0;
		
		if (playerX < 0)
			playerX = gc.getScreenWidth();
		if (playerY < 0)
			playerY = 720;
	}
	

	
	public int getID(){
		return 1;
	}
	
	public Graphics getGraphics() {
		return g;
	}
	
}

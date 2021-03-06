package net.teamwraith.testgame;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import net.teamwraith.testgame.states.Menu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends StateBasedGame {

	public static final String GAME_NAME = "slick2d-game";
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int OPTIONS = 2;
	public static Properties properties = new Properties();
	public static boolean controllerEnabled = false;
	public static final GraphicsDevice defaultGraphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	public Game(final String GAME_NAME){
		super(GAME_NAME);
		this.addState(new Menu(MENU));
	}
	
	public void initStatesList(GameContainer gc) {
		try {
			getState(MENU).init(gc, this);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.enterState(MENU);
	}
	
	public static void main (String[] args) {
		AppGameContainer appgc;
		
		try {
			FileInputStream reader = new FileInputStream("game.ini");
			properties.load(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			appgc = new AppGameContainer(new Game(GAME_NAME));
			appgc.setDisplayMode(
				Integer.parseInt(properties.getProperty("width")), 
				Integer.parseInt(properties.getProperty("height")), 
				Boolean.parseBoolean(properties.getProperty("fullscreen"))
			);
			appgc.setVSync(false);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}


}

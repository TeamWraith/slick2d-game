package net.teamwraith.testgame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{

	public static final String GAME_NAME = "TESTGAME";
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static Properties properties = new Properties();
	
	public Game(final String GAME_NAME){
		super(GAME_NAME);
		this.addState(new Menu(MENU));
	}
	
	public void initStatesList(GameContainer gc) {
		try {
			this.getState(MENU).init(gc, this);
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
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}


}

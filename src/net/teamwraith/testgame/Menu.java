package net.teamwraith.testgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private Image bg;
	public static final int BUTTON_DESIGN = 10;
	private MouseOverArea startButton;
	private MouseOverArea fullscreenButton;
	
	public Menu(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		startButton = new MouseOverArea(gc, bg, 10, 45, 80, 15);
		fullscreenButton = new MouseOverArea(gc, bg, 10, 95, 80, 15);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.drawString("Start game!", 10, 25);
		g.drawString("Screen Res.", 10, 75);
		
		startButton.render(gc, g);
		fullscreenButton.render(gc, g);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (input.isMouseButtonDown(0)) {
			if(startButton.isMouseOver()) {
				sbg.addState(new Play(1));
				sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);
			} else if (fullscreenButton.isMouseOver()) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}
	}
	
	public int getID(){
		return 0;
	}
}

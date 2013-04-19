package net.teamwraith.testgame;

import java.awt.Color;

import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {

	private Image bg;
	private int buttonDesign = 10;
	
	public Menu(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.drawString("Start game!", 10, 25);
		g.fillRoundRect(10, 45, 80, 15, buttonDesign);
		
		g.drawString("Screen Res.", 10, 75);
		g.fillRoundRect(10, 95, 80, 15, buttonDesign);
		
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int Xpos = Mouse.getX();
		int Ypos = Mouse.getY();
		
		if (input.isMouseButtonDown(0) {
			if((Xpos > 10 && Xpos < 90) && (Ypos > 660 && Ypos < 675)) {
				sbg.addState(new Play(1));
				sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);
			} else if ((Xpos > 10 && Xpos < 90) &&(Ypos > 610 && Ypos < 625)) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}
	}
	
	public int getID(){
		return 0;
	}
}

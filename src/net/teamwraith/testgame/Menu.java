package net.teamwraith.testgame;

import java.awt.Dimension;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private Image bg;
	
	private Shape startButton;
	private Shape fullscreenButton;
	
	private int selectedItem = 0;
	private float selectionPos = 64;
	private Polygon selection;
	
	private boolean controllersEnabled = true;
	public Menu(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		startButton = new Rectangle(10, 45, 80, 15);
		fullscreenButton = new Rectangle(10, 95, 80, 15);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.setColor(Color.white);
		g.drawString("Start game!", 10, 25);
		g.drawString("Toggle Fullscreen.", 10, 75);
		g.setColor(Color.orange);
		g.fill(startButton);
		g.fill(fullscreenButton);
		
		g.setColor(Color.magenta);
		selection = new Polygon(new float[] {5,selectionPos, 95, selectionPos, 95, selectionPos-22, 5, selectionPos-22});
		g.draw(selection);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (controllersEnabled) {
			ControllerControls(input, gc, sbg);
		}
		else {
			NormalControls(input, gc, sbg);
		}

	}
	
	private void ControllerControls(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		if (input.isControllerUp(2) && (selectedItem > 0)) { 
			selectionPos = selectionPos - 50;
			selectedItem--;
		}
		if (input.isControllerDown(2) && (selectedItem < 1)) { 
			selectionPos = selectionPos + 50;
			selectedItem++;
		}
		
		if (input.isButton1Pressed(2)) {
			if (selection.contains(startButton)) {
				stateOne(gc, sbg);
			}
			if (selection.contains(fullscreenButton)) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}

	}

	private void NormalControls(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		if (input.isKeyPressed(Input.KEY_W) && (selectedItem > 0)) { 
			selectionPos = selectionPos - 50;
			selectedItem--;
		}
		if (input.isKeyPressed(Input.KEY_S) && (selectedItem < 1)) { 
			selectionPos = selectionPos + 50;
			selectedItem++;
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER)) {
			if (selection.contains(startButton)) {
				stateOne(gc, sbg);
			}
			if (selection.contains(fullscreenButton)) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}

		if (input.isMouseButtonDown(0)) {
			if(startButton.contains(input.getMouseX(), input.getMouseY())) {
				stateOne(gc, sbg);
			}
			if (fullscreenButton.contains(input.getMouseX(), input.getMouseY())) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}
	}
	
	public int getID(){
		return 0;
	}
	
	private void stateOne(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sbg.addState(new Play(1));
		sbg.getState(1).init(gc, sbg);
		sbg.enterState(1);
	}
}

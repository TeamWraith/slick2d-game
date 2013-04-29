package net.teamwraith.testgame.states;

import net.teamwraith.testgame.Keys;
import net.teamwraith.testgame.Keys.Bindings;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState {

	private Image bg;
	
	private Shape startButton, optionsButton;
	
	private int selectedItem = 0, selectionPos = 64;
	private Polygon selection;
	
	private Options options;
	private Play play;
	
	public Menu(int state) {
		play = new Play(state+1);
		options = new Options(state+2);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		startButton = new Rectangle(10, 45, 80, 15);
		optionsButton = new Rectangle(10, 95, 80, 15);
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.setColor(Color.white);
		g.drawString("Start game!", 10, 25);
		g.drawString("Options", 10, 75);
		
		g.setColor(Color.orange);
		g.fill(startButton);
		g.fill(optionsButton);
		
		g.setColor(Color.magenta);
		selection = new Polygon(new float[] {5,selectionPos, 95, selectionPos, 95, selectionPos-22, 5, selectionPos-22});
		g.draw(selection);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		controls(input, gc, sbg);
	}
	
	// TODO: Implement full controller support later.
	// TODO: Create methods for each button's function WITHOUT a messy controls method.
	private void controls(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		if (Keys.Bindings.UP.getKeyTyped(input) && (selectedItem > 0)) { 
			selectionPos = selectionPos - 50;
			selectedItem--;
		} else if (Keys.Bindings.DOWN.getKeyTyped(input) && (selectedItem < 1)) { 
			selectionPos = selectionPos + 50;
			selectedItem++;
		}
		
		if (Keys.Bindings.SELECT.getKeyTyped(input)) {
			if (selection.contains(startButton)) {
				stateOne(gc, sbg);
			} else if (selection.contains(optionsButton)) {
				options(gc, sbg);
			}
		}

		if (input.isMousePressed(0)) {
			if(startButton.contains(input.getMouseX(), input.getMouseY())) {
				stateOne(gc, sbg);
			} else if (optionsButton.contains(input.getMouseX(), input.getMouseY())) {
				options(gc, sbg);
			}
		}
	}
	
	public int getID(){
		return 0;
	}
	
	private void stateOne(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sbg.addState(play);
		sbg.getState(play.getID()).init(gc, sbg);
		sbg.enterState(play.getID());
	}
	
	private void options(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sbg.addState(options);
		sbg.getState(options.getID()).init(gc, sbg);
		sbg.enterState(options.getID());
	}
}

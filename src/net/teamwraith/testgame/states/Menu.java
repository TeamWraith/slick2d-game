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
	private Shape[] buttons = {startButton, optionsButton};
	
	private int selectedItem = 0;
	private boolean selected = false;
	private Polygon selection;
	
	private boolean usingKeyboard = false;
	private int prevMouseX, prevMouseY; 
	
	private Options options;
	private Play play;
	
	public Menu(int state) {
		play = new Play(state+1);
		options = new Options(state+2);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttons[0] = new Rectangle(10, 45, 80, 15);
		buttons[1] = new Rectangle(10, 95, 80, 15);
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.setColor(Color.white);
		g.drawString("Start game!", 10, 25);
		g.drawString("Options", 10, 75);
		
		g.setColor(Color.orange);
		for (int i=0; i < buttons.length; i++) {
			g.fill(buttons[i]);
		}
		
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		reInit(gc, sbg);
		Input input = gc.getInput();
		controls(input, gc, sbg);
	}
	
	public void reInit(GameContainer gc, StateBasedGame sbg) {
		if (selected) {
			buttons[0] = new Rectangle(10, 45, 80, 15);
			buttons[1] = new Rectangle(10, 95, 80, 15);
		}
		
		selection = new Polygon(new float[] {
				buttons[selectedItem].getMinX()-5, buttons[selectedItem].getMinY()-5,
				buttons[selectedItem].getMinX()-5, buttons[selectedItem].getMaxY()+5,
				buttons[selectedItem].getMaxX()+5, buttons[selectedItem].getMaxY()+5,
				buttons[selectedItem].getMaxX()+5, buttons[selectedItem].getMinY()-5
				});
		
		if (prevMouseX != gc.getInput().getMouseX() || prevMouseY != gc.getInput().getMouseY()) {
			usingKeyboard = false;
		}
	}
	
	// TODO: Implement full controller support later.
	// TODO: Create methods for each button's function WITHOUT a messy controls method.
	private void controls(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		if (Keys.Bindings.UP.getKeyTyped(input) && (selectedItem > 0)) { 
			selectedItem--;
			selected = false;
			usingKeyboard = true;
			prevMouseX = input.getMouseX();
			prevMouseY = input.getMouseY();
		} else if (Keys.Bindings.DOWN.getKeyTyped(input) && selectedItem < buttons.length-1) {
			selectedItem++;
			selected = false;
			usingKeyboard = true;
			prevMouseX = input.getMouseX();
			prevMouseY = input.getMouseY();
		}
		buttonIndicator(gc);
		
		if (Keys.Bindings.SELECT.getKeyTyped(input)) {
			if (selection.contains(buttons[0])) {
				stateOne(gc, sbg);
			} else if (selection.contains(buttons[1])) {
				options(gc, sbg);
			}
		}

		if (input.isMousePressed(0)) {
			if(buttons[0].contains(input.getMouseX(), input.getMouseY())) {
				stateOne(gc, sbg);
			} else if (buttons[1].contains(input.getMouseX(), input.getMouseY())) {
				options(gc, sbg);
			}
		}
	}
	
	private void buttonIndicator(GameContainer gc) {

		if (usingKeyboard) {
			buttons[selectedItem] = new Rectangle(
					buttons[selectedItem].getMinX()+5, 
					buttons[selectedItem].getMinY()-(5/2),
					buttons[selectedItem].getMaxX()-buttons[selectedItem].getMinX()+5, 
					buttons[selectedItem].getMaxY()-buttons[selectedItem].getMinY()+5);
			selected = true;
		}
		else {
			for (int i=0; i < buttons.length; i++) {
				if (buttons[i].contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
					selectedItem = i;
					buttons[selectedItem] = new Rectangle(
							buttons[selectedItem].getMinX()+5, 
							buttons[selectedItem].getMinY()-(5/2),
							buttons[selectedItem].getMaxX()-buttons[selectedItem].getMinX()+5, 
							buttons[selectedItem].getMaxY()-buttons[selectedItem].getMinY()+5);
					selected = true;
				}
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

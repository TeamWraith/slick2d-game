package net.teamwraith.testgame.states;

import net.teamwraith.testgame.Game;
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


public class Options extends BasicGameState { 
	
	private Image bg;
	
	private Shape controlsButton, ratioButton, resButton, fullscreenButton, backButton;
	private Shape[] buttons = {controlsButton, ratioButton, resButton, fullscreenButton, backButton};
	
	private int selectedItem = 0;
	private boolean selected = false;
	private Polygon selection;
	
	private boolean usingKeyboard = false;
	private int prevMouseX, prevMouseY; 
	
	private String aspectRatio;
	private String resolution;
	private int ratio = 1;
	private int res = 4;
	
	private int state;

	
	public Options(int state) {
		this.state = state; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		buttons[0] = new Rectangle(10, 45, 80, 15);
		buttons[1] = new Rectangle(10, 95, 80, 15);
		buttons[2] = new Rectangle(10, 145, 80, 15);
		buttons[3] = new Rectangle(10, 195, 80, 15);
		buttons[4] = new Rectangle(10, gc.getHeight() - 40, 80, 15);
		
		ratioControl(gc.getInput(), gc, sbg);
		resolutionControl(gc.getInput(), gc, sbg);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		bg = new Image("res/textures/BackGround01.png");
		g.drawImage(bg, 0, 0);
		
		g.setColor(Color.white);
		g.drawString("Controls", 10, 25);
		g.drawString("Aspect Ratio", 10, 75);
		g.drawString("Resolution", 10, 125);
		g.drawString("Fullscreen", 10, 175);
		
		g.fill(new Rectangle(110, 95, 140, 15));
		g.fill(new Rectangle(110, 145, 140, 15));
		
		g.setColor(Color.orange);

		for (int i=0; i < buttons.length; i++) {
			g.fill(buttons[i]);
		}

		g.setColor(Color.magenta);
		if (isNative()) {
			g.drawString(resolution+"(Native)", 110, 145);
		} else {
			g.drawString(resolution, 110, 145);
		}
		g.drawString(aspectRatio, 110, 95);

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
			buttons[2] = new Rectangle(10, 145, 80, 15);
			buttons[3] = new Rectangle(10, 195, 80, 15);
			buttons[4] = new Rectangle(10, gc.getHeight() - 40, 80, 15);
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
				System.out.println("TODO");
			} else if (selection.contains(buttons[1])) {
				ratioControl(input, gc, sbg);
			} else if (selection.contains(buttons[2])) {
				resolutionControl(input, gc, sbg);
			} else if (selection.contains(buttons[3])) {
				gc.setFullscreen(!gc.isFullscreen());
			} else if (selection.contains(buttons[4])) {
				sbg.enterState(0);
			}
		}

		if (input.isMousePressed(0)) {
			if(buttons[0].contains(input.getMouseX(), input.getMouseY())) {
				System.out.println("TODO");
			} else if (buttons[1].contains(input.getMouseX(), input.getMouseY())) {
				ratioControl(input, gc, sbg);
			} else if (buttons[2].contains(input.getMouseX(), input.getMouseY())) {
				resolutionControl(input, gc, sbg);
			} else if (buttons[3].contains(input.getMouseX(), input.getMouseY())) {
				gc.setFullscreen(!gc.isFullscreen());
			} else if (buttons[4].contains(input.getMouseX(), input.getMouseY())) {
				sbg.enterState(0);
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
	
	private void resolutionControl(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		String[] res = {
			"640x480", "720x576",
			"800x600", "1024x768",
			"1152x864", "1280x960", "1280x1024",
			
			"848x480", "852x480", 
			"1152x648", "1280x720",

			"720x480", "1280x768", "1280x800"};
		
		if (ratio == 0) {
			if (this.res >= 6) {
				this.res = 0;
			}
			else {
				this.res++;
			}
		}
		else if (ratio == 1) {
			if (this.res >= 10) {
				this.res = 7;
			}
			else {
				this.res++;
			}
		}
		else if (ratio == 2) {
			if (this.res >= 13) {
				this.res = 11;
			}
			else {
				this.res++;
			}
		}
		resolution = res[this.res];
	}

	private void ratioControl(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		String[] ratio = {
				"4:3", "16:9", "16:10"};
		
		if (this.ratio >= 2) {
			this.ratio = 0;
		}
		else {
			this.ratio++;
		}
		aspectRatio = ratio[this.ratio];
		resolutionControl(input, gc, sbg);
	}
	
	private boolean isNative() {
		if (resolution.equals(Game.defaultGraphicsDevice.getDisplayMode().getWidth()+"x"
			+Game.defaultGraphicsDevice.getDisplayMode().getHeight())) {
			return true;
		} else {
			return false;
		}
			
	}
	
	public int getID() {
		return state;
	}
}
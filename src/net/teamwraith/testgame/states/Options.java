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
	
	private int selectedItem = 0;
	private int selectionPos = 64;
	private Polygon selection;

	private String aspectRatio;
	private String resolution;
	private int ratio = 1;
	private int res = 4;
	
	private int state;
	
	public Options(int state) {
		this.state = state; 
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		controlsButton = new Rectangle(10, 45, 80, 15);
		ratioButton = new Rectangle(10, 95, 80, 15);
		resButton = new Rectangle(10, 145, 80, 15);
		fullscreenButton = new Rectangle(10, 195, 80, 15);
		backButton = new Rectangle(10, gc.getHeight() - 40, 80, 15);
		
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
		g.fill(controlsButton);
		g.fill(ratioButton);
		g.fill(resButton);
		g.fill(fullscreenButton);
		g.fill(backButton);
		
		g.setColor(Color.magenta);
		selection = new Polygon(new float[] {5,selectionPos, 95, selectionPos, 95, selectionPos-22, 5, selectionPos-22});
		
		g.draw(selection);
		if (isNative()) {
			g.drawString(resolution+"(Native)", 110, 145);
		} else {
			g.drawString(resolution, 110, 145);
		}
		g.drawString(aspectRatio, 110, 95);
	}
	
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		controls(input, gc, sbg);
	}

	private void controls(Input input, GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		if (Keys.Bindings.UP.getKeyTyped(input) && (selectedItem > 0)) { 
			selectionPos = selectionPos - 50;
			selectedItem--;
		} else if (Keys.Bindings.DOWN.getKeyTyped(input) && (selectedItem < 3)) { 
			selectionPos = selectionPos + 50;
			selectedItem++;
		}
		
		if (Keys.Bindings.SELECT.getKeyTyped(input)) {
			if (selection.contains(controlsButton)) {
				System.out.println("TODO");
			} else if (selection.contains(ratioButton)) {
				ratioControl(input, gc, sbg);
			} else if (selection.contains(resButton)) {
				resolutionControl(input, gc, sbg);
			} else if (selection.contains(fullscreenButton)) {
				gc.setFullscreen(!gc.isFullscreen());
			}
		}

		if (input.isMousePressed(0)) {
			if(controlsButton.contains(input.getMouseX(), input.getMouseY())) {
				System.out.println("TODO");
			} else if (ratioButton.contains(input.getMouseX(), input.getMouseY())) {
				ratioControl(input, gc, sbg);
			} else if (resButton.contains(input.getMouseX(), input.getMouseY())) {
				resolutionControl(input, gc, sbg);
			} else if (fullscreenButton.contains(input.getMouseX(), input.getMouseY())) {
				gc.setFullscreen(!gc.isFullscreen());
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
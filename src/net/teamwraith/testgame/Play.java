package net.teamwraith.testgame;

import java.util.ArrayList;

import net.teamwraith.entities.Entity;
import net.teamwraith.entities.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {

	Player player;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Player();
		entities.add(player);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(
			player.getImage().getScaledCopy((int) player.getShape().getWidth(), (int) player.getShape().getHeight()), 
			player.getShape().getCenterX(), 
			player.getShape().getCenterY()
		);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		// --- Movement ---
		// This will be rebindable later on
		if (input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT)) {
			player.setSpeed(Player.SPRINT_SPEED);
		} else {
			player.setSpeed(Player.NORMAL_SPEED);
		}
		
		if (input.isKeyDown(Input.KEY_W)) { 
			player.getShape().setY(player.getShape().getY() - player.getSpeed());
		}
		if (input.isKeyDown(Input.KEY_S)) { 
			player.getShape().setY(player.getShape().getY() + player.getSpeed());
		}
		if (input.isKeyDown(Input.KEY_A)) { 
			player.getShape().setX(player.getShape().getX() - player.getSpeed());
		}
		if (input.isKeyDown(Input.KEY_D)) {
			player.getShape().setX(player.getShape().getX() + player.getSpeed());
		}
		
		// We really should define an area that isn't dependent on resolution.
		if (player.getShape().getX() >= gc.getWidth()) {
			player.getShape().setX(0);	
		} 
		if (player.getShape().getY() >= gc.getHeight()) {
			player.getShape().setY(0);
		}
		
		if (player.getShape().getX() < 0) {
			player.getShape().setX(gc.getWidth());
		}
		if (player.getShape().getY() < 0) {
			player.getShape().setY(gc.getHeight());
		}
	}
	
	public int getID(){
		return 1;
	}
	
}

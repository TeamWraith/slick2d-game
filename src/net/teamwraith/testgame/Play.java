package net.teamwraith.testgame;

import net.teamwraith.entities.Player;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState{

	Player player;
	
	public Play(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		player = new Player();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(player.getImage(), player.getX(), player.getY());
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
		
		if (input.isKeyDown(Input.KEY_W)) player.setY(player.getY() - player.getSpeed());
		if (input.isKeyDown(Input.KEY_S)) player.setY(player.getY() + player.getSpeed());
		if (input.isKeyDown(Input.KEY_A)) player.setX(player.getX() - player.getSpeed());
		if (input.isKeyDown(Input.KEY_D)) player.setX(player.getX() + player.getSpeed());
		
		// We really should define an area that isn't dependent on resolution.
		if (player.getX() >= gc.getScreenWidth()) {
			player.setX(0);	
		} 
		if (player.getY() >= gc.getScreenHeight()) {
			player.setY(0);
		}
		
		if (player.getX() < 0) {
			player.setX(gc.getScreenWidth());
		}
		if (player.getY() < 0) {
			player.setY(gc.getScreenWidth());
		}
	}
	
	public int getID(){
		return 1;
	}
	
}

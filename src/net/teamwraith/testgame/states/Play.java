package net.teamwraith.testgame.states;

import java.util.ArrayList;
import java.util.Random;

import net.teamwraith.entities.Enemy;
import net.teamwraith.entities.Entity;
import net.teamwraith.entities.Player;
import net.teamwraith.testgame.Keys;
import net.teamwraith.testgame.Keys.Bindings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState { 
	
	Player player;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	private int state;
	
	public Play(int state){
		this.state = state;
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
		keyboardMovement(input);
		holdPlayerInScreen(gc);
		detectCollisions();

	}
	
	private void keyboardMovement(Input input) {
		
		if (Keys.Bindings.SPRINT.getKey(input)) {
			player.setSpeed(Player.SPRINT_SPEED);
		} else {
			player.setSpeed(Player.NORMAL_SPEED);
		}
		if (Keys.Bindings.UP.getKey(input)) { 
			player.getShape().setY(player.getShape().getY() - player.getSpeed());
		}
		if (Keys.Bindings.DOWN.getKey(input)) { 
			player.getShape().setY(player.getShape().getY() + player.getSpeed());
		}
		if (Keys.Bindings.LEFT.getKey(input)) { 
			player.getShape().setX(player.getShape().getX() - player.getSpeed());
		}
		if (Keys.Bindings.RIGHT.getKey(input)) {
			player.getShape().setX(player.getShape().getX() + player.getSpeed());
		}

	}

	private void holdPlayerInScreen(GameContainer gc) {
		// TODO: We really should define an area that isn't dependent on resolution.
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
	
	public void detectCollisions() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e1 = entities.get(i);
			for (int j = i; j < entities.size(); j++) {
				Entity e2 = entities.get(j);
				if (e1.getShape().intersects(e2.getShape())) {
					e1.onCollision(e2);
				}
			}
		}
	}
	
	public int getID(){
		return state;
	}
	
}

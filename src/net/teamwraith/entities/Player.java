package net.teamwraith.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends Entity {
	
	public static final float	NORMAL_SPEED = 0.6f, 
								SPRINT_SPEED = 0.8f;
	
	public Player() {
		try {
			this.setImage(new Image("res/textures/player.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.setSpeed(NORMAL_SPEED);
		this.setX(0);
		this.setY(0);
	}
	
}

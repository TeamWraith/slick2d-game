package net.teamwraith.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Player extends Entity {
	
	public static final float 	NORMAL_SPEED = 0.6f, 
								SPRINT_SPEED = 0.8f;
	
	public Player() {
		try {
			setImage(new Image("res/textures/player.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		setSpeed(NORMAL_SPEED);
		setShape(new Circle(
			getImage().getCenterOfRotationX(), 
			getImage().getCenterOfRotationY(), 
			getImage().getWidth()/2
		));
	}

	@Override
	public void onCollision(Entity entity) {
		if (entity instanceof Enemy) {
			// Die or lose a life. 
		}
	}
	
}

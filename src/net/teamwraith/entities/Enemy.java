package net.teamwraith.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends Entity {

	public Enemy() {
		try {
			setImage(new Image("res/textures/box.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		setShape(new Rectangle(50, 50, getImage().getWidth(), getImage().getHeight()));
	}
	
	@Override
	public void onCollision(Entity entity) {
		
	}

}

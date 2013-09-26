package net.teamwraith.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class Entity {
	
	private float timescale;
	private float speed;
	private Shape shape;
	private Image image;

	/**
	 * Specifies what happens to an entity after colliding with another entity.
	 * <p>
	 * In Play.java, have a method checking every entity in List entities for collisions after each frame. It will
	 * then call the entities' onCollision if one is detected. Efficient algorithm for this required.
	 */
	public abstract void onCollision(Entity entity);
	
	public float getTimeScale() {
		return timeScale;
	}
	
	public void setTimeScale(float timeScale) {
		this.timeScale = timeScale;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape hitbox) {
		this.shape = hitbox;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
}

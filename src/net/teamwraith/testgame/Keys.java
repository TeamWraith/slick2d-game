package net.teamwraith.testgame;

import org.newdawn.slick.Input;

/**
 * @author EternalFacepalm
 * 
 * Holds all handling of key pushes. Keybindings will later be stored in a config.
 */
public class Keys {

	/**
	 * A key. This is used by @link{Bindings}.
	 * 
	 * @param primary - the primary binding of the key.
	 * @param secondary - the secondary binding.
	 */
	static class Key {
		
		public Key (int primary, int secondary) {
			this.primary = primary;
			this.secondary = secondary;
		}
		
		public boolean isKeyDown (Input input) {
			return input.isKeyDown(primary) || input.isKeyDown(secondary);
		}
		
		public boolean isKeyTyped (Input input) {
				return input.isKeyPressed(primary) || input.isKeyPressed(secondary);
		}
		
		public void setPrimary(int primary) {
			this.primary = primary;
		}

		public void setSecondary(int secondary) {
			this.secondary = secondary;
		}

		private int primary;
		private int secondary;
	}
	
	// TODO: Perhaps make this an array later, with Bindings holding the keys' indices?
	private static Key up 		= 	new Key(Input.KEY_W, Input.KEY_UP);
	private static Key down 	= 	new Key(Input.KEY_S, Input.KEY_DOWN);
	private static Key left 	= 	new Key(Input.KEY_A, Input.KEY_LEFT);
	private static Key right 	=	new Key(Input.KEY_D, Input.KEY_RIGHT);
	private static Key sprint	=	new Key(Input.KEY_LSHIFT, Input.KEY_RSHIFT);
	private static Key select	=	new Key(Input.KEY_ENTER, Input.KEY_SPACE);
	
	public enum Bindings {
		UP 		(up),
		DOWN 	(down),
		LEFT 	(left),
		RIGHT 	(right),
		SPRINT 	(sprint),
		SELECT	(select);
		
		private Key key;
		
		Bindings(Key key) {
			this.key = key;
		}
		
		/**
		 * Returns whether the key is down or not.
		 * 
		 * @param input - input object is required.
		 */
		public boolean getKey(Input input) {
			return key.isKeyDown(input);
		}
		
		/**
		 * Returns true when the key is pressed once.
		 * 
		 * @param input - input object is required.
		 */
		public boolean getKeyTyped(Input input) {
			return key.isKeyTyped(input);
		}
	}

	public static void setUp(Key up) {
		Keys.up = up;
	}

	public static void setDown(Key down) {
		Keys.down = down;
	}

	public static void setLeft(Key left) {
		Keys.left = left;
	}

	public static void setRight(Key right) {
		Keys.right = right;
	}

	public static void setSprint(Key sprint) {
		Keys.sprint = sprint;
	}
	
	public static void setSelect(Key select) {
		Keys.select = select;
	}

}

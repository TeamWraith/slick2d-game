package net.teamwraith.testgame;

import org.newdawn.slick.Input;

/**
 * @author EternalFacepalm
 */
public class Keys {

	/**
	 * A key. All it does is hold two integers. It makes it cleaner.
	 * When a controller is enabled, the keys should change. Config
	 * for keybindings necessary.
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
		
		public int primary;
		public int secondary;
	}
	
	private static Key up 		= 	new Key(Input.KEY_W, Input.KEY_UP);
	private static Key down 	= 	new Key(Input.KEY_S, Input.KEY_DOWN);
	private static Key left 	= 	new Key(Input.KEY_A, Input.KEY_LEFT);
	private static Key right 	=	new Key(Input.KEY_D, Input.KEY_RIGHT);
	private static Key sprint	=	new Key(Input.KEY_LSHIFT, Input.KEY_RSHIFT);

	public enum Bindings {
		UP 		(up),
		DOWN 	(down),
		LEFT 	(left),
		RIGHT 	(right),
		SPRINT 	(sprint);
		
		private Key key;
		Bindings(Key key) {
			this.key = key;
		}
		
		public Key getKey() {
			return key;
		}
	}
	
	public static Key getUp() {
		return up;
	}

	public static Key getDown() {
		return down;
	}

	public static Key getLeft() {
		return left;
	}

	public static Key getRight() {
		return right;
	}

	public static Key getSprint() {
		return sprint;
	}
	
}

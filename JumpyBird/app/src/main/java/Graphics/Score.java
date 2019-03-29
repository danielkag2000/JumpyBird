package Graphics;

import Objects.Bird;
import Objects.Pipes;

public class Score {

	public static int points = 0;  // the player's points
	
	public Score() {
		
	}
	
	/**
	 * update the player's score
	 */
	public static void updateScore() {
		for (int i = 0; i < Game.vec.size(); i++) {
			if(Bird.bX >= (Game.vec.get(i).getX() + Pipes.width)  && !Game.vec.get(i).isChecked()) {
				Game.vec.get(i).setChecked(true);
				points++;
			}
		}
	}
	
}

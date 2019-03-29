package Graphics;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import Objects.*;

public class Game {

	public static double gravity = 2000; // the gravity of the world

	public static long inTime = System.nanoTime();  // the initial time of the game

	public static Bird br = new Bird();  // the player's bird

	public static Player thePlayer;

	public static boolean isFirst = false;  // is the player started the game
	
	public static boolean death = false;


	public static Vector<Pipes> vec = new Vector<Pipes>();

	public Game() {
		if(Game.isFirst) {
			Game.br.changeHeight();

			double t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - Game.inTime);

			t *= 0.001;

			if(t >= 3) {
				Game.inTime = System.nanoTime();
				Pipes.createPipes();
			}

			for (int i = 0; i < Game.vec.size(); i++) {
				Game.vec.get(i).setX();
			}

			Score.updateScore();
			this.removePipes();
			this.isDeath();
		}
	}


	/**
	 * remove the pipes which gone after the screen
	 */
	public void removePipes() {
		Pipes[] arr = new Pipes[Game.vec.size()];
		for (int i = 0; i < Game.vec.size(); i++) {
			if(!Game.vec.get(i).isGone())
				arr[i] = Game.vec.get(i);
			else
				arr[i] = null;
		}

		Game.vec.removeAllElements();
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != null)
				Game.vec.add(arr[i]);
		}
	}

	/**
	 * is the bird loses
	 */
	public void isDeath() {
		Pipes p;
		for (int i = 0; i < Game.vec.size(); i++) {
			p = Game.vec.get(i);
			if(!p.isChecked() 
					&& ((Bird.bX >= p.getX() && Bird.bX <= p.getX() + Pipes.width) || (Bird.bX + Bird.width >= p.getX() && Bird.bX + Bird.width <= p.getX() + Pipes.width) )
					&& ((Game.br.getbY() + Bird.height) >= p.getH2() || Game.br.getbY() <= p.getH1()))
			{
				Game.death = true;
				break;
			}	
		}
	}
	
}

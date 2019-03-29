package Objects;

import java.util.concurrent.TimeUnit;

import Graphics.Frame;
import Graphics.Game;

public class Pipes {

	private int x; // the x's start in the axis

	private int h1, h2; // the edge height of the up/down pipes

	public static final int width = 100; // the width of the pipes

	public static final int velocity = -150; // the velocity of the moving pipes

	private long inTime; // the initial time of running the pipes

	public static final int maxSpace = 450, minSpace = 400; // the range of space between the up and down pipes

	private boolean isGone = false;
	
	private boolean isChecked = false;

	public Pipes(int x, int h1, int h2) {

		this.inTime = System.nanoTime();

		this.x = x;
		this.h1 = h1;
		this.h2 = h2;
	}
	
	/**
	 * create new pipes and add it to the pipes vector
	 */
	public static void createPipes() {
		//int rnd1 = (int) (Math.random() * ((1000 - Pipes.maxSpace) - 50 + 1)) + 50;

		int rnd1 = (int) (Math.random() * ((Frame.height - Pipes.maxSpace) + 1));

		int rnd2 = rnd1 + (int) (Math.random() * (Pipes.maxSpace - Pipes.minSpace + 1) + Pipes.minSpace);

		Game.vec.add(new Pipes(1920, rnd1, rnd2));
	}
	
	/**
	 * moving the pipes on the screen
	 */
	public void setX() {
		double t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.inTime);

		this.inTime = System.nanoTime();
		t *= 0.001;

		// x = x0 + v*t
		this.x = (int) (this.x + (Pipes.velocity * t));

		if (this.x < -(Pipes.width * 2))
			this.isGone = true;

	}
	
	public int getX() {
		return x;
	}

	public int getH1() {
		return h1;
	}

	public int getH2() {
		return h2;
	}

	public boolean isGone() {
		return isGone;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	

}

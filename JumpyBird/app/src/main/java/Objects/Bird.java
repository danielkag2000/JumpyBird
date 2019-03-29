package Objects;

import java.util.concurrent.TimeUnit;

import Graphics.Frame;
import Graphics.Game;
import Graphics.Player;

public class Bird {

    public static final int yStart = 565; // the initial height of the bird

    protected int bY = Bird.yStart; // the height of the bird

    public static final int bX = 100; // the bird x in the axis

    protected long inTime; // the initial time of the bird

    protected double velocity = 0; // the velocity of the bird in the y axis (velocity <= 0)

    public static final double maxVelocity = -750; // the max velocity of the bird

    public static final int height = 66, width = 93; // the sizes of the bird

    public static boolean first = false; // first time to start the game

    public static int COLOR_BIRD = 0;

    public static int[] BIRD_ID;

    public Bird() {

    }

    /*
     * changing the height of the bird in the board
     */
    public void changeHeight() {
        // y = y0 + y0 * t + 1/2 * a * t^2

        double t = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.inTime); // time since the last jump

        this.inTime = System.nanoTime();
        t *= 0.001;

        if (!first) {
            t = 0;
            Bird.first = true;
        }

        this.bY = (int) (this.bY + (this.velocity * t) + (0.5 * Game.gravity * Math.pow(t, 2)));

        // v = v0 + a*t
        this.velocity = this.velocity + (Game.gravity * t);

        if (this.velocity < Bird.maxVelocity)
            this.velocity = Bird.maxVelocity;

        if (this.bY < 0) {
            this.bY = 0;
            //Game.death = true;
        }

        if (this.bY > Frame.height - Bird.height) {
            //System.out.println("true true true!!!!!!!!!!!!!!!!!!!!");
            this.bY = Frame.height - Bird.height;
            Game.death = true;
            //System.out.println("-------------------------\n\n\n" + Game.death + "\n\n\n");
        }
    }

    public int getbY() {
        return bY;
    }

    public void setbY(int bY) {
        this.bY = bY;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public static void changeColor() {
        Bird.COLOR_BIRD = (Bird.COLOR_BIRD + 1) % Game.thePlayer.colorsArr.length;
    }

    public static int getIdColor() {
        return Bird.BIRD_ID[Bird.COLOR_BIRD];
    }
}

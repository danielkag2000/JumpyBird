package Graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import com.example.daniel.jumpybird.MainGame;

import Objects.Bird;
import Objects.Pipes;

/**
 * Created by Daniel on 2/28/2018.
 */

public class Frame extends View implements View.OnTouchListener {

    public static int width = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static MainGame main;
    public static boolean END_GAME = false;

    public Frame(Context context, MainGame m) {
        super(context);
        this.main = m;
    }

    public void onDraw(Canvas c) {
        super.onDraw(c);
        paintComponents(c);

        new Game();

        this.setOnTouchListener(this);
    }

    /**
     * the paint function
     *
     * @param c the canvas to draw on
     */
    public void paintComponents(Canvas c) {
        paintBackground(c);
        drawBird(c);

        checkGameState();

            for (int i = 0; i < Game.vec.size(); i++) {
                Pipes p = Game.vec.get(i);

                drawDownerPipe(p.getX(), p.getH2(), c);
                drawUpperPipe(p.getX(), p.getH1(), c);
            }

            writeScore(c);
        invalidate();
    }

    /**
     * paint the background
     *
     * @param c the canvas to draw on
     */
    public void paintBackground(Canvas c) {
        //Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        //Bitmap help = Bitmap.createScaledBitmap(background, width, height, false);
        //c.drawBitmap(help, 0, 0, null);

        Paint p = new Paint();
        p.setColor(Color.rgb(0, 191, 255));
        c.drawRect(0, 0, width, height, p);
    }

    /**
     * paint the upper pipes
     *
     * @param c the canvas to draw on
     */
    public void drawUpperPipe(int x, int y, Canvas c) {
        /*Bitmap upPipe = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
        Bitmap help = Bitmap.createScaledBitmap(upPipe, 80 * width / 1080, y - (37 * height / 1920), false);
        c.drawBitmap(help, x + (10 * width / 1080), 0, null);

        help = Bitmap.createScaledBitmap(upPipe, 100 * width / 1080,  37 * height / 1920, false);
        c.drawBitmap(help, x, y - (37 * height / 1920), null);*/

        Paint p = new Paint();
        p.setColor(Color.rgb(0, 100, 0));
        c.drawRect(x, y - 30 * height / 1920, x + Pipes.width * width / 1080, y, p);  // the top of the pipe
        p.setColor(Color.rgb(0, 128, 0));
        c.drawRect(x + (Pipes.width / 10) * width / 1080, 0, x + ((Pipes.width / 10) * 9) * width / 1080, y - 30 * height / 1920, p);  // the pipe
    }

    /**
     * paint the down pipes
     *
     * @param c the canvas to draw on
     */
    public void drawDownerPipe(int x, int y, Canvas c) {
        /*Bitmap upPipe = BitmapFactory.decodeResource(getResources(), R.drawable.pipe);
        Bitmap help = Bitmap.createScaledBitmap(upPipe, 80 * width / 1080, height - y - (37 * height / 1920), false);
        c.drawBitmap(help, x + (10 * width / 1080), y + (37 * height / 1920), null);

        help = Bitmap.createScaledBitmap(upPipe, 100 * width / 1080, 37 * height / 1920, false);
        c.drawBitmap(help, x, y, null);*/

        Paint p = new Paint();
        p.setColor(Color.rgb(0, 100, 0));
        c.drawRect(x, y, x + Pipes.width * width / 1080, y + 30 * height / 1920, p);  // the top of the pipe
        p.setColor(Color.rgb(0, 128, 0));
        c.drawRect(x + ((Pipes.width / 10) * width / 1080), y + (30 * height / 1920), x + (Pipes.width / 10) * 9 * width / 1080, height, p);  // the pipe
    }

    /**
     * paint the bird
     *
     * @param c the canvas to draw on
     */
    public void drawBird(Canvas c) {
        Bitmap bird = BitmapFactory.decodeResource(getResources(), Bird.getIdColor());
        Bitmap help = Bitmap.createScaledBitmap(bird, Bird.width * width / 1080, Bird.height * height / 1920, false);
        c.drawBitmap(help, Bird.bX, Game.br.getbY(), null);
    }

    /**
     * write the score on the upper left corner
     * of the screen
     *
     * @param c the canvas to draw on
     */
    public void writeScore(Canvas c) {
        Paint p = new Paint();
        p.setTextSize(100);
        p.setColor(Color.BLACK);
        c.drawText("" + Score.points, 10, 100, p);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!Game.isFirst && !Bird.first)
            Game.isFirst = true;

        else if (!Bird.first)
            Bird.first = true;

        Game.br.setVelocity(Game.br.getVelocity() - 2500);

        this.checkGameState();

        return false;
    }

    private void checkGameState() {
        if (Game.death && !Frame.END_GAME) {
            //this.restartStats();
            Frame.END_GAME = true;
            Game.thePlayer.addScore(Score.points);
            Game.thePlayer.setLastScore(Score.points);
            this.main.finishGame();
        }
    }


    public void restartStats(){
        Game.death = false;
        Game.vec.removeAllElements();
        Game.br.setbY(Bird.yStart);
        Game.br.setVelocity(0);
        Bird.first = false;
        Game.isFirst = false;
        Frame.END_GAME = false;
        Score.points = 0;
    }
}

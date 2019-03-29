package com.example.daniel.jumpybird;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import Graphics.Frame;
import Graphics.Game;

/**
 * Created by Daniel on 2/28/2018.
 */

public class MainGame extends AppCompatActivity {

    LinearLayout frame;
    Frame f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screan);

        System.out.println(true);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        frame = (LinearLayout) findViewById(R.id.screen);
        f = new Frame(this, this);
        frame.addView(f);



    }

    @Override
    protected void onStop() {
        super.onStop();
        f.restartStats();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void finishGame(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Score", Game.thePlayer.getScore());
        editor.putString("Bird", Game.thePlayer.getBirds());
        editor.apply();

        super.onBackPressed();
        //finish();
        //startActivity(new Intent(this, AfterDethActivity.class));
    }

}

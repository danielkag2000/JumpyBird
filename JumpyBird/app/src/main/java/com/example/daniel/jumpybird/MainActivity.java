package com.example.daniel.jumpybird;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Graphics.Game;
import Graphics.Player;
import Objects.Bird;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv, stv;
    Button playButton, scoreButton;
    ImageView bird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String s = pref.getString("Score", "");
        String b = pref.getString("Bird", "");

        Game.thePlayer = new Player();

        Game.thePlayer.putScore(s);
        Game.thePlayer.putBirds(b);


        Bird.BIRD_ID = new int[Game.thePlayer.colorsArr.length];

        setValuesID(Bird.BIRD_ID);

        tv = (TextView) findViewById(R.id.text);
        stv = (TextView) findViewById(R.id.secondText);
        playButton = (Button) findViewById(R.id.playButton);

        tv.setText("Play With The Jumpy Bird");
        stv.setText("");

        playButton.setOnClickListener(this);

        bird = (ImageView) findViewById(R.id.bird);
        bird.setBackgroundResource(Bird.getIdColor());
        bird.setOnClickListener(this);

        scoreButton = (Button) findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int lastScore = Game.thePlayer.getLastScore();

        if(lastScore == -1 || lastScore == -2){
            tv.setText("Play With The Jumpy Bird");
            stv.setText("");
        } else if (lastScore <= 50) {
            tv.setText("Was It Too Hard for You?");
            stv.setText("Try Again :P");
        } else if (lastScore <= 100) {
            tv.setText("Not Too Shabby");
            stv.setText("But You Can Do Better ;D");
        } else if (lastScore <= 500) {
            tv.setText("WOW You Are Good At It");
            stv.setText("Let's See you Get Even Higher :D");
        } else {
            tv.setText("OK, You Don't Have Life Right?");
            stv.setText("Or It's Cheats HA? :I");
        }
    }

    //Game.thePlayer.resetPlayersStats();

    public void startGame() {
        startActivity(new Intent(this, MainGame.class));
    }

    public void goToScore() {
        startActivity(new Intent(this, ScoreActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view == playButton) {
            startGame();
        } else if(view == scoreButton){
            goToScore();
        } else if(view == bird){
            Bird.changeColor();
            bird.setBackgroundResource(Bird.getIdColor());
        }
    }

    private void setValuesID(int[] birdId) {
        birdId[0] = R.drawable.bird;
        birdId[1] = R.drawable.redbird;
        birdId[2] = R.drawable.azurebird;
        birdId[3] = R.drawable.blackbird;
    }
}
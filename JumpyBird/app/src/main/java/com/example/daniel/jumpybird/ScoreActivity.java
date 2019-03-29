package com.example.daniel.jumpybird;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import Graphics.Game;
import Objects.Bird;

public class ScoreActivity extends AppCompatActivity {

    private TextView tv, nt1;
    private Button reset, back;
    private ImageView fav;
    private LineGraphSeries<DataPoint> series;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tv = (TextView) findViewById(R.id.secondText);
        nt1 = (TextView) findViewById(R.id.noFav);
        fav = (ImageView) findViewById(R.id.favB);
        reset = (Button) findViewById(R.id.resScore);
        back = (Button) findViewById(R.id.backBut);
        fav = (ImageView) findViewById(R.id.favB);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.thePlayer.resetPlayersStats();
                resetData();
                backToMain();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMain();
            }
        });

        graph = (GraphView) findViewById(R.id.graph);
        graph.setTitle("Your Progress");

        graph.setBackgroundColor(Color.parseColor("#00BFFF"));
        graph.getGridLabelRenderer().setGridColor(Color.parseColor("#000000"));
        graph.getGridLabelRenderer().setVerticalLabelsColor(Color.parseColor("#FFFFFF"));
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.parseColor("#FFFFFF"));

        if (Game.thePlayer.getLastScore() == -1){
            graph.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);

            fav.setVisibility(View.INVISIBLE);

            tv.setVisibility(View.VISIBLE);
            nt1.setVisibility(View.VISIBLE);

        } else {
            createGraph();
            graph.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);

            fav.setVisibility(View.VISIBLE);
            fav.setBackgroundResource(Bird.BIRD_ID[Game.thePlayer.mostUseBird()]);

            tv.setVisibility(View.INVISIBLE);
            nt1.setVisibility(View.INVISIBLE);
        }
    }

    private void resetData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Score", "");
        editor.putString("Bird", "");
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Game.thePlayer.getLastScore() == -1){
            graph.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);

            fav.setVisibility(View.INVISIBLE);

            tv.setVisibility(View.VISIBLE);
            nt1.setVisibility(View.VISIBLE);

        } else {
            createGraph();
            graph.setVisibility(View.VISIBLE);
            reset.setVisibility(View.VISIBLE);

            fav.setVisibility(View.VISIBLE);
            fav.setBackgroundResource(Bird.BIRD_ID[Game.thePlayer.mostUseBird()]);

            tv.setVisibility(View.INVISIBLE);
            nt1.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void backToMain() {
        super.onBackPressed();
    }

    private void createGraph() {

        // set manual X bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(Game.thePlayer.getMaxScore() + 1);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(Game.thePlayer.getNumOfPoints());

        DataPoint[] dataArr = toArrayData();
        series = new LineGraphSeries<>(dataArr);
        series.setColor(Color.parseColor("#0431ea"));
        graph.addSeries(series);
        series.setDrawDataPoints(true);  //Draw points
    }

    private DataPoint[] toArrayData() {
        DataPoint[] dataArr = new DataPoint[Game.thePlayer.getNumOfPoints()];

        for (int i = 0; i < dataArr.length; i++){
            dataArr[i] = new DataPoint(i + 1, Game.thePlayer.getIscore(i));
        }

        return dataArr;
    }
}

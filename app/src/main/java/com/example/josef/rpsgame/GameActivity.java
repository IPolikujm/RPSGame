package com.example.josef.rpsgame;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameActivity extends Activity {

    Canvas canvas;
    RPSGameView rpsGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        //setContentView(R.layout.activity_game);
        canvas = new Canvas();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        rpsGameView = new RPSGameView(this,displayMetrics.heightPixels,displayMetrics.widthPixels);

        setContentView(rpsGameView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();

        float touchY = event.getY();
        rpsGameView.Draw(canvas, touchX, touchY);
        Log.println(Log.INFO,"hello","x: " + touchX + " y: "+ touchY);
        Toast.makeText(getApplicationContext(),"Clicked: x " + touchX + " y " + touchY,Toast.LENGTH_SHORT);
        setContentView(rpsGameView);
        return super.onTouchEvent(event);
    }


}

package com.example.josef.rpsgame;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GameActivity extends Activity {

    boolean gameIsON = false;
    Canvas canvas;
    RPSGameView rpsGameView;
    GameController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setBackgroundColor(Color.RED);
        setContentView(R.layout.activity_game);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        controller = new GameController(this, displayMetrics.heightPixels,displayMetrics.widthPixels);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gameIsON) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                float touchX = event.getX();
                float touchY = event.getY();
                //rpsGameView.Draw(canvas, (int) touchX, (int) touchY);
                Point2D touchPoint = new Point2D((int) touchX, (int) touchY);
                RPSGameView rps = controller.onUserAction(touchPoint,rpsGameView);
                if (rps != null) {
                    rpsGameView = rps;
                }
                Log.println(Log.INFO, "aijdoga", "POsition: " + touchX + " y: " + touchY);
                Toast.makeText(getApplicationContext(), "Clicked: x " + touchX + " y " + touchY, Toast.LENGTH_SHORT);
                if (rpsGameView != null) {
                    setContentView(rpsGameView);
                }
                return super.onTouchEvent(event);
            } else {
                return super.onTouchEvent(event);
            }
        }
        return super.onTouchEvent(event);
    }

    public void onNewGameButtonClick(View view){

        canvas = new Canvas();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        controller.SetPLayer1Units();
        rpsGameView = new RPSGameView(this,displayMetrics.heightPixels,displayMetrics.widthPixels,canvas, controller.chessBoard);
        gameIsON = true;
        setContentView(rpsGameView);


    }


}

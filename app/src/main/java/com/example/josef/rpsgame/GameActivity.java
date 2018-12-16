package com.example.josef.rpsgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.security.spec.ECField;

public class GameActivity extends Activity {

    boolean gameIsON = false;
    Canvas canvas;
    RPSGameView rpsGameView;
    RPSGameView rpsGameView2;
    GameController controller;
    GameController controllerPlayer2;
    int currentPlayer = 1;
    int nextPLayerN = 2;
    Handler handler;
    Intent helpAcitities;
    boolean isWaiting;
    boolean changePlayerDueDraw = false;
    float drawCousedClick_X;
    float drawCousedClick_Y;
    boolean iWasHereP1 = false;

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
        Intent intent = getIntent();
        int leftColor = intent.getIntExtra("leftColor", 1);
        int rightColor = intent.getIntExtra("rightColor", 1);


        controller = new GameController(getApplicationContext(), displayMetrics.heightPixels,displayMetrics.widthPixels);
        controller.player1.SetColor(leftColor);
        controllerPlayer2 = new GameController(getApplicationContext(), displayMetrics.heightPixels,displayMetrics.widthPixels);
        controllerPlayer2.onTurnPLayer = controllerPlayer2.player2;
        controllerPlayer2.player2.SetColor(rightColor);
        controllerPlayer2.chessBoard = controller.chessBoard;
        handler = new Handler();
        isWaiting = false;


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gameIsON) {
            if (!isWaiting) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    float touchX = event.getX();
                    float touchY = event.getY();
                    //rpsGameView.Draw(canvas, (int) touchX, (int) touchY);
                    Point2D touchPoint = new Point2D((int) touchX, (int) touchY);
                    RPSGameView rps = null;



                    if (currentPlayer == 1){
                        rps = controller.onUserAction(touchPoint, rpsGameView);
                    }else{
                        rps = controller.onUserAction(touchPoint, rpsGameView2);
                    }


                    if (rps != null) {
                        if (currentPlayer == 1){
                            if (controller.fightResult == "Draw"){
                                if(iWasHereP1){
                                    rpsGameView.DrawDraw();
                                    rpsGameView2.DrawDraw();
                                    changePlayerDueDraw = true;
                                    iWasHereP1 = false;
                                }else{
                                    drawCousedClick_X = touchX;
                                    drawCousedClick_Y = touchY;
                                    iWasHereP1 = true;
                                    //rps.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN, touchX, touchY, 0));
                                    return super.onTouchEvent(event);
                                }



                            }
                        }
                        if (currentPlayer == 2){
                            if (controller.fightResult == "Draw"){
                                if(iWasHereP1){
                                    rpsGameView.DrawDraw();
                                    rpsGameView2.DrawDraw();
                                    changePlayerDueDraw = true;
                                    iWasHereP1 = false;
                                }else{
                                    drawCousedClick_X = touchX;
                                    drawCousedClick_Y = touchY;
                                    iWasHereP1 = true;
                                    return super.onTouchEvent(event);
                                }
                                //changePlayerDueDraw = true;
                                //rps.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN, touchX, touchY, 0));

                            }
                        }
                        if (controller.fightResult == "unknown") {
                            rpsGameView.DrawDrawDone();
                            rpsGameView2.DrawDrawDone();
                            changePlayerDueDraw = false;
                            rps.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_DOWN, drawCousedClick_X, drawCousedClick_Y, 0));
                        }

                        if (currentPlayer == 1){
                            currentPlayer = 2;
                        }else{
                            currentPlayer = 1;
                        }
                        isWaiting = true;
                        //rpsGameView = rps;
                        setContentView(rps);
                        if(controller.fightResult == "Win"){
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ta_da);
                            mediaPlayer.start();
                            try{
                                Thread.sleep(500);
                            }catch (Exception e){

                            }
                        }
                        if(controller.fightResult == "Defeat"){
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.nope);
                            mediaPlayer.start();
                            try{
                                Thread.sleep(300);
                            }catch (Exception e){

                            }
                        }

                        if (!controller.endGame) {
                            helpAcitities = new Intent(this, NextPlayer.class);
                            helpAcitities.putExtra("np", nextPLayerN);
                            if (nextPLayerN == 2) {
                                nextPLayerN = 1;
                            } else {
                                nextPLayerN = 2;
                            }

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isWaiting = false;
                                    startActivity(helpAcitities);
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            isWaiting = false;
                                            if (currentPlayer == 1){
                                                setContentView(rpsGameView);
                                            }else{
                                                setContentView(rpsGameView2);
                                            }

                                        }
                                    }, 250);
                                }
                            }, 1300);


                        }else{
                            helpAcitities = new Intent(this, PlayerWinActivity.class);
                            helpAcitities.putExtra("np", nextPLayerN);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    isWaiting = false;
                                    startActivity(helpAcitities);
                                    finish();
                                }
                            }, 400);

                        }

                    }
                    Log.println(Log.INFO, "aijdoga", "POsition: " + touchX + " y: " + touchY);
                    Toast.makeText(getApplicationContext(), "Clicked: x " + touchX + " y " + touchY, Toast.LENGTH_SHORT);
               /* if (rpsGameView != null) {
                    setContentView(rpsGameView);
                    Intent nextPlayer = new Intent(this, NextPlayer.class);
                    nextPlayer.putExtra("np",nextPLayerN);
                    if (nextPLayerN == 2){
                        nextPLayerN = 1;
                    }
                    else{
                        nextPLayerN = 2;
                    }
                    startActivity(nextPlayer);
                }*/
                    return super.onTouchEvent(event);
                } else {
                    return super.onTouchEvent(event);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void onNewGameButtonClick(View view){

        canvas = new Canvas();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        controller.SetPLayer1Units();
        rpsGameView = new RPSGameView(getApplicationContext(),displayMetrics.heightPixels,displayMetrics.widthPixels,canvas, controller.chessBoard, controller.player1, controllerPlayer2.player2.GetColor());
        rpsGameView.RPSImages = controller.RPSImages;
        rpsGameView2 = new RPSGameView(getApplicationContext(),displayMetrics.heightPixels,displayMetrics.widthPixels,canvas, controllerPlayer2.chessBoard,controllerPlayer2.player2, controller.player1.GetColor());
        rpsGameView2.RPSImages = controller.RPSImages;
        gameIsON = true;
        setContentView(rpsGameView);


    }



}

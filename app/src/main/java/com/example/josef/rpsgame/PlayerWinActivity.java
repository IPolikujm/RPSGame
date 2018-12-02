package com.example.josef.rpsgame;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PlayerWinActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player_win);

        textView = findViewById(R.id.playerWinsTextView);
        int s = getIntent().getIntExtra("np",0);
        if (s == 1){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
            textView.setText("Red Wins!");
        }else{
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            textView.setText("Blue Wins!");
        }
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.happy_birthday);
        mediaPlayer.start(); //

        //setContentView(R.layout.activity_player_win);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}

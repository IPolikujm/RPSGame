package com.example.josef.rpsgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class NextPlayer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        int s = getIntent().getIntExtra("np",0);
        if (s == 1){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }else{
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        setContentView(R.layout.activity_next_player);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }
}

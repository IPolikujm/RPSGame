package com.example.josef.rpsgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;


public class MainActivity extends Activity {

    Button ExitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);
        ExitButton = findViewById(R.id.ExitButton);
        ExitButton.setBackgroundColor(Color.YELLOW);
    }

    public void OnExitButtonClick(View view){
        this.finishAndRemoveTask();

    }

    public void OnPlayButtonClick(View view){
        Intent gameActivity = new Intent(this, GameActivity.class);
        startActivity(gameActivity);
    }

}

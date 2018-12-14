package com.example.josef.rpsgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;


public class MainActivity extends Activity {

    Button ExitButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int leftColor;
    int rightColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getActionBar().hide();
        setContentView(R.layout.activity_main);
        ExitButton = findViewById(R.id.ExitButton);
        ExitButton.setBackgroundColor(Color.YELLOW);
        leftColor = sharedPreferences.getInt("leftColor", 1);
        rightColor = sharedPreferences.getInt("rightColor", 1);

    }

    public void OnExitButtonClick(View view){
        this.finishAndRemoveTask();

    }

    public void OnPlayButtonClick(View view){
        Intent gameActivity = new Intent(this, GameActivity.class);
        gameActivity.putExtra("leftColor", leftColor);
        gameActivity.putExtra("rightColor", rightColor);
        startActivity(gameActivity);
    }

    public void OnSettingsButtonClick(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivityForResult(intent, 112);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            return;
        }

        if (requestCode == 112){
            int leftColor = data.getIntExtra("leftColor", 1);
            int rightColor = data.getIntExtra("rightColor", 1);

            switch (leftColor){
                case 1:
                    editor = sharedPreferences.edit();
                    editor.putInt("leftColor", 1);
                    editor.apply();
                    this.leftColor = 1;
                    break;
                case 2:
                    editor = sharedPreferences.edit();
                    editor.putInt("leftColor", 2);
                    editor.apply();
                    this.leftColor = 2;
                    break;
            }

            switch (rightColor){
                case 1:
                    editor = sharedPreferences.edit();
                    editor.putInt("rightColor", 1);
                    editor.apply();
                    this.rightColor = 1;
                    break;
                case 2:
                    editor = sharedPreferences.edit();
                    editor.putInt("rightColor", 2);
                    editor.apply();
                    this.rightColor = 2;
                    break;
            }




        }
    }
}

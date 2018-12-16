package com.example.josef.rpsgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Settings extends Activity {


    Button sendingButton;

        ImageView blueImageView;
    RelativeLayout blueLayout;
    RelativeLayout redLayout;
    RelativeLayout yellowLayout;
    RelativeLayout greenLayout;
    private boolean somethingSelected;
    private boolean somethingRightSelected;
    private boolean blueSelected;
    private boolean redSelected;
    private boolean yellowSelected;
    private boolean greenSelected;
    int color;
    int rightColor;
    int leftColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getWindow().getDecorView().findViewById(android.R.id.content);
        view.setBackgroundColor(Color.WHITE);
        setContentView(R.layout.activity_settings);
        blueImageView = findViewById(R.id.blueImageView);
        sendingButton = findViewById(R.id.button);
        blueLayout = findViewById(R.id.blueRelativeBorder);
        redLayout = findViewById(R.id.redRelativeBorder);
        yellowLayout = findViewById(R.id.yellowRelativeBorder);
        greenLayout = findViewById(R.id.greenRelativeBorder);
        color = 0;
        somethingRightSelected = false;
        rightColor = 0;
        leftColor = 0;

    }

    public void myClick2(View view) {
        Intent intent = new Intent();
        intent.putExtra("color",color);
        intent.putExtra("rightColor", rightColor);
        intent.putExtra("leftColor", leftColor);
        setResult(-1, intent);
        finish();

    }

    public void blueOnClick(View view){
        if (!somethingSelected){
            highlightBlue();
        }else{
            if(!blueSelected){
                clearSomethingClick();
                highlightBlue();
            }
            else{
                clearSomethingClick();
            }
        }
    }

    public void redOnClick(View view){
        if (!somethingRightSelected){
            highLightRed();
        }else {
            if (!redSelected) {
                clearRoightSomethingClick();
                highLightRed();
            }else{
                clearRoightSomethingClick();
            }
        }
    }

    public void yellowOnClick(View view){
        if (!somethingRightSelected){
            highlightYellow();
        }else {
            if (!yellowSelected) {
                clearRoightSomethingClick();
                highlightYellow();
            }else{
                clearRoightSomethingClick();
            }
        }
    }

    public void greenOnClick(View view){
        if (!somethingSelected){
            highlightGreen();
        }else {
            if (!greenSelected) {
                clearSomethingClick();
                highlightGreen();
            }else{
                clearSomethingClick();
            }
        }
    }

    private void clearSomethingClick(){
        if (somethingSelected){
            if (blueSelected){
                setBlueLayoutWhite();
                blueSelected = false;
                somethingSelected = false;
                color = 0;
                return;
            }

            if(greenSelected){
                setGreenLayoutWhite();
                greenSelected = false;
                somethingSelected = false;
                color = 0;
                return;
            }
        }
    }

    private void clearRoightSomethingClick(){
        if (somethingRightSelected){
            if(yellowSelected){
                setYellowLayoutWhite();
                yellowSelected = false;
                somethingSelected = false;
                color = 0;
                return;
            }

            if (redSelected){
                setRedLayoutWhite();
                redSelected = false;
                somethingSelected = false;
                color = 0;
                return;
            }
        }
    }

    private void setBlueLayoutWhite(){
        blueLayout.setBackgroundColor(Color.WHITE);
    }

    private void setRedLayoutWhite(){
        redLayout.setBackgroundColor(Color.WHITE);
    }

    private void setYellowLayoutWhite(){
        yellowLayout.setBackgroundColor(Color.WHITE);
    }

    private void setGreenLayoutWhite(){
        greenLayout.setBackgroundColor(Color.WHITE);
    };

    private void highLightRed(){
        redLayout.setBackgroundColor(Color.GREEN);
        redSelected = true;
        somethingRightSelected = true;
        color = 2;
        rightColor = 1;
    }

    private void highlightBlue(){
        blueLayout.setBackgroundColor(Color.GREEN);
        blueSelected = true;
        somethingSelected = true;
        color = 1;
        leftColor = 1;
    }

    private void highlightYellow(){
        yellowLayout.setBackgroundColor(Color.GREEN);
        yellowSelected = true;
        somethingRightSelected = true;
        color = 3;
        rightColor = 2;
    }

    private void highlightGreen(){
        greenLayout.setBackgroundColor(Color.GREEN);
        greenSelected = true;
        somethingSelected = true;
        color = 4;
        leftColor = 2;

    }

    private void highlightColor(RelativeLayout layout, int c){
        layout.setBackgroundColor(Color.GREEN);
        switch (c){
            case 1:
                blueSelected = true;
                color = 1;
                break;
        }
        somethingSelected = true;

    }
}

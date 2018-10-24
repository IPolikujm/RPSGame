package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import java.io.*;

public class RPSGameView extends View   {

    Context context;
    PlayerUnit pokus;
    Bitmap backgroud;
    double width; //Sirka obrazovky / 54 je pocateni souradnice obrazku (pri spravnem pohledu -90° - vlevo dole)
    double height; //11*h/48

    public RPSGameView(Context context,double height,double width) {
        super(context);
        this.context = context;
        pokus = new PlayerUnit(context);
        setUpBackgroud();
        this.width = width;
        this.height = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground( new BitmapDrawable(getResources(), backgroud));
        //setBackgroundColor(Color.WHITE);
        pokus.getResources().setBounds(1,1,1,1);
        canvas.drawBitmap(Bitmap.createScaledBitmap(pokus.GetBitmap(),64,64,true), (int)(width/54),(int)((height/48)*11),null);
        invalidate();

        //pokus.getResources().draw(canvas);
    }

    public void Draw(Canvas canvas, double x, double y){
        width = x;
        height = y;
        canvas.drawBitmap(Bitmap.createScaledBitmap(pokus.GetBitmap(),64,64,true), (int)(x),(int)(y),null);
    }

    public boolean setUpBackgroud() {
        try{
            backgroud = BitmapFactory.decodeResource(context.getResources(),R.drawable.sachovnicev02_90);
            return true;
        }catch(Exception e) {
            throw e;
        }
    }

}

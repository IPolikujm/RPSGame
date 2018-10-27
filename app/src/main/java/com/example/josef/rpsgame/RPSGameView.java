package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import java.io.*;
import java.util.ArrayList;


public class RPSGameView extends View   {

    Canvas canvas;
    Context context;
    PlayerUnit pokus;
    Bitmap backgroud;
    double width; //Sirka obrazovky / 54 je pocateni souradnice obrazku (pri spravnem pohledu -90° - vlevo dole)
    double height; //11*h/48
    ArrayList<PlayerUnit> list;
    ChessboardSquare[][] chessboardSquares;

    public RPSGameView(Context context,double height,double width) {
        super(context);
        this.context = context;
        pokus = new PlayerUnit(context,4,4);
        setUpBackgroud();
        this.width = width;
        this.height = height;

    }

    public RPSGameView(Context context, double height, double width, Canvas canvas,ChessboardSquare[][] chessboardSquares) {
        super(context);
        this.context = context;
        //pokus = new PlayerUnit(context,1,1);
        //list.add(pokus);
        setUpBackgroud();
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.chessboardSquares = chessboardSquares;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground( new BitmapDrawable(getResources(), backgroud));
        //setBackgroundColor(Color.WHITE);
        //pokus.getResources().setBounds(1,1,1,1);

        /*
        for (PlayerUnit u:list
             ) {
            canvas.drawBitmap(Bitmap.createScaledBitmap(u.GetBitmap(),64,64,true), (int)((width/54)+((width/54)*52/8*(u.column-1))),(int)((height/48)*11 + (height/48)*26/8*(u.row)),null);

        }
        */
        //invalidate();

        //pokus.getResources().draw(canvas);
        for (int i = 0; i < 8; i ++) {
            for (ChessboardSquare square : chessboardSquares[i]) {
                if (square.unit != null) {
                    canvas.drawBitmap(Bitmap.createScaledBitmap(square.unit.UnitImage, 64, 64, true), square.imageLeftUpperPoint.x, square.imageLeftUpperPoint.y, null);
                }
            }
        }

    }

    public void Draw(Canvas canvas, double x, double y){
        super.draw(canvas);
        setBackground( new BitmapDrawable(getResources(), backgroud));
        width = x;
        height = y;
        //canvas.drawBitmap(Bitmap.createScaledBitmap(list.get(1).GetBitmap(),64,64,true), (int)(x),(int)(y),null);
    }

    public void Draw(ChessboardSquare[][] chessboardSquares){
        super.draw(canvas);
        setBackground( new BitmapDrawable(getResources(), backgroud));
        for (int i = 0; i < 8; i ++) {
            for (ChessboardSquare square : chessboardSquares[i]) {
                if (square.unit != null) {
                    canvas.drawBitmap(Bitmap.createScaledBitmap(square.unit.UnitImage, 64, 64, true), square.leftUpperPoint.x, square.leftUpperPoint.y, null);
                }
            }
        }
    }

    public boolean setUpBackgroud() {
        try{
            backgroud = BitmapFactory.decodeResource(context.getResources(),R.drawable.sachovnicev02_90);
            return true;
        }catch(Exception e) {
            throw e;
        }
    }

    public void helpmedebug(Canvas canvas){
        super.draw(canvas);
        setBackground( new BitmapDrawable(getResources(), backgroud));
        canvas.drawBitmap(Bitmap.createScaledBitmap(pokus.UnitImage,64,64,true),200,200,null);
    }

}

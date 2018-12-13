package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RPSGameView extends View   {

    private Player PlayerID;
    Canvas canvas;
    Context context;
    PlayerUnit pokus;
    Bitmap backgroud;
    double width; //Sirka obrazovky / 54 je pocateni souradnice obrazku (pri spravnem pohledu -90° - vlevo dole)
    double height; //11*h/48
    ArrayList<PlayerUnit> list;
    ChessboardSquare[][] chessboardSquares;
    boolean somethingmore = false;
    Point2D movementVector;
    PlayerUnit moveFrom;
    ChessboardSquare moveTo;
    Boolean somethingToMove = false;
    int MovementMultiplier = 2;
    Bitmap InvisUnit;
    Boolean Draw = false;
    ClickableImage[] RPSImages;


    public RPSGameView(Context context,double height,double width) {
        super(context);
        this.context = context;
        //pokus = new PlayerUnit(context,4,4);
        setUpBackgroud();
        this.width = width;
        this.height = height;

    }

    public RPSGameView(Context context, double height, double width, Canvas canvas,ChessboardSquare[][] chessboardSquares, Player player, int enemyqmColor) {
        super(context);
        setWillNotDraw(false);
        this.context = context;
        //pokus = new PlayerUnit(context,1,1);
        //list.add(pokus);
        setUpBackgroud();
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.chessboardSquares = chessboardSquares;
        this.PlayerID = player;
        switch (player.ID){
            case 1:
                InvisUnit = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark90n_red);
                break;
            case 2:
                if(enemyqmColor == 1)
                    InvisUnit = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark90p_blue);
                if(enemyqmColor == 2)
                    InvisUnit = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark90p_green);

                break;
                default:
                    break;
        }

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
                if(square.MoveFrom){
                    Paint paint = new Paint();
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(1);
                    canvas.drawRect(square.leftUpperPoint.x, square.leftUpperPoint.y, square.rightBottomPoint.x,square.rightBottomPoint.y, paint);
                    paint.setColor(Color.GREEN);
                    paint.setStrokeWidth(0);
                    canvas.drawRect(square.leftUpperPoint.x+1, square.leftUpperPoint.y+1, square.rightBottomPoint.x+1, square.rightBottomPoint.y+1, paint);
                }

                if(square.MoveTo){
                    Paint paint = new Paint();
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(1);
                    canvas.drawRect(square.unit.leftUpper.x, square.unit.leftUpper.y, square.unit.leftUpper.x + square.unit.image_x,square.unit.leftUpper.y + square.unit.image_y, paint);
                    paint.setColor(Color.MAGENTA);
                    paint.setStrokeWidth(0);
                    canvas.drawRect(square.leftUpperPoint.x+1, square.leftUpperPoint.y+1, square.rightBottomPoint.x+1, square.rightBottomPoint.y+1, paint);
                }


                if (square.unit != null) {
                    if (square.unit.player.ID != this.PlayerID.ID){
                        if (!square.unit.isVisible()){
                            canvas.drawBitmap(Bitmap.createScaledBitmap(InvisUnit, 64, 64, true), square.unit.leftUpper.x, square.unit.leftUpper.y, null);
                        }else{
                            canvas.drawBitmap(Bitmap.createScaledBitmap(square.unit.UnitImage, 64, 64, true), square.unit.leftUpper.x, square.unit.leftUpper.y, null);
                        }
                    }else {
                        canvas.drawBitmap(Bitmap.createScaledBitmap(square.unit.UnitImage, 64, 64, true), square.unit.leftUpper.x, square.unit.leftUpper.y, null);
                    }
                }
            }
        }

        if(somethingmore){
            somethingmore = false;
        }else {
            somethingmore = true;
        }

        if(somethingmore){
            Bitmap dice = BitmapFactory.decodeResource(context.getResources(),R.drawable.firework);
            canvas.drawBitmap(Bitmap.createScaledBitmap(dice, 100, 100, true), 0, 0, null);
        }
        if (somethingToMove){
           // moveUnit();

        }

        if(Draw){
            for (ClickableImage CI : RPSImages) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(CI.image, 128, 128, true), CI.LeftUpperPoint.x, 75, null);
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
        somethingmore = false;
        setBackground( new BitmapDrawable(getResources(), backgroud));
        for (int i = 0; i < 8; i ++) {
            for (ChessboardSquare square : chessboardSquares[i]) {
                if (square.unit != null) {
                    canvas.drawBitmap(Bitmap.createScaledBitmap(square.unit.UnitImage, 64, 64, true), square.leftUpperPoint.x, square.leftUpperPoint.y, null);
                }
            }
        }
    }

    public boolean Draw(ChessboardSquare origin, ChessboardSquare newOne){
         movementVector = new Point2D();

        if ((newOne.imageLeftUpperPoint.x - origin.unit.leftUpper.x) > 0){
            movementVector.x = 1;
        }
        if ((newOne.imageLeftUpperPoint.x - origin.unit.leftUpper.x) < 0){
            movementVector.x = -1;
        }
        if ((newOne.imageLeftUpperPoint.x - origin.unit.leftUpper.x) == 0){
            movementVector.x = 0;
        }
        if ((newOne.imageLeftUpperPoint.y - origin.unit.leftUpper.y) > 0){
            movementVector.y = 1;
        }
        if ((newOne.imageLeftUpperPoint.y - origin.unit.leftUpper.y) < 0){
            movementVector.y = -1;
        }
        if ((newOne.imageLeftUpperPoint.y - origin.unit.leftUpper.y) == 0){
            movementVector.y = 0;
        }

        movementVector.x *= MovementMultiplier;
        movementVector.y *= MovementMultiplier;
        moveTo = new ChessboardSquare();
        moveFrom = origin.unit;
        moveTo.imageLeftUpperPoint.x = newOne.imageLeftUpperPoint.x;
        moveTo.imageLeftUpperPoint.y = newOne.imageLeftUpperPoint.y;
        somethingToMove = true;
        return true;
    }

    public void moveUnit(){
        if(movementVector != null) {
            if (moveFrom.leftUpper.x != moveTo.imageLeftUpperPoint.x || moveFrom.leftUpper.y != moveTo.imageLeftUpperPoint.y) {

                moveFrom.leftUpper.x += movementVector.x;
                moveFrom.leftUpper.y += movementVector.y;

            } else {
                somethingToMove = false;
                movementVector = null;
            }
            invalidate();
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

    public void DrawDraw(){
//      super.draw(canvas);
        Draw = true;
    }


    public void Draw(){
        invalidate();
    }


    public void DrawDrawDone() {
        Draw = false;
    }
}

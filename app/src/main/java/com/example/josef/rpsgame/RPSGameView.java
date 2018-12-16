package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import java.util.ArrayList;


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
    Bitmap InvisUnit;
    Boolean Draw = false;
    ClickableImage[] RPSImages;


    public RPSGameView(Context context,double height,double width) {
        super(context);
        this.context = context;
        setUpBackgroud();
        this.width = width;
        this.height = height;

    }

    public RPSGameView(Context context, double height, double width, Canvas canvas,ChessboardSquare[][] chessboardSquares, Player player, int enemyqmColor) {
        super(context);
        setWillNotDraw(false);
        this.context = context;

        setUpBackgroud();
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.chessboardSquares = chessboardSquares;
        this.PlayerID = player;
        switch (player.ID){
            case 1:
                if(enemyqmColor == 1)
                    InvisUnit = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark90n_red);
                if(enemyqmColor == 2)
                    InvisUnit = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark90n_orange);
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

        for (int i = 0; i < 8; i ++) {
            for (ChessboardSquare square : chessboardSquares[i]) {
                if(square.MoveFrom){
                    Paint paint = new Paint();
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(1);
                    canvas.drawRect(square.leftUpperPoint.x, square.leftUpperPoint.y, square.rightBottomPoint.x,square.rightBottomPoint.y, paint);
                    paint.setColor(Color.LTGRAY);
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
        if(Draw){
            for (ClickableImage CI : RPSImages) {
                canvas.drawBitmap(Bitmap.createScaledBitmap(CI.image, 128, 128, true), CI.LeftUpperPoint.x, 75, null);
            }
        }

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

    public void DrawDraw(){
        Draw = true;
    }

    public void DrawDrawDone() {
        Draw = false;
    }
}

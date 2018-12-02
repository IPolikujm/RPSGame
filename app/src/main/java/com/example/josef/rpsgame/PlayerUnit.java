package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class PlayerUnit {
    Boolean IsAlive;
    Bitmap UnitImage;
    Player player;
    int image_x, image_y;
    int row, column;
    Context context;
    boolean canMove;
    private boolean OtherPlayerVisible = false;
    Point2D leftUpper;

    public PlayerUnit(Context context, int r, int c, Player player, int x, int y) {
        IsAlive = true;
        image_x = 64;
        image_y = 64;
        //UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.scissors90p_blue);
        //UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.stone90);
        row = c;
        column = r;
        this.player = player;
        leftUpper = new Point2D(x,y);
    }
    public PlayerUnit(){
        UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.flag90n_black);
    }


    public void checkAlive(){
        if (!IsAlive){

        }
    }
/*
    public Drawable getResources(){
        return d;
    }
*/
    public Bitmap GetBitmap(){
        return UnitImage;
    }

    public void SetUnitVisible(){
        OtherPlayerVisible = true;
    }
    public boolean isVisible(){
        return OtherPlayerVisible;
    }

}

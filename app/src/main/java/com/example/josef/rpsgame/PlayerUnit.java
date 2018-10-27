package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class PlayerUnit {
    Boolean IsAlive;
    Bitmap UnitImage;

    int image_x, image_y;
    int row, column;
    Context context;

    public PlayerUnit(Context context, int r, int c) {
        IsAlive = true;
        image_x = 64;
        image_y = 64;
        UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.scissors90p_blue);
        //UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.stone90);
        row = c;
        column = r;
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


}

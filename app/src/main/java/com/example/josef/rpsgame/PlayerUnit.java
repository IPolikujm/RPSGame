package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class PlayerUnit {
    Boolean IsAlive;
    Drawable d;
    Bitmap UnitImage;
    int image_x, image_y;
    Context context;

    public PlayerUnit(Context context) {
        IsAlive = true;
        d = context.getResources().getDrawable(R.drawable.scissors_256_256);
        image_x = 64;
        image_y = 64;
        UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.scissors_256_256_r90_blue);
        //UnitImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.stone90);

    }

    public void checkAlive(){
        if (!IsAlive){

        }
    }

    public Drawable getResources(){
        return d;
    }

    public Bitmap GetBitmap(){
        return UnitImage;
    }


}

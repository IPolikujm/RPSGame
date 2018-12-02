package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ClickableImage {

    Point2D LeftUpperPoint;
    Bitmap image;

    public ClickableImage() {
        LeftUpperPoint = new Point2D();

    }

    public ClickableImage(Context context, int x, int y, String object){
        LeftUpperPoint = new Point2D(x,y);
        switch (object){
            case "Rock":
                image = BitmapFactory.decodeResource(context.getResources(),R.drawable.stone_black);
                break;
            case "Paper":
                image = BitmapFactory.decodeResource(context.getResources(),R.drawable.paper);
                break;
            case "Scissors":
                image = BitmapFactory.decodeResource(context.getResources(),R.drawable.scissors_256_256);
                break;
            default:
                    break;
        }
    }
}

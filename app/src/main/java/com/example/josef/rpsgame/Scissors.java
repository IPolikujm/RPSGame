package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Scissors extends PlayerUnit {
    public Scissors(Context context, int r, int c, Player player,  int x, int y) {
        super(context, r, c, player, x, y);
        super.canMove = true;
        if (player.ID == 1) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.scissors90p_blue);
        }
        if (player.ID == 2) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.scissors90n_red);
        }
    }
}

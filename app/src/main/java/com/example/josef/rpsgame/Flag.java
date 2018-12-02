package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Flag extends PlayerUnit {
    public Flag(Context context, int r, int c, Player player,  int x, int y) {
        super(context, r, c, player, x, y);
        super.canMove = false;
        if (player.ID == 1) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.flag90p_blue);
        }
        if (player.ID == 2) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.flag90n_red);
        }

    }
}

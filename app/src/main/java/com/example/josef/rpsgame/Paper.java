package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Paper extends PlayerUnit {
    public Paper(Context context, int r, int c, Player player) {
        super(context, r, c, player);
        super.canMove = true;
        if (player.ID == 1) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.paper90p_blue);
        }
        if (player.ID == 2) {
            super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.paper90n_red);
        }

    }
}

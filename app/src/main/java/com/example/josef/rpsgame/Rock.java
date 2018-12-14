package com.example.josef.rpsgame;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Rock extends PlayerUnit {
    public Rock(Context context, int r, int c, Player player,  int x, int y) {
        super(context, r, c, player, x, y);
        super.canMove = true;
        if (player.ID == 1) {
            if (player.GetColor() == 1)
                super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone90p_blue);
            if (player.GetColor() == 2)
                super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone90p_green);

        }
        if (player.ID == 2) {
            if (player.GetColor() == 1)
                super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone90n_red);
            if (player.GetColor() == 2)
                super.UnitImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.stone90n_orange);
        }
    }
}

package com.example.josef.rpsgame;

public class Player {

    public String playerName;
    public int ID;
    private int Color = 1;

    public Player(String name, int ID){
        playerName = name;
        this.ID = ID;
    }

    public int GetColor(){
        return this.Color;
    }

    public void SetColor(int i){
        this.Color = i;
    }

}

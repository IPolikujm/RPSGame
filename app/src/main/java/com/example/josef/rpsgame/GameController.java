package com.example.josef.rpsgame;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class GameController {

    Context context;
    ArrayList<PlayerUnit> pjUnits;
    PlayerUnit unit;
    int displayHeight;
    int displayWidth;
    Point2D gameDesk_leftUpper_point;
    Point2D chessboardSquareVector;
    ChessboardSquare[][] chessBoard;
    Point2D gameDesk_rightBottom_point;
    ChessboardSquare clickedSquare = null;
    ChessboardSquare[] possibleMovement;
    Player player1;
    Player player2;
    Player onTurnPLayer;
    public boolean endGame = false;


    public GameController(Context context, double displayHeight, double displayWidth) {
        this.context = context;
        pjUnits = new ArrayList<>();
        player1 = new Player("Player1",1);
        player2 = new Player("Player2",2);
        onTurnPLayer = player1;
        this.displayHeight = (int)displayHeight;
        this.displayWidth = (int)displayWidth;
        //gameDesk_leftUpper_point = new Point2D(0,0);
       // gameDesk_leftUpper_point.x = (int)displayWidth/54;
       // gameDesk_leftUpper_point.y = (int)displayHeight/48;
        double vector_x = ((((double)(this.displayWidth))/54)*52)/8;
        double vector_y = (((((double)(this.displayHeight))/48)*26)/8);
        gameDesk_leftUpper_point = new Point2D( (int)displayWidth/54,11*(int)displayHeight/48); //levý horní roh šachovnice - pozice levého horního čtverečku
        chessboardSquareVector = new Point2D((int)vector_x,(int)vector_y);
        gameDesk_rightBottom_point = new Point2D(gameDesk_leftUpper_point.x + 9*chessboardSquareVector.x,gameDesk_leftUpper_point.y + 9*chessboardSquareVector.y);
        chessBoard = new ChessboardSquare[8][8];

        setUpChessboard();
        SetPLayer1Units();
    }

    public void SetPLayer1Units(){
        //p1Units.add(new Scissors(context));
        //unit = new Scissors(context,2,1);
        //pjUnits.add(unit);


        //PlayerUnit flagP1 = new PlayerUnit(context,0,6,player1);
        //PlayerUnit flagP2 = new PlayerUnit(context,7,4,player2);
       /*
        for (int i = 0; i < 4; i++){
            PlayerUnit scissors = new Scissors(context,i,0,player1);
            PlayerUnit rock = new Rock(context,i+4,0, player1);
            chessBoard[i][0].unit = scissors;
            chessBoard[i+4][0].unit = rock;
            PlayerUnit paper = new Paper(context,i,1, player1);
            chessBoard[i][1].unit = paper;
            chessBoard[i+4][1].unit = paper;

            scissors = new Scissors(context,i,0,player2);
            rock = new Rock(context,i+4,0, player2);
            chessBoard[i][7].unit = scissors;
            chessBoard[i+4][7].unit = rock;
            paper = new Paper(context,i,1, player2);
            chessBoard[i][6].unit = paper;
            chessBoard[i+4][6].unit = paper;

        }*/
        SetUnitsOnBoard(0,player1);
        SetUnitsOnBoard(6,player2);

    }

    public RPSGameView onUserAction(Point2D clickedPoint, RPSGameView view){
        //in chessboar
        if (clickedPoint.x > this.gameDesk_leftUpper_point.x && clickedPoint.x < gameDesk_rightBottom_point.x && clickedPoint.y > gameDesk_leftUpper_point.y && clickedPoint.y < gameDesk_rightBottom_point.y){
            Log.println(Log.INFO, "hello", "clicked in chessboard ");
            Toast.makeText(context,"Clicked in chessboard",Toast.LENGTH_SHORT);
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (chessBoard[i][j].isPointIn(clickedPoint)){//click on square

                        Log.println(Log.INFO, "hello2", "clicked in square [" + j + "][" + i + "]");
                        Log.println(Log.INFO, "hello2", "clicked in square [" + chessBoard[i][j].matrixPossition.x + "][" + chessBoard[i][j].matrixPossition.y + "]");
                        Toast.makeText(context, "clicked in square [" + i + "][" + j + "]", Toast.LENGTH_SHORT);
                        if (possibleMovement != null) {
                            //some square clicked 2nd time
                            for (int k = 0; k < 4; k++) {
                                if (chessBoard[i][j] == possibleMovement[k]) {
                                    DoMove(clickedSquare, possibleMovement[k]);
                                    view.Draw(chessBoard);
                                    possibleMovement = null;
                                    clickedSquare = null;
                                    changePLayer(onTurnPLayer);
                                    return view;
                                }
                            }
                        }
                        //some squre click 1st time
                        if (chessBoard[i][j].unit != null && chessBoard[i][j].unit.player == onTurnPLayer && chessBoard[i][j].unit.canMove) {
                            setPossibleMovement(chessBoard[i][j]);
                            clickedSquare = chessBoard[i][j];
                        }
                        return null;
                    }
                }
            }
        }else{//click outside chessboard
            possibleMovement = null;
            clickedSquare = null;
        }
        return null;
    }

    private void setUpChessboard(){
        Point2D point = new Point2D(gameDesk_leftUpper_point.x, gameDesk_leftUpper_point.y) ;

        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                chessBoard[i][j] = new ChessboardSquare(point,chessboardSquareVector);
                chessBoard[i][j].matrixPossition = new Point2D(i,j);
                point.x += (chessboardSquareVector.x);

            }
            point.x = gameDesk_leftUpper_point.x;
            point.y += (chessboardSquareVector.y);
        }
    }

    public void setPossibleMovement(ChessboardSquare square){
        possibleMovement = new ChessboardSquare[4];
        if (square.matrixPossition.y-1 >= 0){
            if (chessBoard[square.matrixPossition.x][square.matrixPossition.y-1] != null){
                if (chessBoard[square.matrixPossition.x][square.matrixPossition.y-1].unit == null) {
                    possibleMovement[0] = chessBoard[square.matrixPossition.x][square.matrixPossition.y - 1];
                }else{
                    if (chessBoard[square.matrixPossition.x][square.matrixPossition.y-1].unit.player != onTurnPLayer){
                        possibleMovement[0] = chessBoard[square.matrixPossition.x][square.matrixPossition.y - 1];
                    }
                }
            }
        }
        if (square.matrixPossition.y+1 < 8){
            if (chessBoard[square.matrixPossition.x][square.matrixPossition.y+1] != null){
                if (chessBoard[square.matrixPossition.x][square.matrixPossition.y + 1].unit == null) {
                    possibleMovement[1] = chessBoard[square.matrixPossition.x][square.matrixPossition.y + 1];
                }else {
                    if (chessBoard[square.matrixPossition.x][square.matrixPossition.y+1].unit.player.ID != onTurnPLayer.ID)
                    {
                        possibleMovement[1] = chessBoard[square.matrixPossition.x][square.matrixPossition.y + 1];
                    }
                }

            }
        }
        if (square.matrixPossition.x - 1 >= 0) {
            if (chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y] != null){
                if (chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y].unit == null) {
                    possibleMovement[2] = chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y];
                }else{
                    if (chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y].unit.player != onTurnPLayer){
                        possibleMovement[2] = chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y];

                    }
                }
            }
        }
        if (square.matrixPossition.x + 1 < 8) {
            if (chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y] != null) {
                if (chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y].unit == null) {
                    possibleMovement[3] = chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y];
                }else{
                    if (chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y].unit.player != onTurnPLayer){
                        possibleMovement[3] = chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y];
                    }
                }
            }
        }
        if (possibleMovement[0] == null && possibleMovement[1] == null && possibleMovement[2] == null && possibleMovement[3] == null){
            possibleMovement = null;
        }
    }

    public void DoMove(ChessboardSquare origin, ChessboardSquare newOne){
        if (newOne.unit == null){
            newOne.unit = origin.unit;
            origin.unit = null;
            return;
        }

        switch (doFight(origin,newOne)){
            case "win": {
                newOne.unit = origin.unit;
                origin.unit = null;
                break;
            }
            case "defeat":{
                origin.unit = null;
                break;
            }
            case "draw":{
                newOne.unit = origin.unit;
                origin.unit = null;
                break;
            }

        }
/*
        if (origin.unit != null) {
            newOne.unit = origin.unit;
            origin.unit = null;

        }
        */
    }

    public void changePLayer(Player player){
        if (player == player1){
            onTurnPLayer = player2;
        }
        if (player == player2){
            onTurnPLayer = player1;
        }
    }

    public void SetUnitsOnBoard(int r, Player player){
        boolean flagIsUp = false;
        Random rand = new Random();
        PlayerUnit unit;
        int help;
        for (int j = r; j <= r+1;j++) {
            for (int i = 0; i < 8; i++) {
                while (true) {
                    help = rand.nextInt(4) + 1;
                    unit = generateUnitOnBoard(j, i, help, player);
                    if (!flagIsUp) {
                        chessBoard[i][j].unit = unit;
                        if (unit instanceof Flag){
                            flagIsUp = true;
                        }
                        break;
                    }

                    if (flagIsUp && (unit instanceof Flag)) {
                    } else {
                        chessBoard[i][j].unit = unit;
                        break;
                    }
                }
            }
        }
    }

    private PlayerUnit generateUnitOnBoard(int r, int i, int randomValue, Player player) {
        switch (randomValue){
            case 1: return new Scissors(context, i, r, player);
            case 2: return new Paper(context,i,r,player);
            case 3: return new Rock(context,i,r,player);
            case 4: return new Flag(context,i,r,player);

        }
        return null;
    }

    public String doFight(ChessboardSquare attacking,ChessboardSquare defending){
        if (attacking.unit instanceof Paper){
            if (defending.unit instanceof Paper){
                return "draw";
            }
            if (defending.unit instanceof Scissors){
                return "defeat";
            }
            if (defending.unit instanceof Rock){
                return  "win";
            }
        }
        if (attacking.unit instanceof Rock){
            if (defending.unit instanceof Paper){
                return "defeat";
            }
            if (defending.unit instanceof Scissors){
                return "win";
            }
            if (defending.unit instanceof Rock){
                return  "draw";
            }
        }
        if (attacking.unit instanceof Scissors) {
            if (defending.unit instanceof Paper) {
                return "win";
            }
            if (defending.unit instanceof Scissors) {
                return "draw";
            }
            if (defending.unit instanceof Rock) {
                return "defeat";
            }
        }
        if (defending.unit instanceof  Flag){
            endGame = true;
            return "win";
        }

        return null;
    }

}

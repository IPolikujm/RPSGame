package com.example.josef.rpsgame;

import java.util.ArrayList;
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


    public GameController(Context context, double displayHeight, double displayWidth) {
        this.context = context;
        pjUnits = new ArrayList<>();
        this.displayHeight = (int)displayHeight;
        this.displayWidth = (int)displayWidth;
        //gameDesk_leftUpper_point = new Point2D(0,0);
       // gameDesk_leftUpper_point.x = (int)displayWidth/54;
       // gameDesk_leftUpper_point.y = (int)displayHeight/48;
        double vectorx = ((((double)(this.displayWidth))/54)*52)/8;
        double vectory = (((((double)(this.displayHeight))/48)*26)/8);
        gameDesk_leftUpper_point = new Point2D( (int)displayWidth/54,11*(int)displayHeight/48); //levý horní roh šachovnice - pozice levého horního čtverečku
        chessboardSquareVector = new Point2D((int)vectorx,(int)vectory);
        gameDesk_rightBottom_point = new Point2D(gameDesk_leftUpper_point.x + 9*chessboardSquareVector.x,gameDesk_leftUpper_point.y + 9*chessboardSquareVector.y);
        chessBoard = new ChessboardSquare[8][8];

        setUpChessboard();
        SetPLayer1Units();
    }

    public void SetPLayer1Units(){
        //p1Units.add(new Scissors(context));
        //unit = new Scissors(context,2,1);
        //pjUnits.add(unit);

        for (int i = 0; i < 8; i++){
            PlayerUnit unit = new Scissors(context,i,0);
            chessBoard[i][0].unit = unit;

        }

    }

    public RPSGameView onUserAction(Point2D clickedPoint, RPSGameView view){
        //in chessboar
        if (clickedPoint.x > this.gameDesk_leftUpper_point.x && clickedPoint.x < gameDesk_rightBottom_point.x && clickedPoint.y > gameDesk_leftUpper_point.y && clickedPoint.y < gameDesk_rightBottom_point.y){
            Log.println(Log.INFO, "hello", "clicked in chessboard ");
            Toast.makeText(context,"Clicked in chessboard",Toast.LENGTH_SHORT);
            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    if (chessBoard[i][j].isPointIn(clickedPoint)){
                        //click on square
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
                                    return view;
                                }
                            }
                        }
                        //some squre click 1st time
                        setPossibleMovement(chessBoard[i][j]);
                        clickedSquare = chessBoard[i][j];
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
                possibleMovement[0] = chessBoard[square.matrixPossition.x][square.matrixPossition.y-1];
            }
        }
        if (square.matrixPossition.y+1 < 8){
            if (chessBoard[square.matrixPossition.x][square.matrixPossition.y+1] != null){
                possibleMovement[1] = chessBoard[square.matrixPossition.x][square.matrixPossition.y+1];
            }
        }
        if (square.matrixPossition.x - 1 >= 0) {
            if (chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y] != null) {
                possibleMovement[2] = chessBoard[square.matrixPossition.x - 1][square.matrixPossition.y];
            }
        }
        if (square.matrixPossition.x + 1 < 8) {
            if (chessBoard[square.matrixPossition.x + 1][square.matrixPossition.y] != null) {
                possibleMovement[3] = chessBoard[square.matrixPossition.x+ 1][square.matrixPossition.y ];
            }
        }
        if (possibleMovement[0] == null && possibleMovement[1] == null && possibleMovement[2] == null && possibleMovement[3] == null){
            possibleMovement = null;
        }
    }

    public void DoMove(ChessboardSquare origin, ChessboardSquare newOne){
        if (origin.unit != null) {
            newOne.unit = origin.unit;
            origin.unit = null;

        }
    }

}

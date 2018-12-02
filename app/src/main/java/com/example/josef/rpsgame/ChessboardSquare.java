package com.example.josef.rpsgame;

public class ChessboardSquare {

    public Point2D leftUpperPoint;
    public Point2D rightBottomPoint;
    public Point2D matrixPossition;
    public PlayerUnit unit = null;
    public Point2D imageLeftUpperPoint;
    boolean MoveFrom = false;
    boolean MoveTo = false;

    public ChessboardSquare(Point2D LUP, Point2D vector) {
        leftUpperPoint = new Point2D(LUP.x,LUP.y);
        rightBottomPoint = new Point2D(leftUpperPoint.x + vector.x,leftUpperPoint.y + vector.y);
        imageLeftUpperPoint = new Point2D((leftUpperPoint.x + (vector.x-64)/2),leftUpperPoint.y + (vector.y-64)/2);
    }

    public ChessboardSquare() {
        leftUpperPoint = new Point2D();
        rightBottomPoint = new Point2D();
        imageLeftUpperPoint = new Point2D();
    }

    public boolean isPointIn(Point2D point){
        if (point.x > leftUpperPoint.x && point.x <= rightBottomPoint.x && point.y > leftUpperPoint.y && point.y <= rightBottomPoint.y){
            return true;
        }else{
            return false;
        }

    }

    public PlayerUnit getPlayerUnit(){
        return unit;
    }
}

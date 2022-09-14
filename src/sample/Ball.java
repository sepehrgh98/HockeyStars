package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    double spx,spy;
    double fk,vxl,vyl;
    double mas,rad;
    Ball(double X, double Y, double R, Color C){
        super(X,Y,R,C);
        spx=0;
        spy=0;
        mas=5*R;
        rad=R;
        fk=1*mas;

    }

}

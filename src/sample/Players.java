package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Players extends Circle {

    double spx,spy,mas,rad,fk,vxl,vyl;

    Players(double X, double Y, double R, Color C){
        super(X,Y,R,C);
        spx=0;
        spy=0;
        mas=R*7;
        rad=R;
        fk=1*mas;
    }



}

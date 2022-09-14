package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.css.SimpleStyleableDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Locale;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class computerplay {
    public Text reportext;
    SimpleBooleanProperty p1goal;
    SimpleBooleanProperty p2goal;
    SimpleBooleanProperty p1goalch;
    SimpleBooleanProperty p2goalch;
    SimpleBooleanProperty runanim;
    int player1goal;
    int player2goal;
    public Text turntt;
    public Text pl1g;
    public Button mmb;
    public Text pl2g;
    String codp;
    double delx, dely, cost, sint, dist,last;
    double afgtime;
    double prex;
    double prey;
    double dragx;
    double dragy;
    boolean isclose;
    double relx;
    double rely;
    int turn,lturn;
    Players[] players1;
    Players[] players2;
    double t;
    public AnchorPane anchor;
    Ball ball ;
    @FXML
    void initialize() {
        p1goal=new SimpleBooleanProperty(false);
        p2goal=new SimpleBooleanProperty(false);
        p1goalch=new SimpleBooleanProperty(false);
        p2goalch=new SimpleBooleanProperty(false);
        runanim=new SimpleBooleanProperty(true);

        int n = 5;
        players1 = new Players[n];
        players1[0] = new Players(430, 141, 10, Color.RED);
        players1[1] = new Players(430, 225, 10, Color.RED);
        players1[2] = new Players(430, 299, 10, Color.RED);
        players1[3] = new Players(380, 225, 10, Color.RED);
        players1[4] = new Players(490, 225, 10, Color.RED);

        players2 = new Players[n];
        players2[0] = new Players(170, 141, 10, Color.BLUE);
        players2[1] = new Players(170, 225, 10, Color.BLUE);
        players2[2] = new Players(170, 299, 10, Color.BLUE);
        players2[3] = new Players(220, 225, 10, Color.BLUE);
        players2[4] = new Players(110, 225, 10, Color.BLUE);

        ball= new Ball(300, 226, 6, Color.BLACK);
        anchor.getChildren().addAll(players1);
        anchor.getChildren().addAll(players2);
        anchor.getChildren().addAll(ball);
        p1goal.addListener((observable, oldValue, newValue) -> {
            if(p1goal.get()==true){
                resetgame();
                p1goalch.set(true);
                player1goal+=1;
                pl1g.setText(Integer.toString(player1goal));
            }
        });
        p2goal.addListener((observable, oldValue, newValue) -> {
            if(p2goal.get()==true) {
                resetgame();
                p2goalch.set(true);
                player2goal += 1;
                pl2g.setText(Integer.toString(player2goal));
            }
        });
        ball.setOnMousePressed(event -> {
            ball.spx = 100;
            ball.spy = 100;
        });
        for ( int i=0;i<players1.length;i++) {
            Players p = players1[i];
            p.setOnMousePressed(event -> {

                if(deturn()==true&&iscalm()==true) {

                    prex = event.getX();
                    prey = event.getY();
                    codp=event.getButton().toString();
                    System.out.println(codp);
                    System.out.println(!codp.equals("SECONDARY"));
                }
            });
            p.setOnMouseDragged(event -> {
                if(deturn()==true&&iscalm()==true) {

                    if (sqrt((ball.getCenterX() - p.getCenterX()) * (ball.getCenterX() - p.getCenterX()) + (ball.getCenterY() - p.getCenterY()) * (ball.getCenterY() - p.getCenterY())) <= 50) {
                        if(!codp.equals("SECONDARY") ) {
                            dragx = event.getX();
                            dragy = event.getY();
                            System.out.println(dragx);
                            delx = dragx - prex;
                            dely = dragy - prey;
                            dist = sqrt(delx * delx + dely * dely);
                            sint = dely / dist;
                            cost = delx / dist;
                            ball.setCenterY(p.getCenterY() + 20 * sint * (-1));
                            ball.setCenterX(p.getCenterX() + 20 * cost * (-1));
                        }
                    }
                }
            });
            p.setOnMouseReleased(event -> {
                if(deturn()==true&&iscalm()==true) {

                    if (sqrt((ball.getCenterX() - p.getCenterX()) * (ball.getCenterX() - p.getCenterX()) + (ball.getCenterY() - p.getCenterY()) * (ball.getCenterY() - p.getCenterY())) <= 50) {
                        if (!codp.equals("SECONDARY")) {
                            turn++;
                            relx = event.getX();
                            rely = event.getY();
                            ball.spy = -(rely - prey) * 10;
                            ball.spx = -(relx - prex) * 10;
                            if ((rely - prey) * (rely - prey) + (relx - prex) * (relx - prex) >= 1024) {
                                delx = relx - prex;
                                dely = rely - prey;
                                dist = sqrt(delx * delx + dely * dely);
                                sint = dely / dist;
                                cost = delx / dist;
                                ball.spy = 32 * sint * (-10);
                                ball.spx = 32 * cost * (-10);
                                isclose = false;

                            }
                        }
                        else if( codp.equals("SECONDARY")){
                            turn++;
                            relx = event.getX();
                            rely = event.getY();
                            p.spy = -(rely - prey) * 10;
                            p.spx = -(relx - prex) * 10;
                            if ((rely - prey) * (rely - prey) + (relx - prex) * (relx - prex) >= 1024) {
                                delx = relx - prex;
                                dely = rely - prey;
                                dist = sqrt(delx * delx + dely * dely);
                                sint = dely / dist;
                                cost = delx / dist;
                                p.spy = 32 * sint * (-10);
                                p.spx = 32 * cost * (-10);
                                isclose = false;
                            }
                        }
                    }
                    else {
                        turn++;
                        relx = event.getX();
                        rely = event.getY();
                        p.spy = -(rely - prey) * 10;
                        p.spx = -(relx - prex) * 10;
                        if ((rely - prey) * (rely - prey) + (relx - prex) * (relx - prex) >= 1024) {
                            delx = relx - prex;
                            dely = rely - prey;
                            dist = sqrt(delx * delx + dely * dely);
                            sint = dely / dist;
                            cost = delx / dist;
                            p.spy = 32 * sint * (-10);
                            p.spx = 32 * cost * (-10);

                        }
                    }
                }
            });
        }




        AnimationTimer hanim = new AnimationTimer() {
            long prt;
            @Override
            public void start() {

                super.start();
                prt = System.nanoTime();
                last=System.nanoTime();
            }

            @Override
            public void handle(long now) {
                long elap = now - prt;
                prt = now;
                t = elap / 1000000000.0;
                if(runanim.get()==true){
                    bmovement();
                    bwallco();
                    bcolp();
                    pcolp();
                    psmowe();
                    pswalco();
                    turntime(now);
                    bcolgate();
                    pcolgate();
                    playeringate();
                    comuterplay(now);
                    goal();
                }
                aftergoal(now);
            }
        };
        hanim.start();

    }



    public void bmovement() {
        if (ball.spx <2 && ball.spx > -2) {
            ball.spy = 0;
            ball.spx = 0;
        }
        double a = ball.fk;
        double ay = a * (abs(ball.spy) / (sqrt(ball.spy * ball.spy + ball.spx * ball.spx)));
        double ax = a * (abs(ball.spx) / (sqrt(ball.spy * ball.spy + ball.spx * ball.spx)));
        if (ball.spx > 0 && ball.spy > 0) {
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spx = ball.spx - t * ax;
            ball.spy = ball.spy - t * ay;

        } else if (ball.spx < 0 && ball.spy < 0) {
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spx = ball.spx + t * ax;
            ball.spy = ball.spy + t * ay;

        } else if (ball.spy < 0 && ball.spx > 0) {
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spx = ball.spx - t * ax;
            ball.spy = ball.spy + t * ay;


        } else if (ball.spy > 0 && ball.spx < 0) {
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spx = ball.spx + t * ax;
            ball.spy = ball.spy - t * ay;

        }
        else if(ball.spx==0&&ball.spy<0){
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spy = ball.spy + t * ay;
        }
        else if(ball.spx==0&&ball.spy>0){
            ball.setCenterY(ball.getCenterY() + t * ball.spy);
            ball.spy = ball.spy - t * ay;
        }
        else if(ball.spx<0&&ball.spy==0){
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.spx = ball.spx + t * ax;
        }
        else if(ball.spx>0&&ball.spy==0){
            ball.setCenterX(ball.getCenterX() + t * ball.spx);
            ball.spx = ball.spx - t * ax;
        }

    }


    public void bwallco() {

        if ((ball.getCenterX() - ball.getRadius() <= 4 && ball.spx < 0)
                || (ball.getCenterX() + ball.getRadius() >= 596 && ball.spx > 0)) {
            ball.spx *= -1;
        }
        if ((ball.getCenterY() - ball.getRadius() <= 67 && ball.spy < 0)
                || (ball.getCenterY() + ball.getRadius() >= 385 && ball.spy > 0)) {
            ball.spy *= -1;
        }

    }


    public void bcolp() {

        for (Players player : players1) {
            if (sqrt((ball.getCenterX() - player.getCenterX()) * (ball.getCenterX() - player.getCenterX()) + (ball.getCenterY() - player.getCenterY()) * (ball.getCenterY() - player.getCenterY())) <= ball.rad + player.rad) {
                if ((player.getCenterX() - ball.getCenterX()) * (player.spx - ball.spx) + (player.getCenterY() - ball.getCenterY()) * (player.spy - ball.spy) < 0) {
                    final double deltaX = ball.getCenterX() - player.getCenterX();
                    final double deltaY = ball.getCenterY() - player.getCenterY();
                    final double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
                    final double unitContactX = deltaX / distance;
                    final double unitContactY = deltaY / distance;

                    final double xVelocity1 = player.spx;
                    final double yVelocity1 = player.spy;
                    final double xVelocity2 = ball.spx;
                    final double yVelocity2 = ball.spy;

                    final double u1 = xVelocity1 * unitContactX + yVelocity1 * unitContactY;
                    final double u2 = xVelocity2 * unitContactX + yVelocity2 * unitContactY;

                    final double massSum = player.mas + ball.mas;
                    final double massDiff = player.mas - ball.mas;

                    final double v1 = (2 * ball.mas * u2 + u1 * massDiff) / massSum;
                    final double v2 = (2 * player.mas * u1 - u2 * massDiff) / massSum;
                    final double u1PerpX = xVelocity1 - u1 * unitContactX;
                    final double u1PerpY = yVelocity1 - u1 * unitContactY;
                    final double u2PerpX = xVelocity2 - u2 * unitContactX;
                    final double u2PerpY = yVelocity2 - u2 * unitContactY;

                    player.spx = (v1 * unitContactX + u1PerpX);
                    player.spy = (v1 * unitContactY + u1PerpY);
                    ball.spx = (v2 * unitContactX + u2PerpX);
                    ball.spy = (v2 * unitContactY + u2PerpY);

                }
            }
        }
        for (Players player : players2) {
            if (sqrt((ball.getCenterX() - player.getCenterX()) * (ball.getCenterX() - player.getCenterX()) + (ball.getCenterY() - player.getCenterY()) * (ball.getCenterY() - player.getCenterY())) <= ball.rad + player.rad) {
                if ((player.getCenterX() - ball.getCenterX()) * (player.spx - ball.spx) + (player.getCenterY() - ball.getCenterY()) * (player.spy - ball.spy) < 0) {

                    final double deltaX = ball.getCenterX() - player.getCenterX();
                    final double deltaY = ball.getCenterY() - player.getCenterY();
                    final double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
                    final double unitContactX = deltaX / distance;
                    final double unitContactY = deltaY / distance;

                    final double xVelocity1 = player.spx;
                    final double yVelocity1 = player.spy;
                    final double xVelocity2 = ball.spx;
                    final double yVelocity2 = ball.spy;

                    final double u1 = xVelocity1 * unitContactX + yVelocity1 * unitContactY;
                    final double u2 = xVelocity2 * unitContactX + yVelocity2 * unitContactY;

                    final double massSum = player.mas + ball.mas;
                    final double massDiff = player.mas - ball.mas;

                    final double v1 = (2 * ball.mas * u2 + u1 * massDiff) / massSum;
                    final double v2 = (2 * player.mas * u1 - u2 * massDiff) / massSum;

                    final double u1PerpX = xVelocity1 - u1 * unitContactX;
                    final double u1PerpY = yVelocity1 - u1 * unitContactY;
                    final double u2PerpX = xVelocity2 - u2 * unitContactX;
                    final double u2PerpY = yVelocity2 - u2 * unitContactY;

                    player.spx = (v1 * unitContactX + u1PerpX);
                    player.spy = (v1 * unitContactY + u1PerpY);
                    ball.spx = (v2 * unitContactX + u2PerpX);
                    ball.spy = (v2 * unitContactY + u2PerpY);

                }
            }
        }
    }

    public void pcolp() {

        for (Players player : players1) {
            for (Players p : players2) {
                if (sqrt((p.getCenterX() - player.getCenterX()) * (p.getCenterX() - player.getCenterX()) + (p.getCenterY() - player.getCenterY()) * (p.getCenterY() - player.getCenterY())) <= p.rad + player.rad) {
                    if ((player.getCenterX() - p.getCenterX()) * (player.spx - p.spx) + (player.getCenterY() - p.getCenterY()) * (player.spy - p.spy) < 0) {
                        final double deltaX = p.getCenterX() - player.getCenterX();
                        final double deltaY = p.getCenterY() - player.getCenterY();
                        final double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
                        final double unitContactX = deltaX / distance;
                        final double unitContactY = deltaY / distance;

                        final double xVelocity1 = player.spx;
                        final double yVelocity1 = player.spy;
                        final double xVelocity2 = p.spx;
                        final double yVelocity2 = p.spy;

                        final double u1 = xVelocity1 * unitContactX + yVelocity1 * unitContactY;
                        final double u2 = xVelocity2 * unitContactX + yVelocity2 * unitContactY;

                        final double massSum = player.mas + p.mas;
                        final double massDiff = player.mas - p.mas;

                        final double v1 = (2 * p.mas * u2 + u1 * massDiff) / massSum;
                        final double v2 = (2 * player.mas * u1 - u2 * massDiff) / massSum;
                        final double u1PerpX = xVelocity1 - u1 * unitContactX;
                        final double u1PerpY = yVelocity1 - u1 * unitContactY;
                        final double u2PerpX = xVelocity2 - u2 * unitContactX;
                        final double u2PerpY = yVelocity2 - u2 * unitContactY;

                        player.spx = (v1 * unitContactX + u1PerpX);
                        player.spy = (v1 * unitContactY + u1PerpY);
                        p.spx = (v2 * unitContactX + u2PerpX);
                        p.spy = (v2 * unitContactY + u2PerpY);

                    }
                }
            }

        }


        for (int i = 0; i < players1.length; i++) {
            Players player = players1[i];
            for (int j = i + 1; j < players1.length; j++) {
                Players p = players1[j];
                if (sqrt((p.getCenterX() - player.getCenterX()) * (p.getCenterX() - player.getCenterX()) + (p.getCenterY() - player.getCenterY()) * (p.getCenterY() - player.getCenterY())) <= p.rad + player.rad) {
                    if ((player.getCenterX() - p.getCenterX()) * (player.spx - p.spx) + (player.getCenterY() - p.getCenterY()) * (player.spy - p.spy) < 0) {
                        final double deltaX = p.getCenterX() - player.getCenterX();
                        final double deltaY = p.getCenterY() - player.getCenterY();
                        final double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
                        final double unitContactX = deltaX / distance;
                        final double unitContactY = deltaY / distance;

                        final double xVelocity1 = player.spx;
                        final double yVelocity1 = player.spy;
                        final double xVelocity2 = p.spx;
                        final double yVelocity2 = p.spy;

                        final double u1 = xVelocity1 * unitContactX + yVelocity1 * unitContactY;
                        final double u2 = xVelocity2 * unitContactX + yVelocity2 * unitContactY;

                        final double massSum = player.mas + p.mas;
                        final double massDiff = player.mas - p.mas;

                        final double v1 = (2 * p.mas * u2 + u1 * massDiff) / massSum;
                        final double v2 = (2 * player.mas * u1 - u2 * massDiff) / massSum;

                        final double u1PerpX = xVelocity1 - u1 * unitContactX;
                        final double u1PerpY = yVelocity1 - u1 * unitContactY;
                        final double u2PerpX = xVelocity2 - u2 * unitContactX;
                        final double u2PerpY = yVelocity2 - u2 * unitContactY;

                        player.spx = (v1 * unitContactX + u1PerpX);
                        player.spy = (v1 * unitContactY + u1PerpY);
                        p.spx = (v2 * unitContactX + u2PerpX);
                        p.spy = (v2 * unitContactY + u2PerpY);

                    }
                }

            }

        }
        for (int i = 0; i < players2.length; i++) {
            Players player = players2[i];
            for (int j = i + 1; j < players2.length; j++) {
                Players p = players2[j];

                if (sqrt((p.getCenterX() - player.getCenterX()) * (p.getCenterX() - player.getCenterX()) + (p.getCenterY() - player.getCenterY()) * (p.getCenterY() - player.getCenterY())) <= p.rad + player.rad) {
                    if ((player.getCenterX() - p.getCenterX()) * (player.spx - p.spx) + (player.getCenterY() - p.getCenterY()) * (player.spy - p.spy) < 0) {

                        final double deltaX = p.getCenterX() - player.getCenterX();
                        final double deltaY = p.getCenterY() - player.getCenterY();
                        final double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
                        final double unitContactX = deltaX / distance;
                        final double unitContactY = deltaY / distance;

                        final double xVelocity1 = player.spx;
                        final double yVelocity1 = player.spy;
                        final double xVelocity2 = p.spx;
                        final double yVelocity2 = p.spy;

                        final double u1 = xVelocity1 * unitContactX + yVelocity1 * unitContactY; // velocity of ball 1 parallel to contact vector
                        final double u2 = xVelocity2 * unitContactX + yVelocity2 * unitContactY; // same for ball 2

                        final double massSum = player.mas + p.mas;
                        final double massDiff = player.mas - p.mas;

                        final double v1 = (2 * p.mas * u2 + u1 * massDiff) / massSum; // These equations are derived for one-dimensional collision by
                        final double v2 = (2 * player.mas * u1 - u2 * massDiff) / massSum; // solving equations for conservation of momentum and conservation of energy

                        final double u1PerpX = xVelocity1 - u1 * unitContactX; // Components of ball 1 velocity in direction perpendicular
                        final double u1PerpY = yVelocity1 - u1 * unitContactY; // to contact vector. This doesn't change with collision
                        final double u2PerpX = xVelocity2 - u2 * unitContactX; // Same for ball 2....
                        final double u2PerpY = yVelocity2 - u2 * unitContactY;

                        player.spx = (v1 * unitContactX + u1PerpX);
                        player.spy = (v1 * unitContactY + u1PerpY);
                        p.spx = (v2 * unitContactX + u2PerpX);
                        p.spy = (v2 * unitContactY + u2PerpY);

                    }
                }
            }

        }


    }

    public void psmowe() {

        for (Players p : players1) {

            if (p.spx > -2 && p.spx < 2) {
                p.spy = 0;
                p.spx = 0;
            }
            double a = p.fk;
            double ay = a * (abs(p.spy) / (sqrt(p.spy * p.spy + p.spx * p.spx)));
            double ax = a * (abs(p.spx) / (sqrt(p.spy * p.spy + p.spx * p.spx)));
            if (p.spx > 0 && p.spy > 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx - t * ax;
                p.spy = p.spy - t * ay;

            } else if (p.spx < 0 && p.spy < 0) {

                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx + t * ax;
                p.spy = p.spy + t * ay;
            } else if (p.spy < 0 && p.spx > 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx - t * ax;
                p.spy = p.spy + t * ay;

            } else if (p.spy > 0 && p.spx < 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx + t * ax;
                p.spy = p.spy - t * ay;

            }
            else if(p.spx==0&&p.spy<0){
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spy = p.spy + t * ay;
            }
            else if(p.spx==0&&p.spy>0){
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spy = p.spy - t * ay;
            }
            else if(p.spx<0&&p.spy==0){
                p.setCenterX(p.getCenterX() + t *p.spx);
                p.spx = p.spx + t * ax;
            }
            else if(p.spx>0&&p.spy==0){
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.spx = p.spx - t * ax;
            }
        }
        for (Players p : players2) {
            if (p.spx < 2 && p.spx > -2) {
                p.spy = 0;
                p.spx = 0;
            }
            double a = p.fk;
            double ay = a * (abs(p.spy) / (sqrt(p.spy * p.spy + p.spx * p.spx)));
            double ax = a * (abs(p.spx) / (sqrt(p.spy * p.spy + p.spx * p.spx)));
            if (p.spx > 0 && p.spy > 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx - t * ax;
                p.spy = p.spy - t * ay;

            } else if (p.spx < 0 && p.spy < 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx + t * ax;
                p.spy = p.spy + t * ay;

            } else if (p.spy < 0 && p.spx > 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx - t * ax;
                p.spy = p.spy + t * ay;

            } else if (p.spy > 0 && p.spx < 0) {
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spx = p.spx + t * ax;
                p.spy = p.spy - t * ay;

            }
            else if(p.spx==0&&p.spy<0){
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spy = p.spy + t * ay;
            }
            else if(p.spx==0&&p.spy>0){
                p.setCenterY(p.getCenterY() + t * p.spy);
                p.spy = p.spy - t * ay;
            }
            else if(p.spx<0&&p.spy==0){
                p.setCenterX(p.getCenterX() + t *p.spx);
                p.spx = p.spx + t * ax;
            }
            else if(p.spx>0&&p.spy==0){
                p.setCenterX(p.getCenterX() + t * p.spx);
                p.spx = p.spx - t * ax;
            }
        }
    }

    public void pswalco() {

        for (Players p : players1) {
            if ((p.getCenterX() - p.getRadius() <= 4 && p.spx < 0)
                    || (p.getCenterX() + p.getRadius() >=596 && p.spx > 0)) {
                p.spx *= -1;
            }
            if ((p.getCenterY() - p.getRadius() <= 67 && p.spy < 0)
                    || (p.getCenterY() + p.getRadius() >= 385 && p.spy > 0)) {
                p.spy *= -1;
            }
        }
        for (Players p : players2) {
            if ((p.getCenterX() - p.getRadius() <= 4 && p.spx < 0)
                    || (p.getCenterX() + p.getRadius() >= 596 && p.spx > 0)) {
                p.spx *= -1;
            }
            if ((p.getCenterY() - p.getRadius() <= 67 && p.spy < 0)
                    || (p.getCenterY() + p.getRadius() >= 385 && p.spy > 0)) {
                p.spy *= -1;
            }
        }
    }


    public boolean deturn(){
        if(turn%2==0)return true;
        return false;
    }
    public boolean iscalm(){

        for(int i=0;i<players1.length;i++){
            Players p=players1[i];
            if(p.spx==0&&p.spy==0){
                continue;
            }
            else return false;
        }
        for(int i=0;i<players2.length;i++){
            Players p=players2[i];

            if(p.spx==0&&p.spy==0){
                continue;
            }
            else return false;
        }
        if(ball.spx==0&&ball.spy==0){
            return true;
        }
        else return false;
    }
    public void turntime(long now){
        if(iscalm()) {


            if (turn % 2 == 0) {
                turntt.setText(String.format(Locale.ENGLISH, "player1 remained time:%f", 30 - (now / 1000000000.0 - last / 1000000000.0)));
            } else if (turn % 2 != 0) {
                turntt.setText(String.format(Locale.ENGLISH, "player2 remained time:%f", 30 - (now / 1000000000.0 - last / 1000000000.0)));

            }
            if (turn == lturn) {
                if (now / 1000000000.0 - last / 1000000000.0 > 30) {
                    turn++;
                    lturn++;
                    last = now;
                }
            } else if (turn != lturn) {
                last = now;
                lturn = turn;
            }
        }
    }
    public void bcolgate(){
        if ((ball.getCenterX() - ball.getRadius() <= 536 && ball.getCenterY()>=184&&ball.getCenterY()<=268&&ball.getCenterX()>536 && ball.spx < 0)
                || (ball.getCenterX() + ball.getRadius() >= 64 && ball.getCenterY()>=184&&ball.getCenterY()<=268 &&ball.getCenterX()<64 &&ball.spx > 0)) {
            ball.spx *= -1;
        }
        if ((ball.getCenterY() - ball.getRadius() <= 268 && ((ball.getCenterX()<=96&&ball.getCenterX()>=64)||(ball.getCenterX()>=504&&ball.getCenterX()<=536))&&ball.getCenterY()>268 && ball.spy < 0)
                || (ball.getCenterY() + ball.getRadius() >= 184 && ((ball.getCenterX()<=96&&ball.getCenterX()>=64)||(ball.getCenterX()>=504&&ball.getCenterX()<=536))&&ball.getCenterY()<184 && ball.spy > 0)) {
            ball.spy *= -1;
        }

    }
    public void pcolgate(){
        for (Players p:players1) {
            if ((p.getCenterX() - p.getRadius() <= 536 && p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() > 536 && p.spx < 0)
                    || (p.getCenterX() +p.getRadius() >= 64 &&p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() < 64 && p.spx > 0)||
                    (p.getCenterX() + p.getRadius() >= 536 && p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() < 536 && p.spx > 0)
                    ||(p.getCenterX()-p.getRadius() <= 64 &&p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() > 64 && p.spx < 0)) {
                p.spx *= -1;
            }
            if ((p.getCenterY() - p.getRadius() <= 268 && ((p.getCenterX() <= 96 &&p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() > 268 &&p.spy < 0)
                    || (p.getCenterY() +p.getRadius() >= 184 && ((p.getCenterX() <= 96 && p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() < 184 && p.spy > 0)||
                    (p.getCenterY() + p.getRadius() >= 268 && ((p.getCenterX() <= 96 &&p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() < 268 &&p.spy >0)||
                    (p.getCenterY() -p.getRadius() <= 184 && ((p.getCenterX() <= 96 && p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() > 184 && p.spy <0) ) {
                p.spy *= -1;
            }
        }
        for(Players p:players2){
            if ((p.getCenterX() - p.getRadius() <= 536 && p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() > 536 && p.spx < 0)
                    || (p.getCenterX() +p.getRadius() >= 64 &&p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() < 64 && p.spx > 0)||
                    (p.getCenterX() + p.getRadius() >= 536 && p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() < 536 && p.spx > 0)
                    ||(p.getCenterX()-p.getRadius() <= 64 &&p.getCenterY() >= 184 && p.getCenterY() <= 268 && p.getCenterX() > 64 && p.spx < 0)) {
                p.spx *= -1;
            }
            if ((p.getCenterY() - p.getRadius() <= 268 && ((p.getCenterX() <= 96 &&p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() > 268 &&p.spy < 0)
                    || (p.getCenterY() +p.getRadius() >= 184 && ((p.getCenterX() <= 96 && p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() < 184 && p.spy > 0)||
                    (p.getCenterY() + p.getRadius() >= 268 && ((p.getCenterX() <= 96 &&p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() < 268 &&p.spy >0)||
                    (p.getCenterY() -p.getRadius() <= 184 && ((p.getCenterX() <= 96 && p.getCenterX() >= 64) || (p.getCenterX() >= 504 && p.getCenterX() <= 536)) && p.getCenterY() > 184 && p.spy <0) ) {
                p.spy *= -1;
            }
        }

    }
    public void goal(){
        if(ball.getCenterX()-ball.getRadius()<=96 && ball.getCenterX()>96 && ball.getCenterY()>=184&&ball.getCenterY()<=268&&ball.spx<0){
            p1goal.set(true);
            runanim.set(false);
            afgtime=System.nanoTime();
        }
        if(ball.getCenterX()+ball.getRadius()>=504 && ball.getCenterX()<504 && ball.getCenterY()>=184&&ball.getCenterY()<=268&&ball.spx>0){
            p2goal.set(true);
            runanim.set(false);
            afgtime=System.nanoTime();
        }

    }
    public void aftergoal(long now){
        if(now/1000000000.0-afgtime/1000000000.0 >2){
            reportext.setText("continue...");
            runanim.set(true);
            //resetgame();
            if(p1goal.get()==true){
                turn=1;
                p1goal.set(false);
            }
            if(p2goal.get()==true){
                turn=0;
                p2goal.set(false);
            }
        }
        else {
            if (p1goalch.get() == true) {
                reportext.setText("a goal was recorded for player 1");
                afgtime = now;
                p1goalch.set(false);

            }
            if (p2goalch.get() == true) {
                reportext.setText("a goal was recorded for player 2");
                afgtime = now;
                p2goalch.set(false);
            }
        }

    }
    public void resetgame(){
        players1[0].setCenterX(430);
        players1[0].setCenterY(141);
        players1[1].setCenterX(430);
        players1[1].setCenterY(225);
        players1[2].setCenterX(430);
        players1[2].setCenterY(299);
        players1[3].setCenterX(380);
        players1[3].setCenterY(225);
        players1[4].setCenterX(490);
        players1[4].setCenterY(225);
        for(int i=0;i<players1.length;i++) {
            players1[i].spx = 0;
            players1[i].spy = 0;
        }
        players2[0].setCenterX(170);
        players2[0].setCenterY(141);
        players2[1].setCenterX(170);
        players2[1].setCenterY(225);
        players2[2].setCenterX(170);
        players2[2].setCenterY(299);
        players2[3].setCenterX(220);
        players2[3].setCenterY(225);
        players2[4].setCenterX(110);
        players2[4].setCenterY(225);
        for(int i=0;i<players2.length;i++) {
            players2[i].spx = 0;
            players2[i].spy = 0;
        }
        ball.setCenterX(300);
        ball.setCenterY(226);
        ball.spx=0;
        ball.spy=0;
    }
    public void playeringate(){
        for (Players p:players1) {
            if (p.getCenterX() + p.getRadius() < 536 && p.getCenterX() > 504 && p.getCenterY() - p.getRadius() > 184 && p.getCenterY() + p.getRadius() < 268 && p.spx == 0 && p.spy == 0) {
                p.setCenterX(p.getCenterX() - (p.getCenterX() - 504) * 2 - 20);
            }
            else if(p.getCenterX()- p.getRadius() > 64 && p.getCenterX() < 96 && p.getCenterY() - p.getRadius() > 184 && p.getCenterY() + p.getRadius() < 268 && p.spx == 0 && p.spy == 0){
                p.setCenterX(p.getCenterX()+(96-p.getCenterX())*2+20);
            }
        }
        for (Players p:players2){
            if (p.getCenterX() + p.getRadius() < 536 && p.getCenterX() > 504 && p.getCenterY() - p.getRadius() > 184 && p.getCenterY() + p.getRadius() < 268 && p.spx == 0 && p.spy == 0) {
                p.setCenterX(p.getCenterX() - (p.getCenterX() - 504) * 2 - 20);
            }
            else if(p.getCenterX()- p.getRadius() > 64 && p.getCenterX() < 96 && p.getCenterY() - p.getRadius() > 184 && p.getCenterY() + p.getRadius() < 268 && p.spx == 0 && p.spy == 0){
                p.setCenterX(p.getCenterX()+(96-p.getCenterX())*2+20);
            }
        }
    }
    public void comuterplay(long now){
        if (turn%2!=0&&iscalm()==true&&now/1000000000.0-last/1000000000.0>4) {
            for (int i = 0; i < players2.length; i++) {
                Players p = players2[i];
                if (sqrt((ball.getCenterX() - p.getCenterX()) * (ball.getCenterX() - p.getCenterX()) + (ball.getCenterY() - p.getCenterY()) * (ball.getCenterY() - p.getCenterY())) <= 50) {
                    delx = 504 - p.getCenterX();
                    dely = 225 - p.getCenterY();
                    dist = sqrt(delx * delx + dely * dely);
                    sint = dely / dist;
                    cost = delx / dist;
                    ball.setCenterY(p.getCenterY() + 20 * sint * (1));
                    ball.setCenterX(p.getCenterX() + 20 * cost * (1));
                    if (now / 1000000000.0 - last / 1000000000.0 > 6) {
                        ball.spy = 32 * sint * (10);
                        ball.spx = 32 * cost * (10);
                        turn++;
                        return;
                    }
                }
            }
            if (now / 1000000000.0 - last / 1000000000.0 > 6) {
                double m, b, d;
                for (int i = 0; i < players2.length; i++) {
                    Players p = players2[i];
                    m = (225 - p.getCenterY()) / (504 - p.getCenterX());
                    b = p.getCenterY() - m * p.getCenterX();
                    d = 3 * sqrt(1 + m * m);
                    if (ball.getCenterY() <= m * ball.getCenterX() + d && ball.getCenterY() >= m * ball.getCenterX() - d) {
                        delx = ball.getCenterX() - p.getCenterX();
                        dely = ball.getCenterY() - p.getCenterY();
                        dist = sqrt(delx * delx + dely * dely);
                        sint = dely / dist;
                        cost = delx / dist;
                        p.spy = 32 * sint * (10);
                        p.spx = 32 * cost * (10);
                        turn++;
                        return;
                    }
                }
                int nearest = 0;
                double neardist = sqrt((players2[0].getCenterX() - ball.getCenterX()) * (players2[0].getCenterX() - ball.getCenterX()) + (players2[0].getCenterY() - ball.getCenterY()) * (players2[0].getCenterY() - ball.getCenterY()));
                double nsint = (ball.getCenterY() - players2[0].getCenterY()) / neardist;
                double ncost = (ball.getCenterX() - players2[0].getCenterX()) / neardist;
                for (int i = 0; i < players2.length; i++) {
                    Players p = players2[i];
                    delx = ball.getCenterX() - p.getCenterX();
                    dely = ball.getCenterY() - p.getCenterY();
                    dist = sqrt(delx * delx + dely * dely);
                    sint = dely / dist;
                    cost = delx / dist;
                    if (dist < neardist) {
                        nearest = i;
                        neardist = dist;
                        nsint = sint;
                        ncost = cost;
                    }
                }
                players2[nearest].spy = 32 * nsint * 10;
                players2[nearest].spx = 32 * ncost * 10;
                turn++;
            }
        }
    }


    public void mousemove(MouseEvent mouseEvent) {
        System.out.printf("x:%f ,  y:%f\n",mouseEvent.getX(),mouseEvent.getY());
    }
}

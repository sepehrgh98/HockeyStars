package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {


    public ImageView Imageview;
    public AnchorPane anchor;


    public Button Aboutbutton;
    public Button Settingbutton;
    public Button Startbutton;
    public Button Exitbutton;

    public Label labletitle;
    @FXML
    SimpleStringProperty smusic;
    SimpleBooleanProperty svolumvalue;

    MediaPlayer m1;
    MediaPlayer m2;

//    public void Aboutaction(ActionEvent e) throws IOException {
//
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("k"));
//        Stage stage1=new Stage();
//        stage1.setScene(new Scene(loader.load()));
//        stage1.show();
//    }


//    public void Settingaction(ActionEvent e) throws IOException {
//
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("k"));
//        Stage stage1=new Stage();
//        stage1.setScene(new Scene(loader.load()));
//        stage1.show();
//    }


//    public void Startaction(ActionEvent e) throws IOException {
//
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("k"));
//        Stage stage1=new Stage();
//        stage1.setScene(new Scene(loader.load()));
//        stage1.show();
//    }


//    public void Exitaction(ActionEvent e) throws IOException {
//
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("k"));
//        Stage stage1=new Stage();
//        stage1.setScene(new Scene(loader.load()));
//        stage1.show();
//    }

    @FXML
    public void initialize()throws IOException {
smusic=new SimpleStringProperty("m");
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("Setting.fxml"));
        Media musicFile1 = new Media(getClass().getResource("Res/music/med.mp3").toString());
        Media musicFile2 = new Media(getClass().getResource("Res/music/med2.mp3").toString());
        m1=new MediaPlayer(musicFile1);
        m2=new MediaPlayer(musicFile2);
       // settingConroller scontrol= loader.getController();
        /*scontrol.music.addListener((observable, oldValue, newValue) -> {
        scontrol.music.set(newValue);
            if (scontrol.music.get()==true)
                 m1.play();
            else if (scontrol.music.get()==false)
                m2.play();
        });*/
       /* scontrol.volumvalue.addListener((observable, oldValue, newValue) ->{
            scontrol.volumvalue.setValue(newValue);
            m1.setVolume(scontrol.volumvalue.getValue());
        });*/

      // smusic.bind(scontrol.music);
//       smusic.addListener((observable, oldValue, newValue) -> {
//           if (smusic.get()==true)
//               m1.play();
//           else if (smusic.get()==false)
//               m2.play();
//       });
        Aboutbutton.setOnAction(e ->{
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("About.fxml"));
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader1.load()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.show();
        });

        Settingbutton.setOnAction(e ->{
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Setting.fxml"));

            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader2.load()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

           settingConroller control= loader2.getController();
            System.out.println("control.mjusic="+ control.music);
          smusic.bindBidirectional(control.music);
            stage.show();
        });


        Startbutton.setOnAction(e ->{
            FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Select.fxml"));
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(loader3.load()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.show();
        });

       smusic.addListener((observable, oldValue, newValue) -> {
           if (smusic.getValue().equals("m1"))
               m1.play();
            if (smusic.getValue().equals("m2"))
               m2.play();
            if (smusic.getValue().equals("nm"))
                m2.stop();
                m1.stop();
       });



    }


}


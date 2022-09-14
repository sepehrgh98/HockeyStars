package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class settingConroller {
    MediaPlayer mediaPlayer ;
    MediaPlayer mediaPlayer2 ;
    @FXML
     SimpleStringProperty music;
    public SimpleDoubleProperty volumvalue;

    @FXML
    private Button play;
    @FXML
    private Slider volumeslider;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private Button Playbtn;
    @FXML
    private Button Stopbtn;


    @FXML
    void initialize(){
        music = new SimpleStringProperty("m");
        volumvalue=new SimpleDoubleProperty();
        Media musicFile = new Media(getClass().getResource("Res/music/med.mp3").toString());
        Media musicFile2 = new Media(getClass().getResource("Res/music/med2.mp3").toString());
        mediaPlayer = new MediaPlayer(musicFile );
        mediaPlayer2 = new MediaPlayer(musicFile2 );


        volumeslider.setValue(mediaPlayer.getVolume()*60);
        volumeslider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                volumvalue.setValue(volumeslider.getValue()/60);
                //mediaPlayer.setVolume(volumeslider.getValue()/60);
            }
        });
        volumeslider.setValue(mediaPlayer2.getVolume()*60);
        volumeslider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
               // mediaPlayer2.setVolume(volumeslider.getValue()/60);
                volumvalue.setValue(volumeslider.getValue()/60);
            }
        });


        Image imageDecline = new Image(getClass().getResourceAsStream("Res/img/play.png"));
        Image imageDecline2 = new Image(getClass().getResourceAsStream("Res/img/pause.png"));
        Playbtn.setGraphic(new ImageView(imageDecline));
        Stopbtn.setGraphic(new ImageView(imageDecline2));


        Playbtn.setOnAction(event -> {
            music.setValue("m1");
            if (rb1.isSelected())
                music.setValue("m1");

               // mediaPlayer.play();
           else if (rb2.isSelected())
                music.setValue("m2");
                //mediaPlayer2.play();
        });


        Stopbtn.setOnAction(event -> {
//            mediaPlayer.stop();
//            mediaPlayer2.stop();
                music.setValue("nm");


        });

    }

}

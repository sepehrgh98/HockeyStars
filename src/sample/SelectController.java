package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectController {

    public Button Versusbtn;
    public Button Computerplaybtn;


    public void VersusOnaction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("5or6.fxml"));
        Stage st=new Stage();
        st.setScene(new Scene(loader.load()));
        st.show();
    }

    public void ComputerplayOnaction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader2 =new FXMLLoader(getClass().getResource("Start2.fxml"));
        Stage st=new Stage();
        st.setScene(new Scene(loader2.load()));
        st.show();
    }
    }


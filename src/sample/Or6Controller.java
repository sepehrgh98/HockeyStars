package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Or6Controller {
    public void FiveBulls(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader =new FXMLLoader(getClass().getResource("Start.fxml"));
        Stage st=new Stage();
        st.setScene(new Scene(loader.load()));
        st.show();
    }
    public void SixBulls(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("sixplay.fxml"));
        Stage st=new Stage();
        st.setScene(new Scene(loader.load()));
        st.show();
    }
}

package fi.jyu.ohj2.jseppane.kirjasto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Paneelit.setStage(stage);
        Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
    }

}

package com.ImageViewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * A simple image viewing/editing program.
 *
 * @author Dean Lonergan
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        VBox mainVB = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("main-view.fxml")));
        Scene main = new Scene(mainVB, 600, 350);
        stage.setTitle("Image Viewer");
        stage.setScene(main);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
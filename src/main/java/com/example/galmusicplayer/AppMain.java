package com.example.galmusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppMain.class.getResource("player-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 300);
        //stage.setResizable(false);
        stage.setTitle("Gal Music Player");
        PlayerController controller = fxmlLoader.getController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            controller.handleKeyPressed(keyEvent);
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
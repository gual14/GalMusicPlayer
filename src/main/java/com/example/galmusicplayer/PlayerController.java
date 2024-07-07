package com.example.galmusicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Label songLabel;

    @FXML
    private Button playPauseBtn, resetBtn;

    @FXML
    private ComboBox<String> speedComboBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private ProgressBar songProgressBar;

    private boolean isPlaying;
    private File currentFile;
    private int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
    private Timer timer;
    private TimerTask task;

    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadMedia() {

    }

    public void unloadMedia() {

    }

    public void showAbout() {

    }

    public void playPauseMedia() {

        if(media != null) {
            if (isPlaying) {
                isPlaying = false;
                mediaPlayer.pause();
            }
            else {
                isPlaying = true;
                mediaPlayer.play();
            }

        }
    }

    public void resetMedia() {

    }

    public void changeSpeed(ActionEvent event) {

    }

    public void beginTimer() {

    }

    public void cancelTimer() {

    }



}
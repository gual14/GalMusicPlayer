package com.example.galmusicplayer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;


import java.io.File;
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
    private MenuItem loadSongBtn, unloadSongBtn, aboutBtn;

    @FXML
    private ComboBox<String> speedComboBox;

    @FXML
    private Slider volumeSlider;

    @FXML
    private ProgressBar songProgressBar;

    private boolean isPlaying;
    private boolean isLoaded = false;
    private final int[] speeds = {25, 50, 75, 100, 125, 150, 175, 200};
    private Timer timer;

    private Media media;
    private MediaPlayer mediaPlayer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int speed : speeds) {
            speedComboBox.getItems().add(speed + "%");
        }
        pane.setOnKeyPressed(this::handleKeyPressed);
        speedComboBox.setOnAction(this::changeSpeed);
        volumeSlider.valueProperty().addListener((observableValue, number, t1) -> {
            if (isLoaded) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.KP_LEFT) {
            resetMedia();
        }
    }

    public void loadMedia() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 Files", "*.mp3")
                ,new FileChooser.ExtensionFilter("WAV Files", "*.wav")
        );
        File currentFile = fileChooser.showOpenDialog(null);
        unloadMedia();
        isLoaded = true;
        media = new Media(currentFile.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(currentFile.getName());
    }

    public void unloadMedia() {
        if (!isLoaded) {
            return;
        }
        cancelTimer();
        mediaPlayer.pause();
        mediaPlayer = null;
        media = null;
        songLabel.setText("Load A Song First");
        isLoaded = false;
        songProgressBar.setProgress(0);
    }

    public void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "GalMusicPlayer About");
        alert.show();
    }

    public void playPauseMedia() {

        if(media != null) {
            if (isPlaying) {
                cancelTimer();
                mediaPlayer.pause();
            }
            else {
                beginTimer();
                mediaPlayer.play();
            }

        }
    }

    public void resetMedia() {
        if ( media != null) {
            songProgressBar.setProgress(0);
            mediaPlayer.seek(Duration.seconds(0));
        }
    }

    public void changeSpeed(ActionEvent event) {
        if ( media == null) {
            return;
        }
        if (speedComboBox.getValue() == null) {
            mediaPlayer.setRate(1);
        }
        mediaPlayer.setRate(Integer.parseInt(speedComboBox.getValue().substring(0, speedComboBox.getValue().length()-1)) * 0.01);
    }

    public void beginTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                isPlaying = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgressBar.setProgress(current / end);
            }
        };
        timer.scheduleAtFixedRate(task, 1000,250);
    }

    public void cancelTimer() {
        isPlaying = false;
        timer.cancel();
    }



}
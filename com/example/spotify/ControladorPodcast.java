package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Playlist;
import model.Podcast;

public class ControladorPodcast {
    @FXML
    private Label titulopod;

    public void setData(Podcast podcast){
        titulopod.setText(podcast.getTituloPod());
    }
}

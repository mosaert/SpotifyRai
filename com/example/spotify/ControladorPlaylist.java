package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Playlist;

public class ControladorPlaylist {
    @FXML
    private Label nombreplay;

    public void setData(Playlist playlist){
        nombreplay.setText(playlist.getNombrePlaylist());
    }
}

package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.AlbumClass;

public class ControladorAlbum {
    @FXML
    private ImageView portada;

    @FXML
    private Label titulo;

    @FXML
    private Label artista;

    public void setData(AlbumClass albumClass){
        titulo.setText(albumClass.getNombreAlbum());
        artista.setText(albumClass.getArtista());
    }
}

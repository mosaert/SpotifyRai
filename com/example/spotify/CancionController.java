package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Cancion;


public class CancionController {
    @FXML
    private ImageView portada;

    @FXML
    private Label titulo;

    @FXML
    private Label artista;

    public void setData(Cancion cancion){
        titulo.setText(cancion.getNombre());
        artista.setText(cancion.getArtista());
    }
}

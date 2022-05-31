package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Artista;

public class ArtistaController {
    @FXML
    private Label artistaNombre;

    public void setData(Artista artista){
        artistaNombre.setText(artista.getNombreArtista());
    }

}

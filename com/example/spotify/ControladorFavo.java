package com.example.spotify;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Favorita;
import model.Playlist;

public class ControladorFavo {
    @FXML
    private Label titufavo;
    @FXML
    private Label nomfavo;

    public void setData(Favorita favorita){
        titufavo.setText(favorita.getTitulofav());
        nomfavo.setText(favorita.getArtistafav());
    }
}

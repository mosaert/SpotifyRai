package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void cambioAinicio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioAalbum(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("album.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioAcancion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("canciones.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioAartista(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("artista.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioApodcast(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("podcast.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cambioAfavoritas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("favoritas.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void cambioAplaylist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

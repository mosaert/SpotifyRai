package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Playlist;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PlaylistController implements Initializable {
    @FXML
    private SplitMenuButton nombreUsuario;
    Usuario usuario=new Usuario();

    @FXML
    private VBox PlaylistContainer;

    @FXML
    private Button botonFavorito;
    @FXML
    private ImageView favorito;
    public boolean Favorito=false;
    //Image favoriton=new Image("com/example/spotify/imagenes/icons8-me-gusta-48.png");
    //Image favoritoff=new Image("com/example/spotify/imagenes/icons8-me-gusta-48.png");

    @FXML
    private Label reproTitulo;
    @FXML
    private Label reproArtista;
    @FXML
    private ImageView reproPortada;

    Playlist playlist=new Playlist();
    List<Playlist> listaPlaylist;
    private List<Playlist>getListaPlaylist() throws SQLException, ClassNotFoundException {
        List<Playlist>list=new ArrayList<>();
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        String sql="SELECT titulo, fecha_creacion FROM spotify.playlist where usuario_id =" + '"' + usuario.getId() + '"';
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String nombreplaylist=resultSet.getString("titulo");
            Playlist play=new Playlist();
            play.setNombrePlaylist(nombreplaylist);
            list.add(play);
        }
        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Esto es para poner el nombre del usuario en el SplitMenu
            nombreUsuario.setText(LoginController.usuarioVentana);// Recoge el nombre del usuario
            DatabaseConnection con=new DatabaseConnection();
            Connection connection=con.getConnection();
            String sql="select id from usuario where username = " + '"' + nombreUsuario.getText() + '"';
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                usuario.setId(resultSet.getInt("id"));
            }
            listaPlaylist=new ArrayList<>(getListaPlaylist());
            for (Playlist playlist1:listaPlaylist){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("playlistControl.fxml"));
                BorderPane borderPane=fxmlLoader.load();
                ControladorPlaylist controladorPlaylist=fxmlLoader.getController();
                controladorPlaylist.setData(playlist1);
                PlaylistContainer.getChildren().add(borderPane);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
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
    public void cambioAcancion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("canciones.fxml"));
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
    public void cambioAartistas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("artista.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

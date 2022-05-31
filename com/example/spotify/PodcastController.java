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
import model.Podcast;
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

public class PodcastController implements Initializable {
    @FXML
    private SplitMenuButton nombreUsuario;
    Usuario usuario=new Usuario();

    @FXML
    private HBox podcastContainer;
    @FXML
    private HBox seguidosContainer;
    @FXML
    private HBox capitulosContainer;

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

    Podcast podcast=new Podcast();
    List<Podcast>listapodcast;
    private List<Podcast> getListapodcast() throws SQLException, ClassNotFoundException{
        List<Podcast>list=new ArrayList<>();
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        String sql="SELECT titulo FROM spotify.podcast";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while(resultSet.next()){
            String titulo=resultSet.getString("titulo");
            Podcast pod=new Podcast();
            pod.setTituloPod(titulo);
            list.add(pod);
        }
        return list;
    }
    List<Podcast>podcastSeguidos;
    private List<Podcast>getPodcastSeguidos() throws SQLException, ClassNotFoundException{
        List<Podcast>list=new ArrayList<>();
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        String sql="select podcast.titulo\n" +
                "from podcast\n" +
                "inner join spotify.podcast_usuario on podcast_usuario.podcast_id=podcast.id\n" +
                "inner join spotify.usuario on usuario.id=podcast_usuario.usuario_id\n" +
                "where usuario.id="+usuario.getId()+"";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String titulo=resultSet.getString("titulo");
            Podcast pod1=new Podcast();
            pod1.setTituloPod(titulo);
            list.add(pod1);
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
            listapodcast=new ArrayList<>(getListapodcast());
            for (Podcast podcas:listapodcast){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("podcastCaja.fxml"));
                VBox vBox=fxmlLoader.load();
                ControladorPodcast controladorPodcast=fxmlLoader.getController();
                controladorPodcast.setData(podcas);
                podcastContainer.getChildren().add(vBox);
            }
            podcastSeguidos=new ArrayList<>(getPodcastSeguidos());
            for (Podcast podcast1:podcastSeguidos){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("podcastCaja.fxml"));
                VBox vBox=fxmlLoader.load();
                ControladorPodcast controladorPodcast=fxmlLoader.getController();
                controladorPodcast.setData(podcast1);
                seguidosContainer.getChildren().add(vBox);
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
    public void cambioAplaylist(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("playlist.fxml"));
        stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

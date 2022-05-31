package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cancion;
import model.Usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InicioController implements Initializable {
    @FXML
    private SplitMenuButton nombreUsuario;
    Usuario usuario=new Usuario();

    @FXML
    private HBox recomendaciones;

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
    Cancion cancion=new Cancion();

    List<Cancion> Recomendadas;

    private List<Cancion> getRecomendadas() throws SQLException, ClassNotFoundException {
        List<Cancion>lista=new ArrayList<>();
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        String sql="select cancion.id, cancion.titulo, artista.nombre\n" +
                "from spotify.guarda_cancion\n" +
                "inner join spotify.cancion on cancion.id=guarda_cancion.cancion_id\n" +
                "inner join spotify.usuario on usuario.id=guarda_cancion.usuario_id\n" +
                "inner join spotify.album on album.id=cancion.album_id\n" +
                "inner join spotify.artista on artista.id=album.artista_id\n" +
                "where cancion_id not in (\n" +
                "\tselect cancion_id\n" +
                "    from spotify.guarda_cancion\n" +
                "    where usuario_id=" + usuario.getId()+ ")\n" +
                "group by titulo limit 13;";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            String nombre=resultSet.getString("cancion.titulo");
            String artista=resultSet.getString("artista.nombre");
            Cancion cancion=new Cancion();
            cancion.setNombre(nombre);
            cancion.setArtista(artista);
            lista.add(cancion);
        }
        return lista;
    }
    public void agregarFavorito(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        PreparedStatement preparedStatement=null;
        String query = "INSERT INTO spotify.guarda_cancion (usuario_id,cancion_id)VALUES ( " + '"' + usuario.getId() + '"' + "," + '"' + cancion.getId() + '"' + ")";
        preparedStatement=connection.prepareCall(query);
        int status=preparedStatement.executeUpdate();
        if (status>0){
            System.out.println("Cancion guardada :)");
        }
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
            // Coloca las canciones en el ScrollPane ordenadas por orden alfabético :P
            Recomendadas =new ArrayList<>(getRecomendadas());
            for (Cancion canciones : Recomendadas) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("cancion.fxml"));
                VBox vBox = fxmlLoader.load();
                vBox.onMouseClickedProperty().set(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        // Inicia el evento que coloca las canciones en el "reproductor"
                        botonFavorito.setVisible(true);
                        Favorito = false;
                        reproTitulo.setText(canciones.getNombre());
                        reproArtista.setText(canciones.getArtista());
                        try {
                            // Guarda el Id de la cancion para el metodo AgregaFavoritos
                            DatabaseConnection con=new DatabaseConnection();
                            Connection connection=con.getConnection();
                            String sql="select id from cancion where titulo =" + '"' + reproTitulo.getText() + '"';
                            PreparedStatement preparedStatement=connection.prepareStatement(sql);
                            ResultSet resultSet=preparedStatement.executeQuery();
                            while (resultSet.next()){
                                cancion.setId(resultSet.getInt("id"));
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
                CancionController cancionController = fxmlLoader.getController();
                cancionController.setData(canciones);
                recomendaciones.getChildren().add(vBox);
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
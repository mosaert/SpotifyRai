package com.example.spotify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static String usuarioVentana;
    @FXML
    public TextField usuario;
    @FXML
    public PasswordField password;
    @FXML
    public void loginButton(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        DatabaseConnection con=new DatabaseConnection();
        Connection connection=con.getConnection();
        String sql="select username, password from usuario where username= '"+usuario.getText()+"'";
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        if (!usuario.getText().equals("") && !password.getText().equals("")) {
            try {
                if (password.getText().equals(resultSet.getString("password"))) {
                    usuarioVentana=usuario.getText();
                    Parent root = FXMLLoader.load(getClass().getResource("inicio.fxml"));
                    Scene inicio = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.hide();
                    stage.setScene(inicio);
                    stage.show();

                }
            }catch (SQLException exception){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Usuario y/o contraseña incorrectos.");
                alert.showAndWait();
            }
        } else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Introduce usuario y contraseña");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }
}

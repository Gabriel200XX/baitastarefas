package login;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import database.dao.UserDAO;

public class Controller {
    @FXML
    private TextField loginUsuario;
    @FXML
    private TextField loginSenha;
    @FXML
    private Button loginEnviar;

    private UserDAO userDAO;

    public Controller() {
        this.userDAO = new UserDAO();
    }

    public void cadastrarAction() {
        System.out.println("alô");
    }
    public void enviarAction() throws Exception {
        System.out.println(loginUsuario.getText());
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        loginEnviar.getScene().getWindow().hide();
        Stage homeStage = new Stage();
        homeStage.setTitle("Home - Baitas Tarefas");
        homeStage.setScene(new Scene(root, 800, 600));
        homeStage.show();
    }
}

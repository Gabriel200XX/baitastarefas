package login;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import database.dao.UserDAO;

import java.io.IOException;

public class Controller {
    @FXML
    private TextField loginUsuario;
    @FXML
    private TextField loginSenha;
    @FXML
    private Button loginEnviar;
    @FXML
    private Button loginCadastrar;

    private UserDAO userDAO;

    public Controller() {
        this.userDAO = new UserDAO();
    }

    public void cadastrarAction() throws IOException {
        System.out.println(loginUsuario.getText());
        Parent root = FXMLLoader.load(getClass().getResource("../signin/signin.fxml"));
        loginCadastrar.getScene().getWindow().hide();
        Stage homeStage = new Stage();
        homeStage.setTitle("Cadastre-se - Baitas Tarefas");
        homeStage.setScene(new Scene(root, 294, 294));
        homeStage.show();
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

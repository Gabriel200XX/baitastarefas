package signin;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import database.dao.UserDAO;
import database.models.User;

import javax.swing.*;

public class Controller {
    @FXML
    private TextField signinNome;
    @FXML
    private TextField signinUsuario;
    @FXML
    private TextField signinSenha;
    @FXML
    private Button signinLogin;

    private UserDAO userDAO;

    public Controller() {
        this.userDAO = new UserDAO();
    }

    public void loginAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        signinLogin.getScene().getWindow().hide();
        Stage homeStage = new Stage();
        homeStage.setTitle("Login - Baitas Tarefas");
        homeStage.setScene(new Scene(root, 294, 251));
        homeStage.show();
    }
    public void enviarAction() {
        User user = new User();
        System.out.println(signinNome.getText());
        System.out.println(signinUsuario.getText());
        System.out.println(signinSenha.getText());

        user.setName(signinNome.getText());
        user.setUser(signinUsuario.getText());
        user.setPassword(signinSenha.getText());

        if (this.userDAO.getByUser(signinUsuario.getText()) == null) {
            this.userDAO.insert(user);
        } else {
            System.out.println("OLá");
            JOptionPane.showMessageDialog(null, "Usuário já existente!", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }
}

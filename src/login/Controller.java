package login;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import database.dao.UserDAO;

import javax.swing.*;
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
        Parent root = FXMLLoader.load(getClass().getResource("../signin/signin.fxml"));
        loginCadastrar.getScene().getWindow().hide();
        Stage cadastrarStage = new Stage();
        cadastrarStage.setTitle("Cadastre-se - Baitas Tarefas");
        cadastrarStage.setScene(new Scene(root, 294, 294));
        cadastrarStage.show();
    }
    public void enviarAction() throws Exception {
        if (loginUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Usuário!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (loginUsuario.getText().length() > 255) {
            JOptionPane.showMessageDialog(null, "O campo Usuário não pode ser maior que 255!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (loginSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Senha!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (loginSenha.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "O campo Senha não pode ser maior que 20!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            if (this.userDAO.autenticaUser(loginUsuario.getText(), loginSenha.getText())) {
                Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
                loginEnviar.getScene().getWindow().hide();
                Stage homeStage = new Stage();
                homeStage.setTitle("Home - Baitas Tarefas");
                homeStage.setScene(new Scene(root, 800, 600));
                homeStage.show();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

package login;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import database.dao.UserDAO;
import utils.AbreTela;

import javax.swing.*;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField loginUsuario;
    @FXML
    private TextField loginSenha;
    @FXML
    private Button loginEnviar;
    @FXML
    private Button loginCadastrar;

    private UserDAO userDAO;
    private AbreTela abreTela;

    public LoginController() {
        this.userDAO = new UserDAO();
        this.abreTela = new AbreTela();
    }

    public void cadastrarAction() throws IOException {
        loginCadastrar.getScene().getWindow().hide();
        this.abreTela.abreSignin();
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
                loginEnviar.getScene().getWindow().hide();
                this.abreTela.abreHome();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

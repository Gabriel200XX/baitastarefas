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
import utils.AbreTela;

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
    @FXML
    private Button signinEnviar;

    private UserDAO userDAO;
    private AbreTela abreTela;

    public Controller() {
        this.userDAO = new UserDAO();
        this.abreTela = new AbreTela();
    }

    public void loginAction() throws IOException {
        signinLogin.getScene().getWindow().hide();
        this.abreTela.abreLogin();
    }
    public void enviarAction() throws IOException {
        User user = new User();

        if (signinNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Nome!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (signinNome.getText().length() > 255) {
            JOptionPane.showMessageDialog(null, "O campo Nome não pode ser maior que 255!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (signinUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo Usuário!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (signinUsuario.getText().length() > 255) {
            JOptionPane.showMessageDialog(null, "O campo Usuário não pode ser maior que 255!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (signinSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha o campo Senha!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else if (signinUsuario.getText().length() > 20) {
            JOptionPane.showMessageDialog(null, "O campo Senha não pode ser maior que 20!", "Atenção", JOptionPane.WARNING_MESSAGE);
        } else {
            user.setName(signinNome.getText());
            user.setUser(signinUsuario.getText());
            user.setPassword(signinSenha.getText());

            if (this.userDAO.getByUser(signinUsuario.getText()) == null) {
                this.userDAO.insert(user);
                signinEnviar.getScene().getWindow().hide();
                this.abreTela.abreHome();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário já existente!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }


    }
}

package task;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField loginUsuario;
    @FXML
    private TextField loginSenha;

    public void cadastrarAction() {
        System.out.println("alô");
    }
    public void enviarAction() {
        System.out.println(loginUsuario.getText());
    }
}

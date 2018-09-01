package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AbreTela {
    public void abreHome() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        Stage homeStage = new Stage();
        homeStage.setTitle("Home - Baitas Tarefas");
        homeStage.setScene(new Scene(root, 800, 600));
        homeStage.show();
    }

    public void abreTask() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../task/task.fxml"));
        Stage taskStage = new Stage();
        taskStage.setTitle("Task - Baitas Tarefas");
        taskStage.setScene(new Scene(root, 800, 600));
        taskStage.show();
    }

    public void abreSignin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../signin/signin.fxml"));
        Stage cadastrarStage = new Stage();
        cadastrarStage.setTitle("Cadastre-se - Baitas Tarefas");
        cadastrarStage.setScene(new Scene(root, 294, 294));
        cadastrarStage.show();
    }

    public void abreLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));
        Stage loginStage = new Stage();
        loginStage.setTitle("Login - Baitas Tarefas");
        loginStage.setScene(new Scene(root, 294, 251));
        loginStage.show();
    }
}

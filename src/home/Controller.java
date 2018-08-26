package home;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller {
    @FXML
    private Button homeTask;

    public void taskAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../task/task.fxml"));
        homeTask.getScene().getWindow().hide();
        Stage taskStage = new Stage();
        taskStage.setTitle("Task - Baitas Tarefas");
        taskStage.setScene(new Scene(root, 800, 600));
        taskStage.show();
    }
}

package task;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import database.dao.SessionDAO;
import database.dao.TaskDAO;
import database.models.Task;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Controller {
    @FXML
    private TextField taskTarefa;
    @FXML
    private TextField taskPrevisao;
    @FXML
    private Hyperlink taskVoltar;
    @FXML
    private Button taskEnviar;

    private TaskDAO taskDAO;
    private SessionDAO sessionDAO;

    public Controller() {
        this.taskDAO = new TaskDAO();
        this.sessionDAO = new SessionDAO();
    }

    public void homeAction() {

    }

    public void enviarAction() throws ParseException, IOException {
        Task task = new Task();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        task.setIdUser(sessionDAO.getIdUserSession());
        task.setName(taskTarefa.getText());
        task.setPrevisionFinish(new Date(format.parse(taskPrevisao.getText()).getTime()));
        this.taskDAO.insert(task);
        Parent root = FXMLLoader.load(getClass().getResource("../home/home.fxml"));
        taskEnviar.getScene().getWindow().hide();
        Stage homeStage = new Stage();
        homeStage.setTitle("Home - Baitas Tarefas");
        homeStage.setScene(new Scene(root, 800, 600));
        homeStage.show();
    }
}

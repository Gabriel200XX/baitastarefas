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
import utils.AbreTela;

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
    private AbreTela abreTela;

    public Controller() {
        this.taskDAO = new TaskDAO();
        this.sessionDAO = new SessionDAO();
        this.abreTela = new AbreTela();
    }

    public void homeAction() throws IOException {
        taskVoltar.getScene().getWindow().hide();
        this.abreTela.abreHome();
    }

    public void enviarAction() throws ParseException, IOException {
        Task task = new Task();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        task.setIdUser(sessionDAO.getIdUserSession());
        task.setName(taskTarefa.getText());
        task.setPrevisionFinish(new Date(format.parse(taskPrevisao.getText()).getTime()));
        this.taskDAO.insert(task);
        taskEnviar.getScene().getWindow().hide();
        this.abreTela.abreHome();
    }
}

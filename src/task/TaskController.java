package task;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import database.dao.SessionDAO;
import database.dao.TaskDAO;
import database.models.Task;
import utils.AbreTela;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import home.HomeController;

public class TaskController implements Initializable {
    @FXML
    private TextField taskTarefa;
    @FXML
    private DatePicker taskPrevisao;
    @FXML
    private Hyperlink taskVoltar;
    @FXML
    private Button taskEnviar;

    private TaskDAO taskDAO;
    private SessionDAO sessionDAO;
    private AbreTela abreTela;
    private long taskId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.taskDAO = new TaskDAO();
        this.sessionDAO = new SessionDAO();
        this.abreTela = new AbreTela();

        if (HomeController.taskId != 0) {
            Task task = this.taskDAO.getTaskById(HomeController.taskId);
            this.taskTarefa.setText(task.getName());
            String[] data = task.getPrevisionFinish().toString().split("-");
            this.taskPrevisao.setValue(LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            taskId = HomeController.taskId;
        } else {
            taskId = HomeController.taskId;
        }
    }

    public void homeAction() throws IOException {
        taskVoltar.getScene().getWindow().hide();
        this.abreTela.abreHome();
    }

    public void enviarAction() throws ParseException, IOException {
        Task task = new Task();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        task.setIdUser(sessionDAO.getIdUserSession());
        task.setName(taskTarefa.getText());
        task.setPrevisionFinish(new java.sql.Date(format.parse(taskPrevisao.getValue().plusDays(1).toString()).getTime()));
        if (this.taskId > 0) {
            task.setId(this.taskId);
            this.taskDAO.update(task);
        } else {
            this.taskDAO.insert(task);
        }
        taskEnviar.getScene().getWindow().hide();
        this.abreTela.abreHome();
    }
}

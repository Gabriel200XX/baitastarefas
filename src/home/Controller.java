package home;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.dao.SessionDAO;
import database.models.Task;
import database.dao.TaskDAO;
import utils.AbreTela;

public class Controller implements Initializable {
    @FXML
    private Button homeTask;
    @FXML
    private TableView homeTable;

    private TaskDAO taskDAO;
    private SessionDAO sessionDAO;
    private AbreTela abreTela;

    private final ObservableList<Task> data =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.taskDAO = new TaskDAO();
        this.sessionDAO = new SessionDAO();
        this.abreTela = new AbreTela();
        List<Task> task = this.taskDAO.getTaskByIdUser(sessionDAO.getIdUserSession());
        data.addAll(task);

        homeTable.setItems(data);
    }

    public void taskAction() throws IOException {
        homeTask.getScene().getWindow().hide();
        this.abreTela.abreTask();
    }
}

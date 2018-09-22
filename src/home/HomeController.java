package home;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.dao.SessionDAO;
import database.models.Task;
import database.dao.TaskDAO;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import utils.AbreTela;

public class HomeController implements Initializable {
    @FXML
    private Button homeLogout;
    @FXML
    private Button homeTask;
    @FXML
    private TableView homeTable;

    public static long taskId = 0;
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

        buscaTasks();

        FileInputStream inputUCheck = null;
        FileInputStream inputCheck = null;

        FileInputStream inputEdit = null;
        FileInputStream inputDelete = null;
        try {
            inputUCheck = new FileInputStream("assets/uncheck.png");
            inputCheck = new FileInputStream("assets/check.png");

            inputEdit = new FileInputStream("assets/edit.png");
            inputDelete = new FileInputStream("assets/garbage.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image imageUCheck = new Image(inputUCheck);
        Image imageCheck = new Image(inputCheck);

        Image imageEdit = new Image(inputEdit);
        Image imageDelete = new Image(inputDelete);

        addButtonToTable(false, "Completar", imageUCheck, imageCheck);
        addButtonToTable(true, "Editar", imageEdit, imageEdit);
        addButtonToTable(true, "Excluir", imageDelete, imageDelete);
    }

    private void addButtonToTable(boolean fimLista, String nome, Image image, Image image2) {
        TableColumn<Task, Task> colBtn = new TableColumn();

        Callback<TableColumn<Task, Task>, TableCell<Task, Task>> cellFactory = new Callback<TableColumn<Task, Task>, TableCell<Task, Task>>() {
            @Override
            public TableCell<Task, Task> call(final TableColumn<Task, Task> param) {
                final TableCell<Task, Task> cell = new TableCell<Task, Task>() {

                    private ImageView imageView = new ImageView(image);
                    private ImageView imageView2 = new ImageView(image2);

                    private Button btn;

                    @Override
                    public void updateItem(Task item, boolean empty) {
                        btn = new Button(null, imageView);
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Task data = getTableView().getItems().get(getIndex());
                            if (nome.equals("Completar") && data.isFinished()) {
                                btn.setGraphic(imageView2);
                            }

                            acaoBotao(data);

                            setGraphic(btn);
                        }
                    }

                    public void acaoBotao(Task data) {
                        btn.setOnAction((ActionEvent event) -> {
                            taskId = data.getId();
                            if (nome.equals("Editar")) {
                                try {
                                    taskAction();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (nome.equals("Excluir")) {
                                excluirTask(taskId);
                            } else {
                                patchTask(!data.isFinished(), taskId);
                            }
                        });
                    }
                };

                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        if (fimLista) {
            homeTable.getColumns().add(colBtn);
        } else {
            homeTable.getColumns().add(0, colBtn);
        }

    }

    public void buscaTasks() {
        data.clear();
        homeTable.setItems(data);
        List<Task> task = this.taskDAO.getTaskByIdUser(sessionDAO.getIdUserSession());
        data.addAll(task);

        homeTable.setItems(data);
    }

    public void taskAction() throws IOException {
        homeTask.getScene().getWindow().hide();
        this.abreTela.abreTask();
        taskId = 0;
    }

    public void patchTask(boolean finish, long id) {
        this.taskDAO.patchFinish(finish, id);
        taskId = 0;
        buscaTasks();
    }

    public void excluirTask(long id) {
        this.taskDAO.deleteById(id);
        taskId = 0;
        buscaTasks();
    }

    public void logoutAction() throws IOException {
        homeLogout.getScene().getWindow().hide();
        this.sessionDAO.logout();
    }
}

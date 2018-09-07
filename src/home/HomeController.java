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
        List<Task> task = this.taskDAO.getTaskByIdUser(sessionDAO.getIdUserSession());
        data.addAll(task);

        addCheckboxToTable();
        homeTable.setItems(data);

        FileInputStream inputEdit = null;
        FileInputStream inputDelete = null;
        try {
            inputEdit = new FileInputStream("assets/edit.png");
            inputDelete = new FileInputStream("assets/garbage.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image imageEdit = new Image(inputEdit);

        Image imageDelete = new Image(inputDelete);

        addButtonToTable("Editar", imageEdit);
        addButtonToTable("Excluir", imageDelete);
    }

    private void addButtonToTable(String nome, Image image) {
        TableColumn<Task, Void> colBtn = new TableColumn();

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<TableColumn<Task, Void>, TableCell<Task, Void>>() {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param) {
                final TableCell<Task, Void> cell = new TableCell<Task, Void>() {

                    ImageView imageView = new ImageView(image);

                    private final Button btn = new Button(null, imageView);

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Task data = getTableView().getItems().get(getIndex());
                            taskId = data.getId();
                            if (nome == "Editar") {
                                try {
                                    taskAction();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    taskAction();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);

        homeTable.getColumns().add(colBtn);

    }

    private void addCheckboxToTable() {
        TableColumn<Task, Void> colCB = new TableColumn();

        Callback<TableColumn<Task, Void>, TableCell<Task, Void>> cellFactory = new Callback<TableColumn<Task, Void>, TableCell<Task, Void>>() {
            @Override
            public TableCell<Task, Void> call(final TableColumn<Task, Void> param) {
                final TableCell<Task, Void> cell = new TableCell<Task, Void>() {

                    private final CheckBox check = new CheckBox();

                    {
                        check.setSelected();
                        check.setOnAction((ActionEvent event) -> {
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(check);
                        }
                    }
                };
                return cell;
            }
        };

        colCB.setCellFactory(cellFactory);

        homeTable.getColumns().add(0, colCB);

    }

    public void taskAction() throws IOException {
        homeTask.getScene().getWindow().hide();
        this.abreTela.abreTask();
        taskId = 0;
    }
}

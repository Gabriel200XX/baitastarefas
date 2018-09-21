package database.models;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {

    private long id;
    private long idUser;
    private String name;
    private Date previsionFinish;
    private boolean finished;
    private Timestamp createdAt;
    private String previsionFinishFormated;
    private String createdAtFormated;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getPrevisionFinish() {
        return previsionFinish;
    }
    public void setPrevisionFinish(Date previsionFinish) {
        this.previsionFinish = previsionFinish;
        String[] data = previsionFinish.toString().split("-");
        this.setPrevisionFinishFormated(data[2] + "/" + data[1] + "/" + data[0]);
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        String[] dataHora = createdAt.toString().split(" ");
        String[] data = dataHora[0].split("-");
        this.setCreatedAtFormated(data[2] + "/" + data[1] + "/" + data[0] + " " + dataHora[1]);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getPrevisionFinishFormated() {
        return previsionFinishFormated;
    }

    public void setPrevisionFinishFormated(String previsionFinishFormated) {
        this.previsionFinishFormated = previsionFinishFormated;
    }

    public String getCreatedAtFormated() {
        return createdAtFormated;
    }

    public void setCreatedAtFormated(String createdAtFormated) {
        this.createdAtFormated = createdAtFormated;
    }
}

package database.models;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.Date;

public class Task {

    private long id;
    private long idUser;
    private String name;
    private Date previsionFinish;
    private Date createdAt;

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
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

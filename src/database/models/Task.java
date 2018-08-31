package database.models;

import java.sql.Date;

public class Task {

    private long id;
    private long idUser;
    private String name;
    private Date previsionFinish;

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
}

package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.models.Task;

public class TaskDAO {

    public void insert(Task task) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("INSERT INTO Tasks(Users_idUsers, name, previsionFinish) values(?, ?, ?) ");
            p.setLong(1, task.getIdUser());
            p.setString(2, task.getName());
            p.setDate(3, task.getPrevisionFinish());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Task task) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("UPDATE Tasks SET name = ?, previsionFinish = ? WHERE idTasks = ?");
            p.setString(1, task.getName());
            p.setDate(2, task.getPrevisionFinish());
            p.setLong(3, task.getId());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Task task) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("DELETE FROM Tasks WHERE idTasks = ?");
            p.setLong(1, task.getId());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}

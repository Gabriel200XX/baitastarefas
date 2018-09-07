package database.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.models.Task;

public class TaskDAO {

    public void insert(Task task) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("INSERT INTO Tasks(Users_idUsers, name, previsionFinish, finished) values(?, ?, ?, ?) ");
            p.setLong(1, task.getIdUser());
            p.setString(2, task.getName());
            p.setDate(3, task.getPrevisionFinish());
            p.setBoolean(4, false);

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

    public void patchFinish(Task task) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("UPDATE Tasks SET finished = ? WHERE idTasks = ?");
            p.setBoolean(1, task.isFinished());
            p.setLong(1, task.getId());

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

    public Task getTaskById(Long id) {
        Task task = null;
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT name, previsionFinish FROM Tasks WHERE idTasks = ?");
            p.setLong(1, id);

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                task = new Task();

                task.setName(resultado.getString(1));
                task.setPrevisionFinish(resultado.getDate(2));
            }
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return task;
    }

    public List getTaskByIdUser(long id) {
        List<Task> taskList = new ArrayList<Task>();
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT idTasks, name, previsionFinish, createdAt FROM Tasks WHERE Users_idUsers = ?");
            p.setLong(1, id);

            ResultSet resultado = p.executeQuery();

            while (resultado.next()){
                Task task = new Task();

                task.setId(resultado.getLong(1));
                task.setName(resultado.getString(2));
                task.setPrevisionFinish(resultado.getDate(3));
                task.setCreatedAt(resultado.getTimestamp(4));

                taskList.add(task);
            }
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return taskList;
    }

}

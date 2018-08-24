package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.models.User;

public class UserDAO {

    // Insere um Usuário
    public void insert(User user) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("INSERT INTO Users(name, user, password) values(?, ?, ?) ");
            p.setString(1, user.getName());
            p.setString(2, user.getUser());
            p.setString(3, user.getPassword());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Atualiza um Usuário
    public void update(User user) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("UPDATE Users SET name = ?, user = ?, password = ? WHERE idUsers = ?");
            p.setString(1, user.getName());
            p.setString(2, user.getUser());
            p.setString(3, user.getPassword());
            p.setLong(4, user.getId());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Excluir um usuário específico pelo id
    public void deleteById(User user) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("DELETE FROM Users WHERE idUsers = ?");
            p.setLong(1, user.getId());

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Selecionar um usuário específico pelo id
    public User getById(User user) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT * FROM Users WHERE idUsers = ?");
            p.setLong(1, user.getId());

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                return (User) resultado;
            } else {
                return (User) null;
            }
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*// Exclui todos os registros da Tabela
    public void deleteAll() {
        try {
            // Cria a conex�o com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();

            Statement stat = conn.createStatement();
            stat.execute("DELETE FROM Users");
            // Fecha conex�o com o banco de dados
            stat.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }*/

}

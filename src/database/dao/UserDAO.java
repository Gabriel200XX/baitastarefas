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

            PreparedStatement pS =
            conn.prepareStatement("SELECT MAX(idUsers) FROM Users");
            ResultSet resultado = pS.executeQuery();

            if (resultado.next()) {
                this.createSession(resultado.getLong(1));
            }

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
    /*public User getById(long id) {
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
    }*/

    public User getByUser(String username) {
        User user = null;
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT idUsers, name, user, password FROM Users WHERE user LIKE ?");
            p.setString(1, username);

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                user = new User();

                user.setId(resultado.getLong(1));
                user.setName(resultado.getString(2));
                user.setUser(resultado.getString(3));
                user.setPassword(resultado.getString(4));

                this.createSession(user.getId());
            }
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public void createSession(long idUser) {
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("INSERT Session(Users_idUsers) VALUES (?)");
            p.setLong(1, idUser);

            p.execute();
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public boolean autenticaUser(String username, String senha) {
        boolean autentica = false;
        try {
            // Cria a conexão com o banco de dados
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT user, password FROM Users WHERE user LIKE ? AND password LIKE ?");
            p.setString(1, username);
            p.setString(2, senha);

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                autentica = true;
            }
            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return autentica;
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

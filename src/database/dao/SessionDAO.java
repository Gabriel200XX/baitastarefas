package database.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.AbreTela;

public class SessionDAO {

    private AbreTela abreTela;

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

    public long getIdUserSession() {
        long id = 0;
        try {
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT Users_idUsers FROM Session ORDER BY createdAt DESC LIMIT 1");

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                id = resultado.getLong(1);
            }

            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getNameUserSession() {
        String name = "";
        try {
            // Cria a conexão com o banco de dados
            //SELECT * FROM Session ORDER BY createdAt DESC LIMIT 1
            Connection conn = (new ConnectionFactory()).getConnection();
            PreparedStatement p =
                    conn.prepareStatement("SELECT name FROM users WHERE idUsers = (SELECT Users_idUsers FROM Session ORDER BY createdAt DESC LIMIT 1)");

            ResultSet resultado = p.executeQuery();

            if (resultado.next()){
                name = resultado.getString(1);
            }

            p.close();
            conn.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    public void logout() throws IOException {
        this.abreTela = new AbreTela();
        this.abreTela.abreLogin();
    }

}

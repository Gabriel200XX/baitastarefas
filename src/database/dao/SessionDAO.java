package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SessionDAO {


    public long getIdUserSession() {
        long id = 0;
        try {
            // Cria a conexão com o banco de dados
            //SELECT * FROM Session ORDER BY createdAt DESC LIMIT 1
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

}

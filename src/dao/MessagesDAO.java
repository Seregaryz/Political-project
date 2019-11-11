package dao;

import obj.Message;
import support.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessagesDAO {

    public List<Message> getMessages(String debatesId) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        Statement stmnt = conn.createStatement();
        UserDAO userDAO = new UserDAO();
        DebatesDAO debatesDAO = new DebatesDAO();
        String sql = "SELECT * FROM messages WHERE debates_id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, Integer.parseInt(debatesId));
            ResultSet rs = ps.executeQuery();
            List<Message> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Message(rs.getString("text"),
                        new Date(Long.parseLong(rs.getString("date"))), userDAO.getSpecUserFromId(rs.getInt("creator_id") + ""),
                        debatesDAO.getSpecDebates(rs.getInt("second_debater_id") + "")));
            }
            return result;
        }
    }
}

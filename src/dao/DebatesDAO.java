package dao;

import obj.Debates;
import obj.News;
import support.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DebatesDAO extends DAO {


    public ArrayList<Debates> getDebates() throws ClassNotFoundException, SQLException {
      Connection conn = DatabaseHelper.getConnection();
        Statement stmnt = conn.createStatement();
        UserDAO userDAO = new UserDAO();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM debates;");
        ArrayList<Debates> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Debates(rs.getInt("id"), rs.getString("topic"),
                    new Date(Long.parseLong(rs.getString("date"))), userDAO.getSpecUserFromId(rs.getInt("creator_id") + ""),
                    userDAO.getSpecUserFromId(rs.getInt("second_debater_id") + "")));
        }
        return result;
    }

    public boolean roomIsAvailable(String idOfDebates) throws ClassNotFoundException, SQLException{
        Connection conn = DatabaseHelper.getConnection();
        String sql = "SELECT * FROM participants WHERE debates_id = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idOfDebates);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count <= 1) {
                return true;
            } else return false;
        }
    }

    public void enrollUser(String idOfUser, String idOfDebates) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        String sql = "INSERT INTO participants VALUES  (?, ?);";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idOfUser);
            ps.setString(2, idOfDebates);
            ResultSet rs = ps.executeQuery();
        }
    }

    public boolean isParticipant(String idOfUser, String idOfDebates) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        String sql = "SELECT * FROM participants WHERE participant_id = ? and debates_id = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idOfUser);
            ps.setString(2, idOfDebates);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public Debates getSpecDebates(String idOfDebates) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        String sql = "SELECT * FROM debates WHERE id = ?;";
        Statement stmnt = conn.createStatement();
        UserDAO userDAO = new UserDAO();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, idOfDebates);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Debates(rs.getInt("id"), rs.getString("topic"),
                        new Date(Long.parseLong(rs.getString("date"))), userDAO.getSpecUserFromId(rs.getInt("creator_id") + ""),
                        userDAO.getSpecUserFromId(rs.getInt("second_debater_id") + ""));
            } else return null;
        }
    }
}

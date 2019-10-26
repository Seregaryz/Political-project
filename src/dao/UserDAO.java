package dao;

import obj.User;

import java.sql.*;

public class UserDAO extends DAO{


    public User validateUser(String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "SELECT * FROM users WHERE email = ? and password = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nickname"),
                        rs.getString("name"), rs.getString("surname"), rs.getString("sex"));
            } else return null;
        }
    }

    public User getSpecUser(String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "SELECT * FROM users WHERE email = ? and password = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nickname"),
                        rs.getString("name"), rs.getString("surname"), rs.getString("sex"));
            } else return null;
        }
    }
}

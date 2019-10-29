package dao;

import obj.User;
import support.ServiceHelper;

import java.sql.*;

public class UserDAO extends DAO{


    public User validateUser(String login, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String codPass = ServiceHelper.md5Custom(password);
        String sql = "SELECT * FROM users WHERE email = ? and password = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, codPass);
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
        String codPass = ServiceHelper.md5Custom(password);
        String sql = "SELECT * FROM users WHERE email = ? and password = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, login);
            ps.setString(2, codPass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nickname"),
                        rs.getString("name"), rs.getString("surname"), rs.getString("sex"));
            } else return null;
        }
    }

    public User getSpecUserFromId(String id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "SELECT * FROM users WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("nickname"),
                        rs.getString("name"), rs.getString("surname"), rs.getString("sex"));
            } else return null;
        }
    }

    public boolean isNicknameFree(String nickname) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "SELECT * FROM users WHERE nickname = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, nickname);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public boolean updateUser(int idOfUser, String pass, String nickname, String name, String surname, String sex) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "UPDATE users SET password = ?, nickname = ?, name = ?, surname = ?, sex = ? WHERE id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, pass);
            ps.setString(2, nickname);
            ps.setString(3, name);
            ps.setString(4, surname);
            ps.setString(5, sex);
            ps.setInt(6, idOfUser);
            ResultSet rs = ps.executeQuery();
        }
        return true;
    }

}

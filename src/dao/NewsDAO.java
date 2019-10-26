package dao;

import obj.News;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class NewsDAO extends DAO {

    public ArrayList<News> getNews() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM public.news;");
        ArrayList<News> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new News(rs.getInt("id"), rs.getString("header"),
                    rs.getString("text"), rs.getString("img"), new Date(Long.parseLong(rs.getString("date")))));
        }
        return result;
    }

    public News getSpecNews(String idOfNews) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        String sql = "SELECT * FROM news WHERE id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idOfNews));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new News(rs.getInt("id"), rs.getString("header"),
                        rs.getString("text"), rs.getString("img"),
                        new Date(Long.parseLong(rs.getString("date"))));
            } else return null;
        }
    }
}

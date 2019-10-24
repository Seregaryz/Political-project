package dao;

import obj.News;

import java.sql.*;
import java.util.ArrayList;

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
                    rs.getString("text"), rs.getString("img"), rs.getDate("date")));
        }
        return result;
    }
}

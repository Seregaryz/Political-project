package dao;

import obj.News;
import support.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsDAO extends DAO {

    public ArrayList<News> getNews() throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM public.news;");
        ArrayList<News> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new News(rs.getInt("id"), rs.getString("header"),
                    rs.getString("text"), "Preview", rs.getString("img"), new Date(Long.parseLong(rs.getString("date")))));
        }
        return result;
    }

    public News getSpecNews(String idOfNews) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        String sql = "SELECT * FROM news WHERE id = ?;";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idOfNews));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new News(rs.getInt("id"), rs.getString("header"),
                        rs.getString("text"), "Preview", rs.getString("img"),
                        new Date(Long.parseLong(rs.getString("date"))));
            } else return null;
        }
    }

    public List<News> getByLikePattern(String pattern) throws SQLException {
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "select * from news where header like ?"
            );
            ps.setString(1, "%" + pattern + "%");
            ResultSet rs = ps.executeQuery();
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                newsList.add(new News(
                        rs.getInt("id"), rs.getString("header"),
                        rs.getString("text"), "Preview",
                        rs.getString("img"), new Date(Long.parseLong(rs.getString("date")))
                ));
            }
            return newsList;
    }
}

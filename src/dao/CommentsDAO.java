package dao;

import obj.Comment;
import support.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CommentsDAO extends DAO{

    public ArrayList<Comment> getComments(String idOfComment) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseHelper.getConnection();
        ArrayList<Comment> result = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        String sql = "SELECT * FROM public.comments WHERE id_news = ?;";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(idOfComment));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Comment(rs.getInt("id_comment"), userDAO.getSpecUserFromId(rs.getInt("id_commentator") + ""),
                        rs.getInt("id_news"), rs.getString("text"), new Date(Long.parseLong(rs.getString("date_comment")))));
            }
            return result;
        }
    }

    public void setComment(Comment comment) throws ClassNotFoundException, SQLException {
       Connection conn = DatabaseHelper.getConnection();
        String sql = "INSERT INTO comments VALUES (?, ?, ?, ?, nextval('comments_id_comment_seq'));";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, comment.getSender().getId());
            ps.setInt(2, comment.getIdPost());
            ps.setString(3, comment.getLongData() + "");
            ps.setString(4, comment.getText());
            ps.execute();
        }
    }
}

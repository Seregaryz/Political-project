package dao;

import obj.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CommentsDAO extends DAO{

    public ArrayList<Comment> getComments() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery("SELECT * FROM public.comments;");
        ArrayList<Comment> result = new ArrayList<>();
        UserDAO userDAO = new UserDAO();
        while (rs.next()) {
            result.add(new Comment(rs.getInt("id_comment"), userDAO.getSpecUserFromId(rs.getInt("id_commentator") + ""),
                    rs.getInt("id_news"), rs.getString("text"), new Date(Long.parseLong(rs.getString("date_comment")))));
        }
        return result;
    }

    public void setComment(Comment comment) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/political_project_db",
                "postgres",
                "postgres"
        );
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

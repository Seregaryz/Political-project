package servlets;

import dao.CommentsDAO;
import dao.NewsDAO;
import dao.UserDAO;
import obj.Comment;
import obj.News;
import obj.User;
import support.FreemarkerHelper;
import support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "news", urlPatterns = {"/post"})
public class PostOfNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            NewsDAO newsDAO = new NewsDAO();
            News news = newsDAO.getSpecNews(req.getParameter("id"));
            root.put("news", news);
            CommentsDAO commentsDAO = new CommentsDAO();
            ArrayList<Comment> comments = commentsDAO.getComments();
            root.put("comments", comments);
            root.put("path", req.getParameter("id"));
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            if (user != null) {
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "news-page-authorized.ftl", root);
            }else if (ServiceHelper.isSavedInCookies(req)) {
                UserDAO userDAO = new UserDAO();
                user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                session.setAttribute("current_user", user);
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "news-page-authorized.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "news-page-non-authorized.ftl", root);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        String postId = req.getParameter("id");
        String text = req.getParameter("text");
        Date date = new Date(System.currentTimeMillis());
        User sender = (User)session.getAttribute("current_user");
        Comment comment = new Comment(1, sender, Integer.parseInt(postId), text, date);
        CommentsDAO commentsDAO = new CommentsDAO();
        User user = (User) session.getAttribute("current_user");
        try {
            commentsDAO.setComment(comment);
            NewsDAO newsDAO = new NewsDAO();
            News news = newsDAO.getSpecNews(postId);
            root.put("news", news);
            ArrayList<Comment> comments = commentsDAO.getComments();
            root.put("comments", comments);
            root.put("path", req.getParameter("id"));
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            if (user != null) {
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "news-page-authorized.ftl", root);
            }else if (ServiceHelper.isSavedInCookies(req)) {
                UserDAO userDAO = new UserDAO();
                user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                session.setAttribute("current_user", user);
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "news-page-authorized.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "news-page-non-authorized.ftl", root);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}

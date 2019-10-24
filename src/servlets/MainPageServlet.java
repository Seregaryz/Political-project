package servlets;

import dao.DAO;
import dao.NewsDAO;
import obj.News;
import obj.User;
import support.Helpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "main_page", urlPatterns = {"/newslist"})
public class MainPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            NewsDAO newsDAO = new NewsDAO();
            ArrayList<News> newsList = newsDAO.getNews();
            root.put("news", newsList);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            if (user != null) {
                root.put("nickname", user.getNickname());
                Helpers.render(req, resp, "main-page-authorized.ftl", root);
            } else {
                Helpers.render(req, resp, "main-page-non-authorized.ftl", root);
            }
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

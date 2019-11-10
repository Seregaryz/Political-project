package servlets;

import dao.NewsDAO;
import dao.UserDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "search", urlPatterns = {"/searchNews"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        String pattern = req.getParameter("pattern");
        if(pattern.length() > 0){
            try {
                NewsDAO newsDAO = new NewsDAO();
                List<News> newsList = newsDAO.getByLikePattern(pattern);
                root.put("newsList", newsList);
                if (user != null) {
                    root.put("nickname", user.getNickname());
                    FreemarkerHelper.render(req, resp, "search-results.ftl", root);
                } else if (ServiceHelper.isSavedInCookies(req)) {
                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                    session.setAttribute("current_user", user);
                    root.put("nickname", user.getNickname());
                    FreemarkerHelper.render(req, resp, "search-results.ftl", root);
                } else FreemarkerHelper.render(req, resp, "search-results-non-author.ftl", root);
            } catch  (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else resp.sendRedirect("/newsList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

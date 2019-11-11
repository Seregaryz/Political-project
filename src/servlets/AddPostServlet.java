package servlets;

import dao.NewsDAO;
import dao.UserDAO;
import obj.News;
import obj.User;
import support.FreemarkerHelper;
import support.ServiceHelper;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "addPost", urlPatterns = {"/addpost"})
@MultipartConfig
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            if (user != null) {
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "add-post.ftl", root);
            } else {
                if (ServiceHelper.isSavedInCookies(req)) {
                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                    session.setAttribute("current_user", user);
                    root.put("nickname", user.getNickname());
                    FreemarkerHelper.render(req, resp, "add-post.ftl", root);
                } else {
                    FreemarkerHelper.render(req, resp, "login.ftl", root);
                }
            }
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String text = req.getParameter("text");
        text = text.replaceAll("\n", "<br>");
        String preview = req.getParameter("preview");
        String header = req.getParameter("header");
        News news = new News(1, header, text, preview, null, null);

        Part p = req.getPart("photo");
        String localdir = "img";
        String pathDir = getServletContext().getRealPath("") + File.separator + localdir;
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] filename_data = p.getSubmittedFileName().split("\\.");
        String filename = Math.random() + "." + filename_data[filename_data.length - 1];
        String fullpath = pathDir + File.separator + filename;
        p.write(fullpath);
        news.setImg("/" + localdir + "/" + filename);
        NewsDAO newsDAO = new NewsDAO();
        resp.sendRedirect("/newsList");
        try {
            newsDAO.setNews(news);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

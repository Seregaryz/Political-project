package servlets;

import dao.UserDAO;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "registration", urlPatterns = {"/registration"})
@MultipartConfig
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            if (user != null) {
                resp.sendRedirect("/newsList");
            } else {
                if (ServiceHelper.isSavedInCookies(req)) {
                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                    session.setAttribute("current_user", user);
                    resp.sendRedirect("/newsList");
                } else {
                    FreemarkerHelper.render(req, resp, "registration.ftl", root);
                }
            }
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("login");
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String sex = req.getParameter("sex");

        UserDAO userDAO = new UserDAO();
        User user = (User) session.getAttribute("current_user");

        if (user != null) {
            resp.sendRedirect("/profile");
        } else {
            try {
                Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
                Matcher emailMatcher = emailPattern.matcher(email);
                Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z]).{8,32}");
                Matcher passMatcher = passwordPattern.matcher(password);

                if(emailMatcher.matches() && passMatcher.matches() && !userDAO.isNicknameFree(nickname)){
                    User u = new User(1, email, password, nickname, name, surname, sex, null);
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
                    u.setPhotoPath("/" + localdir + "/" + filename);
                    userDAO.setUser(u);

                    resp.sendRedirect("/login");
                } else {
                    resp.sendRedirect("/registration");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

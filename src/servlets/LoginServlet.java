package servlets;

import dao.UserDAO;
import obj.User;
import support.FreemarkerHelper;
import support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            if (user != null) {
                response.sendRedirect("/newsList");
            } else {
                if (ServiceHelper.isSavedInCookies(request)) {
                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getSpecUser((String) session.getAttribute("login"), (String) session.getAttribute("password"));
                    session.setAttribute("current_user", user);
                    response.sendRedirect("/newsList");
                } else {
                    FreemarkerHelper.render(request, response, "login.ftl", root);
                }
            }
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserDAO userDAO = new UserDAO();
        String user = (String) session.getAttribute("current_user");

        if (user != null) {
            response.sendRedirect("/profile");
        } else {
            try {
                Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
                Matcher emailMatcher = emailPattern.matcher(email);
                Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z]).{8,32}");
                Matcher passMatcher = passwordPattern.matcher(password);
                System.out.println(ServiceHelper.md5Custom(password));
                if(emailMatcher.matches() && passMatcher.matches()) {
                    User curr = userDAO.validateUser(email, password);
                    if (curr != null) {
                        session.setAttribute("current_user", curr);
                        if(remember!=null) {
                            Cookie emailCookie = new Cookie("login", email);
                            Cookie passCookie = new Cookie("password", ServiceHelper.md5Custom(password));
                            emailCookie.setMaxAge(60);
                            passCookie.setMaxAge(60);
                            response.addCookie(emailCookie);
                            response.addCookie(passCookie);
                        }
                        response.sendRedirect("/newsList");
                    } else {
                        response.sendRedirect("/login");
                    }
                } else  response.sendRedirect("/login");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}

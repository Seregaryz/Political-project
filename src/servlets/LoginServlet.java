package servlets;

import dao.UserDAO;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = request.getSession();
        response.setCharacterEncoding("utf-8");
        User user = (User) session.getAttribute("current_user");
        if (user != null) {
            response.sendRedirect("/news");
        } else {
            response.setContentType("text/html");
            Helpers.render(request, response, "login.ftl", root);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("login");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        String user = (String) session.getAttribute("current_user");
        if (user != null) {
            response.sendRedirect("/profile");
        } else {
            try {
                User curr = userDAO.validateUser(email, password);
                if (curr != null) {
                    session.setAttribute("current_user", curr);
                    response.sendRedirect("/news");
                } else {
                    response.sendRedirect("/login");
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}

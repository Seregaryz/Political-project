package servlets;

import dao.UserDAO;
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
import java.util.Map;

@WebServlet(name = "profile", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            if (user != null) {
                root.put("user", user);
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "profile.ftl", root);
            } else if (ServiceHelper.isSavedInCookies(req)){
                UserDAO userDAO = new UserDAO();
                user = userDAO.getSpecUser((String)session.getAttribute("login"), (String)session.getAttribute("password"));
                session.setAttribute("current_user", user);
                root.put("user", user);
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "profile.ftl", root);
            } else {
                String idOfUser = req.getParameter("id");
                if(idOfUser == null){
                    resp.sendRedirect("/login");
                } else {
                    UserDAO userDAO = new UserDAO();
                    user = userDAO.getSpecUserFromId(idOfUser);
                    root.put("user", user);
                    FreemarkerHelper.render(req, resp, "profile-of-different-user.ftl", root);
                }
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

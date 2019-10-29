package servlets;

import dao.UserDAO;
import obj.User;
import support.FreemarkerHelper;

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

@WebServlet(name = "other-profile", urlPatterns = {"/profileOfUser"})
public class OtherUserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            UserDAO userDAO = new UserDAO();
            String idOfUser = req.getParameter("id");
            User otherUser = userDAO.getSpecUserFromId(idOfUser);
            root.put("user", otherUser);
            if (user != null) {
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "profile-of-different-user.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "profile-of-different-user-non-authorized.ftl", root);
            }
        }  catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

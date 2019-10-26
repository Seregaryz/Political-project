package servlets;

import dao.DebatesDAO;
import obj.Debates;
import obj.User;
import support.FreemarkerHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DebateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            DebatesDAO debatesDAO = new DebatesDAO();
            Debates debates = debatesDAO.getSpecDebates(req.getParameter("id"));
            root.put("debates", debates);
            if (user != null) {
                root.put("nickname", user.getNickname());
                if (debatesDAO.isParticipant(user.getId() + "", debates.getId() + "")) {
                    FreemarkerHelper.render(req, resp, "participant-debates-page-authorized.ftl", root);
                } else FreemarkerHelper.render(req, resp, "debates-page-authorized.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "debates-page-non-authorized.ftl", root);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            DebatesDAO debatesDAO = new DebatesDAO();
            Debates debates = debatesDAO.getSpecDebates(req.getParameter("id"));
            if (user != null) {
                root.put("nickname", user.getNickname());
                if (debatesDAO.roomIsAvailable(req.getParameter("id"))) {
                    debatesDAO.enrollUser(req.getParameter("id"), debates.getId() + "");
                    FreemarkerHelper.render(req, resp, "participant-debates-page-authorized.ftl", root);
                } else {
                    FreemarkerHelper.render(req, resp, "debates-page-authorized.ftl", root);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

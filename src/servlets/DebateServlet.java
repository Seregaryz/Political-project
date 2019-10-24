package servlets;

import dao.DebatesDAO;
import obj.Debates;
import obj.User;
import support.Helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DebateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            DebatesDAO debatesDAO = new DebatesDAO();
            ArrayList<Debates> debatesList = debatesDAO.getDebates();
            Debates debates = debatesDAO.findDebates(req.getParameter("id"), debatesList);
            root.put("debates", debates);
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            if (user != null) {
                root.put("nickname", user.getNickname());
                if (debatesDAO.isParticipant(user.getId() + "", debates.getId() + "")) {
                    Helpers.render(req, resp, "participant-debates-page-authorized.ftl", root);
                } else Helpers.render(req, resp, "debates-page-authorized.ftl", root);
            } else {
                Helpers.render(req, resp, "debates-page-non-authorized.ftl", root);
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
        User user = (User) session.getAttribute("current_user");
        try {
            DebatesDAO debatesDAO = new DebatesDAO();
            ArrayList<Debates> debatesList = debatesDAO.getDebates();
            Debates debates = debatesDAO.findDebates(req.getParameter("id"), debatesList);
            if (user != null) {
                root.put("nickname", user.getNickname());
                if (debatesDAO.roomIsAvailable(req.getParameter("id"))) {
                    debatesDAO.enrollUser(req.getParameter("id"), debates.getId() + "");
                    Helpers.render(req, resp, "participant-debates-page-authorized.ftl", root);
                } else {
                    Helpers.render(req, resp, "debates-page-authorized.ftl", root);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package servlets;

import dao.DebatesDAO;
import dao.MessagesDAO;
import obj.Debates;
import obj.Message;
import obj.User;
import support.FreemarkerHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                if (debatesDAO.isParticipant(user.getId() + "",
                        debatesDAO.getSpecDebates(req.getParameter("id")).getId() + "")) {
                    FreemarkerHelper.render(req, resp, "participant-debates-page.ftl", root);
                } else FreemarkerHelper.render(req, resp, "debates-page-authorized.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "debates-page-authorized.ftl", root);
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
            root.put("debates", debates);
            MessagesDAO messagesDAO = new MessagesDAO();
            List<Message> messages = messagesDAO.getMessages(req.getParameter("id"));
            root.put("messages", messages);
            if (user != null) {
                root.put("nickname", user.getNickname());
                if (debatesDAO.roomIsAvailable(req.getParameter("id"))) {
                    debatesDAO.enrollUser(req.getParameter("idUser"), req.getParameter("id"));
                    FreemarkerHelper.render(req, resp, "participant-debates-page.ftl", root);
                } else {
                    FreemarkerHelper.render(req, resp, "debates-page-authorized.ftl", root);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

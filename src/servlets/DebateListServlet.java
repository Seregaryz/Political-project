package servlets;

import dao.DebatesDAO;
import obj.Debates;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "debatesList", urlPatterns = {"/debatesList"})
public class DebateListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            DebatesDAO debatesDAO = new DebatesDAO();
            ArrayList<Debates> debatesList = debatesDAO.getDebates();
            root.put("debates", debatesList);
            if (user != null) {
                root.put("nickname", user.getNickname());
                FreemarkerHelper.render(req, resp, "debates-list-authorized.ftl", root);
            } else {
                FreemarkerHelper.render(req, resp, "debates-list-non-authorized.ftl", root);
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

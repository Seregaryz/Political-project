package servlets;

import obj.User;
import support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        Cookie[] cookies = req.getCookies();
        for(Cookie c : cookies){
            c.setValue("");
            c.setMaxAge(0);
            resp.addCookie(c);
        }
        session.setAttribute("current_user", null);
        resp.sendRedirect("/newsList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

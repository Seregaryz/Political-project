package servlets;

import com.sun.xml.internal.ws.server.ServerRtException;
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

@WebServlet(name = "profileRedaction", urlPatterns = {"/profileRedaction"})
@MultipartConfig
public class ProfileRedactionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        try {
            if (user != null) {
                System.out.println("We are here");
                System.out.println(user.getPassword());
                root.put("user", user);
                FreemarkerHelper.render(req, resp, "redaction-profile.ftl", root);
            } else if (ServiceHelper.isSavedInCookies(req)){
                UserDAO userDAO = new UserDAO();
                user = userDAO.getSpecUser((String)session.getAttribute("login"), (String)session.getAttribute("password"));
                session.setAttribute("current_user", user);
                root.put("user", user);
                FreemarkerHelper.render(req, resp, "redaction-profile.ftl", root);
            } else resp.sendRedirect("/login");
        } catch  (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FreeMarkerConfigurator.getInstance(this);
        Map<String, Object> root = new HashMap<>();
        String password = req.getParameter("password");
        String nickname = req.getParameter("nickname");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String sex = req.getParameter("sex");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("current_user");
        UserDAO userDAO = new UserDAO();
        try {
            if(!userDAO.isNicknameFree(nickname)){
                resp.sendRedirect("/profileRedaction");
            }
            Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z]).{8,32}");
            Matcher passMatcher = passwordPattern.matcher(password);
            if(!passMatcher.matches()){
                resp.sendRedirect("/profileRedaction");
            }
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
            String path = "/" + localdir + "/" + filename;
            if(userDAO.updateUser(user.getId(), password, nickname, name, surname, sex, path)) {
                User updatedUser = userDAO.getSpecUser(user.getEmail(), password);
                session.setAttribute("current_user", updatedUser);
            }
            resp.sendRedirect("/success");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

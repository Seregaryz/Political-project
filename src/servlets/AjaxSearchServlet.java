package servlets;

import dao.NewsDAO;
import obj.News;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AjaxSearchServlet extends HttpServlet {

    private NewsDAO newsDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        List<News> newsList= null;
        try {
            newsList = newsDAO.getByLikePattern(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(newsList.size());
        JSONArray ja = new JSONArray();
        for (News news: newsList) {
            ja.put(new JSONObject(news));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);

        resp.setContentType("text/json");
        resp.getWriter().write(jo.toString());

    }

    @Override
    public void init() throws ServletException {
        NewsDAO newsDAO = new NewsDAO();
    }
}

package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-07-21
 * Time: 23:17
 **/

@WebServlet("/findByPageServlet")
public class findByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String currentPage = req.getParameter("cucurrentPage");
        String rows = req.getParameter("rows");
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");
    }
}

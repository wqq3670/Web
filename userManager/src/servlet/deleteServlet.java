package servlet;

import service.UserService;

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
 * Date: 2020-07-23
 * Time: 18:20
 **/
@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idString = req.getParameter("id");
        int id = Integer.parseInt(idString);

        UserService userService = new UserService();

        int ret = userService.delete(id);
        if(ret == 1) {
            resp.sendRedirect("/usermanger/list.html");
        }else {
            System.out.println("删除失败");
            resp.getWriter().write("<h2 删除失败！>" + "</h2>");

        }
    }
}
package servlet;

import entiy.User;
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
 * Time: 0:51
 **/
@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String id = req.getParameter("id");

        UserService userService = new UserService();
        User user = userService.find(Integer.parseInt(id));
        if(user == null) {
            System.out.println("没有要修改的对象");
        }else {
            req.getSession().setAttribute("updateUser",user);
            resp.sendRedirect("/update.html");
        }
    }
}

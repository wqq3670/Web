package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:查询到刚刚记录到session中的user
 * 并转变为json,返回到update.html中，就可以拿到user的信息
 * User: dell
 * Date: 2020-07-23
 * Time: 11:57
 **/
@WebServlet("/returnServlet")
public class returnServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        //获取session中的内容
        Object user = req.getSession().getAttribute("updateUser");

        //把获取到的user返回给前端 json 便于前端处理
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将user转换为json字符串
        objectMapper.writeValue(resp.getWriter(),user);


    }
}

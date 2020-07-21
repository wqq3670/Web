package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entiy.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-07-16
 * Time: 19:22
 **/

@WebServlet("/loginServlet")
public class loginServelt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置请求体的字符集编码
        req.setCharacterEncoding("utf-8");
        //设置响应体前端数据类型以及字符集编码
        resp.setContentType("application/json;charset=utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        //因为Service层login方法需要接收一个User loginUser，所以在servlet层建立并传参
        User loginUser = new User();
        loginUser.setUsername(username);//把前端获取到的登录名密码传给后端
        loginUser.setPassword(password);

        //调用userService中login方法,先有这个类
        UserService userService = new UserService();
        User user = userService.login(loginUser);
        Map<String,Object> returnMap = new HashMap<>();//返回值的map集合形式的

        //根据传回来的返回值判断登录是否成功
        if(user != null) {
             System.out.println("登陆成功"+username);
             //1.把当前登录成功的用户写到session当中
            req.getSession().setAttribute("user",user);
            returnMap.put("msg",true);
        }else {
             System.out.println("登陆失败"+username);
            returnMap.put("msg",false);
        }

        //把登陆成功的map返回给前端 json 便于前端处理
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将returnMap转换为json字符串
        objectMapper.writeValue(resp.getWriter(),returnMap);
    }


}


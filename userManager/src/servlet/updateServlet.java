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
 * Date: 2020-07-23
 * Time: 12:53
 **/
@WebServlet("/updateServlet")
public class updateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        String address = req.getParameter("address");
        String qq = req.getParameter("qq");
        String email = req.getParameter("email");

        //将存到session中的updateUser信息拿出来
        Object us = req.getSession().getAttribute("updateUser");
        //进行类型转换（强转）
        User user = (User) us;
        //new一个updateUser用来接收session中的updateUser信息
        User updateUser = new User();

        //组装updateUser这个对象
        updateUser.setId(user.getId());
        updateUser.setName(name);
        updateUser.setGender(gender);
        updateUser.setAge(age);
        updateUser.setAddress(address);
        updateUser.setQq(qq);
        updateUser.setEmail(email);

        UserService userService = new UserService();
        int ret = userService.update(updateUser);

        Map<String,Object> returnMap = new HashMap<>();

        if(ret == 1) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }

        //把returnMap返回给前端 json 便于前端处理
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将returnMap转换为json字符串
        objectMapper.writeValue(resp.getWriter(),returnMap);

    }

}

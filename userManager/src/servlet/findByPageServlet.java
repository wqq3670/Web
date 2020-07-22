package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entiy.PageBean;
import entiy.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

        String currentPage = req.getParameter("currentPage");
        String rows = req.getParameter("rows");
        /*String name = req.getParameter("name");
        String address = req.getParameter("address");
        String email = req.getParameter("email");*/
        Map<String,String[]> parMap = req.getParameterMap();//这个parMap是不能直接进行修改的
        /*
        *这样直接删除会出现 500 的服务器错误
        parMap.remove(currentPage);
        parMap.remove(rows);*/

        //解决上述问题只要新建一个map集合，把parMap传进去，在map集合中进行删除就好了
        Map<String,String[]> map = new HashMap<>(parMap);
        map.remove(currentPage);
        map.remove(rows);

        for(Map.Entry<String,String[]> entry : parMap.entrySet()) {
            System.out.println("key: "+entry.getKey()+" value"+Arrays.toString(entry.getValue()));
        }

        //在userService.findAllByPage中 currentPage、rows 是int类型，所以在传参前进行类型转换
        int currentpage = Integer.parseInt(currentPage);
        int rowspage = Integer.parseInt(rows);
        UserService userService = new UserService();
        PageBean<User> pageBean = userService.findAllByPage(currentpage,rowspage,map);

        //把登陆成功的map返回给前端 json 便于前端处理
        ObjectMapper objectMapper = new ObjectMapper();
        //就是将pageBean转换为json字符串
        objectMapper.writeValue(resp.getWriter(),pageBean);
    }
}


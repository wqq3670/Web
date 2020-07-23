package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Time: 18:33
 **/
@WebServlet("deleteSelectedServlet")
public class deleteSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        //前端中删除选中，可能选中多个，所以传给后端一个id[]
        String[] values = req.getParameterValues("id[]");

        int sum = 0;
        UserService userService = new UserService();
        for (int i = 0; i < values.length; i++) {
            int delete = userService.delete(Integer.parseInt(values[i]));
            sum += delete;
        }

        Map<String,Object> returnMap = new HashMap<>();
        if(sum == values.length) {
            returnMap.put("msg",true);
        }else {
            returnMap.put("msg",false);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getWriter(),returnMap);
    }
}

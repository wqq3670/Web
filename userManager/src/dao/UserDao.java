package dao;

import entiy.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-07-14
 * Time: 20:07
 **/
public class UserDao {

    /**
     * 注册模块
     * @param registerUser
     * @return
     */
    public static int register(User registerUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "insert into usermessage(username,password) values (?,?)";

        connection = DBUtil.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            //预编译后，拿到前端传回来的用户名 密码
            ps.setString(1,registerUser.getUsername());
            ps.setString(2,registerUser.getPassword());

            int ret = ps.executeUpdate();
            return ret;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return 0;
    }

    /**
     * 登录模块
     * @param loginUser
     * @return
     */
    public static User login(User loginUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select *from usermessage where username=? and password=?";

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);//对SQL语句进行预编译
            //获取要查询的登陆进来的用户名和密码是否正确
            ps.setString(1,loginUser.getUsername());
            ps.setString(2,loginUser.getPassword());
            rs = ps.executeQuery();
            if(rs.next() ) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return user;
    }

    /**
     * 添加用户模块
     * @param user
     * @return
     */
    public static int add(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "insert into usermessage(name,username,password,gender,age,address,qq,email) values(?,?,?,?,?,?,?,?)";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getGender());
            ps.setInt(5,user.getAge());
            ps.setString(6,user.getAddress());
            ps.setString(7,user.getQq());
            ps.setString(8,user.getEmail());

            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return 0;//添加失败
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    public static int delete(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "delete from usermessage where id=?";

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);

            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return 0;//删除失败
    }

    /**
     * 根据用户id查找对应用户
     * @param id
     * @return
     */
    public static User find(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select *from usermessage where id=?";

        connection = DBUtil.getConnection();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if(rs.next() ) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return user;
    }

    /**
     * 更新，只需要给一个用户对象就好了
     * @param updateUser
     * @return
     */
    public static int updateUser(User updateUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "update usermessage set gender=?,age=?,address=?,qq=?,email=? where id=?";


        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,updateUser.getGender());
            ps.setInt(2,updateUser.getAge());
            ps.setString(3,updateUser.getAddress());
            ps.setString(4,updateUser.getQq());
            ps.setString(5,updateUser.getEmail());
            ps.setInt(6,updateUser.getId());

            int ret = ps.executeUpdate();
            return ret;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return 0;//更新失败
    }


   public static void main(String[] args) {
        User user = new User();
//        if(user != null) {
//            System.out.println(user);
//        }else {
//            System.out.println("没有此用户");
//        }
//  }
       // user.setName("王倩倩");
       // user.setUsername("wqq");
       //user.setPassword("123");
//        user.setId(8);
//        user.setGender("女");
//        user.setAge(22);
//        user.setAddress("宝鸡");
//        user.setQq("1121093520");
//        user.setEmail("wangqianqianwork01@163.com");

       user.setUsername("sehun");
       user.setPassword("940412");

       //int ret = updateUser(user);
       if(register(user) == 0) {
           System.out.println("注册失败");
       }else {
           System.out.println("注册成功");
       }
    }
}

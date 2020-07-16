package dao;

import entiy.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    public int register(User registerUser) {
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
    public User login(User loginUser) {
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
     * @param addUser
     * @return
     */
    public int add(User addUser) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "insert into usermessage(name,username,password,gender,age,address,qq,email) values(?,?,?,?,?,?,?,?)";
        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,addUser.getName());
            ps.setString(2,addUser.getUsername());
            ps.setString(3,addUser.getPassword());
            ps.setString(4,addUser.getGender());
            ps.setInt(5,addUser.getAge());
            ps.setString(6,addUser.getAddress());
            ps.setString(7,addUser.getQq());
            ps.setString(8,addUser.getEmail());

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
     * 根据删除用户信息
     * @param id
     * @return
     */
    public int delete(int id) {
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
    public User find(int id) {
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
    public int update(User updateUser) {
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

    /**
     * 将list集合中的值通过ps.setObject给sql语句进行赋值
     * @param ps
     * @param values
     * @throws SQLException
     */
    private static void setValues(PreparedStatement ps, Object[] values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            ps.setObject(i+1,values[i]);
        }
    }

    /**
     * 查询当前条件下的所有用户信息
     * @param start 开始查询的起始位置
     * @param rows 每次查询多少条记录
     * @param map name,address,email
     *            Map<String,String[]> map
     *            map<name/"张飞">
     *            map<address/"成都">
     *            map<email/"126@qq.com">
     * @return
     */
    public static List<User> findByPage(int start,int rows,Map<String,String[]> map) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        String sql = "select * from usermessage where 1=1";//处理查询信息为空的情况，即显示所有信息
        StringBuffer sb = new StringBuffer(sql);//方便sql语句的拼接

        List<Object> list = new ArrayList<>();//创建list集合用来存放map集合中value（不为空）数组的值（第一个元素）

        Set<String> keySet = map.keySet();//把map集合中的key对应的值放进keySet集合

        //遍历keySet集合，拿到每个key值对应的value数组的第一个元素，若其不为空时，进行sql语句拼接
        //并存放list集合中
        for (String key : keySet) {
            String value = map.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and ").append(key).append(" like ?");
                list.add("%"+value+"%");
            }
        }
        sb.append(" limit ?,? ");
        list.add(start);
        list.add(rows);

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());

            //给sql语句进行赋值
            setValues(ps,list.toArray());
            rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAge(rs.getInt("age"));
                user.setAddress(rs.getString("address"));
                user.setQq(rs.getString("qq"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return userList;
    }

    /**
     * 查询有多少条记录
     * @param map
     * @return
     */
    public int findAllRecord(Map<String,String[]> map) {

        int count = 0;//用来存放有多少条数据
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select count(*) from usermessage where 1=1";
        StringBuffer sb = new StringBuffer(sql);
        List<Object> list = new ArrayList<>();

        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            String value = map.get(key)[0];
            if(value != null && !"".equals(value)) {
                sb.append(" and ").append(key).append(" like ?");
                list.add("%"+value+"%");
            }
        }

        try {
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sb.toString());
            //对sql语句进行赋值
            setValues(ps,list.toArray());

            rs = ps.executeQuery();

            if(rs.next()) {
                count = rs.getInt("count(*)");
                //count = rs.getInt(1);//和上一行效果一样
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return count;
    }



    //主函数用来测试
   public static void main(String[] args) {
//        User user = new User();
//        if(user != null) {
//            System.out.println(user);
//        }else {
//            System.out.println("没有此用户");
//        }
//        user.setName("王倩倩");
//        user.setUsername("wqq");
//        user.setPassword("123");
//        user.setId(8);
//        user.setGender("女");
//        user.setAge(22);
//        user.setAddress("宝鸡");
//        user.setQq("1121093520");
//        user.setEmail("wangqianqianwork01@163.com");
//
//       user.setUsername("sehun");
//       user.setPassword("940412");
//
//       int ret = updateUser(user);
//       if(register(user) == 0) {
//           System.out.println("注册失败");
//       }else {
//           System.out.println("注册成功");
//       }
       Map<String,String[]> map = new HashMap<>();
       String[] names = {""};
       map.put("name",names);
       String[] addresses = {""};
       map.put("address",addresses);
       String[] emails = {""};
       map.put("email",emails);

       List<User> userList = findByPage(0,5,map);
       for (User user : userList) {
           System.out.println(user);
       }
//       int ret = findAllRecord(map);
//       System.out.println(ret);
    }
}

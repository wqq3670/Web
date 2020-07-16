package service;

import dao.UserDao;
import entiy.PageBean;
import entiy.User;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: dell
 * Date: 2020-07-16
 * Time: 16:14
 **/
public class UserService {

    /**
     * 注册
     * @param registerUser
     * @return
     */
    public int register(User registerUser) {
        UserDao userDao = new UserDao();
        int ret = userDao.register(registerUser);
        return ret;
    }
    /**
     * 登录
     * @param loginUser
     * @return
     */
    public User login(User loginUser) {
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        return user;
    }

    /**
     * 添加用户
     * @param addUser
     * @return
     */
    public int add(User addUser) {
        UserDao userDao = new UserDao();
        int ret = userDao.add(addUser);
        return ret;
    }

    /***
     * 根据删除用户信息
     * @param id
     * @return
     */
    public int delete(int id) {
        UserDao userDao = new UserDao();
        int ret = userDao.delete(id);
        return ret;
    }

    /**
     *根据用户id查找对应用户
     * @param id
     * @return
     */
    public User find(int id) {
        UserDao userDao = new UserDao();
        User user = userDao.find(id);
        return user;
    }

    /**
     * 更新，只需要给一个用户对象就好了
     * @param updateUser
     * @return
     */
    public int update(User updateUser) {
        UserDao userDao = new UserDao();
        int ret = userDao.update(updateUser);
        return ret;
    }


    /**
     *
     * @param currentPage 当前页
     * @param rows 每页的行数
     * @param map 包含name address email
     * @return
     *
     *     private int totalCount;//总记录数（整个系统所有记录数） 12  fingAllRecord
     *     private int totalPage;//总页码（整个系统所有页码数）    3
     *     private List<T> list;//每页中的数据（信息）        fingByPage
     *     private int currentPage;//当前页码（当前是第几页）    已知
     *     private int rows;//每页的记录数   5（已知）
     */
    public PageBean<User> findAllByPage(int currentPage, int rows, Map<String, String[]> map) {


        //当前页的一个类 里面包括totalCount、totalPage、List<T> list、currentPage、rows等字段
        PageBean<User> pageBean = new PageBean<>();
        UserDao userDao = new UserDao();

        //通过Dao层的findAllRecord函数拿到totalCount
        int totalCount = userDao.findAllRecord(map);
        //通过totalCount算出totalPage
        int totalPage = 0;
        if(totalCount % rows != 0) {
            totalPage = totalCount/rows+1;
        }else {
            totalPage = totalCount/rows;
        }
        //通过Dao层的findByPage函数拿到本页中的数据
        int start = (currentPage-1)*rows;
        List<User> userList = userDao.findByPage(start,rows,map);

        //将PageBean中字段的值传到对象pageBean中
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setList(userList);
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);

        return pageBean;
    }


}

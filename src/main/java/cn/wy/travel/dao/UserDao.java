package cn.wy.travel.dao;


import cn.wy.travel.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @Select("select * from tab_user where username= #{username}")
    User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    @Insert("insert into tab_user (uid,username,password,name,birthday,sex,telephone,email,status,code) values (#{uid},#{username},#{password},#{name},#{birthday},#{sex},#{telephone},#{email},#{status},#{code})")
    void save(User user);

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    @Select("select * from tab_user where code = #{code}")
    User findByCode(String code);

    /**
     * 修改指定用户的状态
     * @param user
     */
    @Update("update tab_user set status = 'Y' where uid=#{uid}")
    void updateStatus(User user);

    /**
     * 根据密码和用户名查询信息
     * @param username
     * @return
     */
    @Select("select * from tab_user where username=#{username}")
    User findByUsernameAndPassword(String username);

    //查找用户名是否存在
    @Select("select username from tab_user where username=#{username}")
    String checkByUsername(String username);

    //查询所有账户
    @Select("select * from tab_user")
    List<User> findAll();

    //根据用户id查找用户信息
    @Select("select * from tab_user where uid=#{uid}")
    User findById(int uid);
}

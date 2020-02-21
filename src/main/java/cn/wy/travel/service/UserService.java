package cn.wy.travel.service;


import cn.wy.travel.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);



    User login(User user);

    boolean checkUsername(String username);
    //查询所有账户
    List<User> findAll();

    User findUser(int uid);


}

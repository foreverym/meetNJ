package cn.wy.travel.service;


import cn.wy.travel.domain.Message;
import cn.wy.travel.domain.Route;
import cn.wy.travel.domain.User;
import cn.wy.travel.util.PageBean;

import java.util.List;

public interface MessageService {


    void insertMessage(int rid, int uid, String message);

    PageBean<Message> messageQuery(int rid, int currentPage, int pageSize);

    List<User> findUserlist(int rid);

}

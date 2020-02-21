package cn.wy.travel.service.impl;

import cn.wy.travel.dao.MessageDao;
import cn.wy.travel.dao.UserDao;
import cn.wy.travel.domain.Message;
import cn.wy.travel.domain.Route;
import cn.wy.travel.domain.User;
import cn.wy.travel.service.MessageService;
import cn.wy.travel.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Autowired
    UserDao userDao;




    @Override
    public void insertMessage(int rid, int uid, String message) {
        messageDao.insertMessage(rid, uid, message);
    }

    @Override
    public PageBean<Message> messageQuery(int rid, int currentPage, int pageSize) {

        //封装PageBean
        PageBean<Message> pb = new PageBean();
        List<Integer> uidlist = messageDao.findUidByRid(rid);
        List<User> userList = new ArrayList<>();
        for (Integer uid : uidlist) {
            System.out.println(uid+"===============================================================================");
            User user = userDao.findById(uid);
            userList.add(user);
        }
        pb.setUserList(userList);
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示的条数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = messageDao.findCountMessage(rid);
        // int totalCount2 = routeDao.findTotalCount2(cid, rname);
        System.out.println(totalCount);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1)*pageSize;
        System.out.println(start);
        System.out.println(pageSize);
        List<Message> list = messageDao.findMessage(rid, start, pageSize);
        System.out.println("************************************");
        for (Message message : list){
            System.out.println(message);
        }
        System.out.println("************************************");
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize ==0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public List<User> findUserlist(int rid) {

        List<Integer> uidlist = messageDao.findUidByRid(rid);
        List<User> userList = new ArrayList<>();
        for (Integer uid : uidlist) {
            System.out.println(uid+"===============================================================================");
            User user = userDao.findById(uid);
            userList.add(user);
        }
        return userList;
    }


}

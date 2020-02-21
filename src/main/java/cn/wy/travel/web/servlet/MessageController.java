package cn.wy.travel.web.servlet;


import cn.wy.travel.dao.UserDao;
import cn.wy.travel.domain.Message;
import cn.wy.travel.domain.User;
import cn.wy.travel.service.MessageService;
import cn.wy.travel.service.UserService;
import cn.wy.travel.util.PageBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping("/messageQuery")
    public void messageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        System.out.println(pageSizeStr+"--------------------------------------------------------------");
        String ridStr =  request.getParameter("rid");
//        String uidStr =  request.getParameter("uid");
//
        int rid = 0;

        //2处理参数
        rid = Integer.parseInt(ridStr);


        //List<User> users  = messageService.findUserlist(rid);
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示的条数
        if (pageSizeStr != null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

//

        //调用service查询pagebean对象
        PageBean<Message> pageBean = messageService.messageQuery(rid,currentPage,pageSize);
        writeValue(pageBean,response);
//        writeValue(users,response);

    }


    @RequestMapping("/userQuery")
    public void userQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {

        String uidStr =  request.getParameter("uid");

        int uid = 0;
        //2处理参数
        if (uidStr != null && uidStr.length()>0){
            uid = Integer.parseInt(uidStr);
        }else{
            uid = 0;
        }

        User user = userService.findUser(uid);
//        writeValue(user, response);
    }

    //将pagebean对象序列化json
    public void writeValue(Object obj1, HttpServletResponse response) throws IOException {

        response.setContentType("application/json:charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj1);
       // mapper.writeValue(response.getOutputStream(),obj2);

    }

}

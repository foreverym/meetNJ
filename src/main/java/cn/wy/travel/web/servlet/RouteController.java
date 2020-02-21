package cn.wy.travel.web.servlet;

import cn.wy.travel.domain.Route;
import cn.wy.travel.domain.User;
import cn.wy.travel.service.FavoriteService;
import cn.wy.travel.service.RouteService;
import cn.wy.travel.util.PageBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/route")
public class RouteController {

    /**
     * 分页查询
     */
    @Autowired
    RouteService routeService;

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping("/pageQuery")
    public void pageQuery(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");


        //接收rname线路名称
        String rname = request.getParameter("rname");

        int cid = 0;
        //2处理参数
        if (cidStr != null && cidStr.length()>0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }

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

        //调用service查询pagebean对象
        PageBean<Route> pageBean = routeService.pageQuery(cid,currentPage,pageSize,rname);
        writeValue(pageBean,response);
    }

    @RequestMapping("/findOne")
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收id
        String rid = request.getParameter("rid");

        //调用service查询route对象
        Route route = routeService.findOne(rid);

        //转为json传回客户端
        writeValue(route,response);
    }


    /**
     * 判断当前登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/isFavorite")
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //获取线路id
        String rid = request.getParameter("rid");
        //获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            //用户尚未登录
            uid = 0;
        }else {
            //用户已经登录
            uid = user.getUid();
        }

        //调用favoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //写会客户端
        writeValue(flag,response);

    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addFavorite")
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //获取线路id
        String rid = request.getParameter("rid");
        System.out.println("********************************************"+rid);
        //获取当前登录的用户
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if (user == null){
            //用户尚未登录
            return;
        }else {
            //用户已经登录
            uid = user.getUid();
        }

        //调用favoriteService查询是否收藏
        favoriteService.add(rid,uid);

    }


    //将pagebean对象序列化json
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {

        response.setContentType("application/json:charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj);

    }

}

package cn.wy.travel.service.impl;

import cn.wy.travel.dao.FavoriteDao;
import cn.wy.travel.dao.RouteDao;
import cn.wy.travel.dao.RouteImageDao;
import cn.wy.travel.dao.SellerDao;
import cn.wy.travel.domain.Route;
import cn.wy.travel.domain.RouteImg;
import cn.wy.travel.domain.Seller;
import cn.wy.travel.service.RouteService;
import cn.wy.travel.util.PageBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteDao routeDao;
    
    @Autowired
    private RouteImageDao routeImageDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private FavoriteDao favoriteDao;



    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示的条数
        pb.setPageSize(pageSize);
        //设置总记录数
        ///System.out.println("111111111111111111111111111111");
        int totalCount = routeDao.findTotalCount(cid,rname);
       // int totalCount2 = routeDao.findTotalCount2(cid, rname);
        System.out.println(totalCount);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1)*pageSize;
        System.out.println("2222222222222222222222222222222222");
        System.out.println(start);
        System.out.println(pageSize);
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        for (Route route : list){
            System.out.println(route);
        }
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize ==0 ? totalCount / pageSize : (totalCount / pageSize)+1;
        pb.setTotalPage(totalPage);

        return pb;

    }

    @Override
    public Route findOne(String rid) {

        //根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //根据route的id查询图片的信息
        List<RouteImg> routeImgList = routeImageDao.findByRid(route.getRid());
        //将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //根据route 的sid查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }

}

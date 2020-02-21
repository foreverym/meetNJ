package cn.wy.travel.service;

import cn.wy.travel.domain.Route;
import cn.wy.travel.util.PageBean;

public interface RouteService {

    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    //根据id查询
    Route findOne(String rid);
}

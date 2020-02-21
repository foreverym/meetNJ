package cn.wy.travel.dao;

import cn.wy.travel.domain.RouteImg;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteImageDao {

    @Select("select * from tab_route_img where rid=#{rid} ")
    List<RouteImg> findByRid(int rid);

}

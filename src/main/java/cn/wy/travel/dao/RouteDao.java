package cn.wy.travel.dao;

import cn.wy.travel.domain.Route;
import cn.wy.travel.util.SqlMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDao {

    /**
     * 根据id查询总记录数
     */
//    @Select("select count(*) from tab_route where cid=#{cid}")
//    int findTotalCount(int cid,String rname);

    @SelectProvider(type = SqlMapper.class, method = "getString")
    int findTotalCount(@Param("cid")int cid, @Param("rname")String rname);//@Param("cid")


    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     */
    @SelectProvider(type = SqlMapper.class, method = "getString2")//"select * from tab_route where cid=#{cid} limit #{start},#{pageSize}"
    List<Route> findByPage(@Param("cid") int cid, @Param("start") int start, @Param("pageSize") int pageSize, @Param("rname") String rname);

    /**
     * 根据rid查询route对象
     * @param rid
     * @return
     */
    @Select("select * from tab_route where rid = #{rid}")
    Route findOne( int rid);

}

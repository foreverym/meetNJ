package cn.wy.travel.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HotSearchDao {

    @Select("select hname from tab_hotsearch order by hnum desc limit 0,10")
    List<String> findResult();

    @Select("select hnum from tab_hotsearch where hname=#{hname}")
    int findHnum(String hname);

    @Select("select count(*) from tab_hotsearch where hname=#{hname}")
    int countHotWords(String hname);

    @Insert("insert into tab_hotsearch (hname,hnum) values (#{hname},#{hnum})")
    void insertResult(@Param("hname") String hname, @Param("hnum") int hnum);

    @Update("update tab_hotsearch set hnum = #{hnum} where hname=#{hname}")
    void updateHotWords(@Param("hname") String hname, @Param("hnum") int hnum);

}

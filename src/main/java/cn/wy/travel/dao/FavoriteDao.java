package cn.wy.travel.dao;

import cn.wy.travel.domain.Favorite;
import cn.wy.travel.util.SqlMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface FavoriteDao {

    @Select("select * from tab_favorite where rid = #{rid} and uid = #{uid}")
    Favorite findByRidAndUid(@Param("rid") int rid, @Param("uid") int uid);

    @Select("select count(*) from tab_favorite where rid = #{rid}")
    int findCountByRid(int rid);

    //@Insert("insert into tab_favorite values(#{rid},new Date(),#{uid})")
    @InsertProvider(type = SqlMapper.class, method = "getString3")
    void add(@Param("rid") int rid, @Param("uid") int uid);
}

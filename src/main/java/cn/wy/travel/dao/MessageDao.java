package cn.wy.travel.dao;

import cn.wy.travel.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface MessageDao {

    @Select("select * from tab_message where rid=#{rid} limit #{start},#{pageSize}")
    List<Message> findMessage(@Param("rid") int rid, @Param("start") int start, @Param("pageSize") int pageSize);

    @Insert("insert into tab_message (rid,uid,message) values(#{rid},#{uid},#{message})")
    void insertMessage(@Param("rid") int rid, @Param("uid") int uid, @Param("message") String message);

    @Select("select count(*) from tab_message where rid = #{rid}")
    int findCountMessage(int rid);

    //SELECT username FROM tab_user WHERE uid IN (SELECT uid FROM tab_message WHERE rid=1);
    @Select("select uid from tab_message where rid = #{rid}")
    List<Integer>  findUidByRid(int rid);



}

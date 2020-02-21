package cn.wy.travel.dao;

import cn.wy.travel.domain.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao {

    /**
     * 查询所有
     * @return
     */
    @Select("select *  from tab_category")
    List<Category> findAll();

}

package cn.wy.travel.dao;

import cn.wy.travel.domain.Seller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao {

    @Select("select * from tab_seller where sid=#{sid}")
    Seller findById(int sid);

}

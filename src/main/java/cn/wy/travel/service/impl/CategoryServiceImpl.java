package cn.wy.travel.service.impl;

import cn.wy.travel.dao.CategoryDao;
import cn.wy.travel.domain.Category;
import cn.wy.travel.service.CategoryService;
import cn.wy.travel.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 期望数据库中存储的数据
     * @return
     */
    @Override
    public List<Category> finaAll() {
        //1.从redis中查询

        //1.1获取jedis客户端
        //Jedis jedis = JedisUtil.getJedis();
        //1.2可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category",0,-1);
        //1.3查询sortedset中的分数（cid）和值(name)
       // Set<Tuple> categorys = jedis.zrangeWithScores("categoty", 0, -1);
        //2.判断查询的集合是否为空
        List<Category> cs = null;
       // if (categorys == null || categorys.size() == 0){
            System.out.println("从数据查询库");
            //3.如果为空，需要从数据库查询，再讲数据存入redis
            //3.1从数据库中查询
            cs = categoryDao.findAll();
            //3.2将集合数据存储到redis中的category的keyrt
//            for (int i=0; i<cs.size(); i++){
//                jedis.zadd("category", cs.get(i).getCid(),cs.get(i).getCname());
//            }
//        }else {
//            System.out.println("从redis中查询");
//            //4.如果不为空，将set集合的数据缓存到list
//            cs = new ArrayList<Category>();
//            for (Tuple tuple: categorys){
//                Category category = new Category();
//                category.setCname(tuple.getElement());
//                category.setCid((int)tuple.getScore());
//                cs.add(category);
//            }
//        }
        return cs;
    }
}

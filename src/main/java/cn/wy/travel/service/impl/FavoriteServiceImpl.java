package cn.wy.travel.service.impl;

import cn.wy.travel.dao.FavoriteDao;
import cn.wy.travel.domain.Favorite;
import cn.wy.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    @Override
    public boolean isFavorite(String rid, int uid) {

        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);
        return favorite != null;
    }

    @Override
    public void add(String rid, int uid) {
        System.out.println("添加");
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}

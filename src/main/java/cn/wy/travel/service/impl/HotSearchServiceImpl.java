package cn.wy.travel.service.impl;

import cn.wy.travel.dao.HotSearchDao;
import cn.wy.travel.service.HotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotSearchServiceImpl implements HotSearchService {
    @Autowired
    HotSearchDao hotSearchDao;

    @Override
    public List<String> queryHotWords() {
        List<String> results = hotSearchDao.findResult();
        return results;
    }

    @Override
    public void insertHotWords(String hname) {
        int count = hotSearchDao.countHotWords(hname);

        if (hname == null){
            return;
        }
        else if (count == 0 && hname!=null) {
            hotSearchDao.insertResult(hname,count+1);
        } else {
            int hnum = hotSearchDao.findHnum(hname);
            hnum=hnum+1;
            hotSearchDao.updateHotWords(hname,hnum);
        }


    }


}

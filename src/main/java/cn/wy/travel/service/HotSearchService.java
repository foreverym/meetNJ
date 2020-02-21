package cn.wy.travel.service;

import java.util.List;

public interface HotSearchService {

    List<String> queryHotWords();

    void insertHotWords(String hname);

}

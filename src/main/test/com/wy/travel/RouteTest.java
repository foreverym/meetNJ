package com.wy.travel;

import cn.wy.travel.dao.RouteDao;
import cn.wy.travel.service.impl.RouteServiceImpl;
import cn.wy.travel.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

public class RouteTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:applicationContext.xml")
    public class UserTest {
        @Resource
        public RouteServiceImpl routeService;

        @Test
        public void testGetStuInfo() {
           routeService.pageQuery(5,0,0,"兵马俑");

        }
    }

}

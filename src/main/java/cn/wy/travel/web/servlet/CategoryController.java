package cn.wy.travel.web.servlet;

import cn.wy.travel.domain.Category;
import cn.wy.travel.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/findAll")
    public void findAll(HttpServletResponse response) throws IOException {
        //1.调用service查询所有
        List<Category> categories = categoryService.finaAll();
        //2.序列化json返回
        writeValue(categories,response);


    }




    /**
     * 直接传入的对象序列化json，并且写会客户端
     * @param obj
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {


        response.setContentType("application/json:charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     *将传入的参数序列化为json，返回
     * @param obj
     * @return
     */
    public String writeVlaueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }



}
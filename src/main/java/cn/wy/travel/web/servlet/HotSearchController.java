package cn.wy.travel.web.servlet;

import cn.wy.travel.service.HotSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("hotwords")
public class HotSearchController {

    @Autowired
    HotSearchService hotSearchService;

    @RequestMapping("queryHotWords")
    public void queryHotWords(HttpServletResponse response) throws IOException {
        List<String> hotWords = hotSearchService.queryHotWords();
        for (String hotword : hotWords) {
            System.out.println(hotword);
        }
        writeValue(hotWords,response);
    }

    @RequestMapping("/insertWords")
    public void insertWords(HttpServletRequest request,HttpServletResponse response) throws IOException {


        response.setContentType("text/html;charset=utf-8");
        String hname = request.getParameter("rname");
        if (hname == null){
            response.sendRedirect(request.getContextPath() + "/index.html");
        }else {
            System.out.println(hname);
            hotSearchService.insertHotWords(hname);
        }


    }

    public void writeValue(Object obj, HttpServletResponse response) throws IOException {

        response.setContentType("application/json:charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),obj);
    }

}

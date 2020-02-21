package cn.wy.travel.util;

import cn.wy.travel.dao.RouteDao;
import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SqlMapper {

   /*public  String getString(Map<String, Object> para){
        //1.定义SQL模板
        String sql = "select count(*) from tab_route where ";
        StringBuilder sb = new StringBuilder(sql);

       // List params = new ArrayList();  //条件

        //2.判断参数是否有值
        if (para.get("cid") != 0){
            sb.append(" cid = #{cid} ");
        }
        if (para.get("cname") != null && para.get("cid").toString().length()>0){
            sb.append(" and rname like" +"%"+"#{rname}"+"%" );
        }
        sql = sb.toString();
        return sql;
    }*/

    public String getString(final Map<String, Object> para){
        return new SQL(){{
            SELECT("count(*)");
            FROM("tab_route");
//            System.out.println(para.get("cid")+"----------------------------");
//            System.out.println(para.get("rname")+"------------------------");
            if (para.get("cid") != 0){
                WHERE("cid="+para.get("cid"));
                System.out.println(para.get("cid")+"+++++++++++++++++++");
            }
            if (para.get("rname") != null && para.get("rname").toString().length() > 0){
                WHERE(" rname like "+"'%"+para.get("rname")+"%'" );

                System.out.println(para.get("rname")+"------------------------------");
            }



        }}.toString();
    }

    public String getString2(final Map<String, Object> para){
        return new SQL(){{
            SELECT(" * ");
            FROM(" tab_route ");
//            System.out.println(para.get("cid")+"----------------------------");
//            System.out.println(para.get("rname")+"------------------------");
            WHERE(" 1=1 ");

            if (para.get("rname") != null && para.get("rname").toString().length() > 0){
                WHERE(" and rname like "+"'%"+para.get("rname")+"%'" );

                System.out.println(para.get("rname")+"------------------------------");
            }
            if (para.get("cid") != 0){
                WHERE(" and cid="+para.get("cid"));
                System.out.println("+++++++++++++++++++");
            }
            WHERE("limit "+para.get("start")+","+para.get("pageSize"));

        }}.toString().replace(")"," ").replace("("," ").replace("AND"," ");
    }

    public String getString3(final Map<String, Object> para){
        final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        return new SQL(){{

            INSERT_INTO("tab_favorite");
            //INTO_COLUMNS("rid", "date","uid");
            INTO_VALUES(para.get("rid").toString(),"'"+sdf.format(new Date())+"'",para.get("uid").toString());
            //VALUES("rid",(String) para.get("rid"));
//            VALUES("date",new Date().toString());
//            VALUES("uid",(String) para.get("uid"));



        }}.toString();
    }

    public static void main(String[] args) {

    }

}

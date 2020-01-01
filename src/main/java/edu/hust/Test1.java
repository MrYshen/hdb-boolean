package edu.hust;

import edu.hust.Dao.BaseDao;
import edu.hust.Dao.UserDao;
import edu.hust.data.Data;
import edu.hust.mysql.Mysql;
import edu.hust.screen.Screen;
import hdb.*;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {


        List<IndexTag> idList = Screen.deleteTag();
//        System.out.println(idList.size());

        int m = 0;
        for (IndexTag a :idList) {
            m++;
            if(a.id<=3986){
                continue;
            }
            for(int i = 4;i<30;i++) {
                Mysql mysql1 = new Mysql();
                BaseDao baseDao = new BaseDao();
                Data data = new Data();
                UserDao userDao = new UserDao();
                String timeString = userDao.timeString(i);
                String tableName = new String("tbl" + a.id + timeString);
                String sql = mysql1.getTblSQL(tableName);
                baseDao.affectRowMore1(sql, -1);
                data.writeData(tableName, a.id);
                System.out.println("正在写第"+m+"个测点");
            }
        }


    }
}

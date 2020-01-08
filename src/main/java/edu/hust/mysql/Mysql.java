package edu.hust.mysql;

import edu.hust.Dao.UserDao;
import hdb.BoolData;
import hdb.FloatData;
import hdb.IndexTag;
import java.util.List;


public class Mysql {

    //获取创建表的语句
    public String getTblSQL(String tableName) {

        String sql = new String("create table if not exists " + tableName + "(t int not null primary key,"
            +"v int not null ,f int not null"+") ENGINE=INNODB DEFAULT CHARSET=utf8;");

        return sql;
    }


    public void insertBenchMark(BoolData[] floatData, String tableName, int id){

        UserDao userDao = new UserDao();
        StringBuffer suffix = new StringBuffer();
        for ( int i = 0;i<floatData.length;i++) {
//            if (floatData[i].flag == 0)
//                continue;
            suffix.append("(" + floatData[i].tm + "," + floatData[i].val + ","+floatData[i].flag+ "),");
            if(suffix.length()>1000000){
                String suffix1 = suffix.substring(0, suffix.length()-1);
                boolean b = userDao.insertMore1(suffix1+";", i,tableName,id);
                if(!b) {
                    System.out.println("出错了----");
                    System.exit(0);
                }else {
                    // 清空上一次添加的数据
                    suffix = new StringBuffer();
                }
            }
        }
        String suffix1 = suffix.substring(0, suffix.length()-1);

        boolean b = userDao.insertMore1(suffix1+";", 1,tableName,id);

        if(!b) {
            System.out.println("出错了----");
            System.exit(0);
        }else {
            // 清空上一次添加的数据
            suffix = new StringBuffer();
        }

    }


}
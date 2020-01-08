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


        int[] as = {	72,	129,	132,	211,	273,	274,	290,	292,
                312,	324,	328,	332,	499,	500,	501,	502,	503,
                504,	505,	506,	507,	508,	509,	510,	530,	556,
                570,	576,	585,	597,	601,	605,	618,	628,	657,
                686,	687,	789,	790,	791,	798,	799,	800,	804,
                805,	1517,	1583,	1799,	1800,	1801,

        };
//        System.out.println(idList.size());



        int m = 0;
        for (int a =0;a<as.length;a++) {
            m++;
            for(int i = 4;i<30;i++) {
                Mysql mysql1 = new Mysql();
                BaseDao baseDao = new BaseDao();
                Data data = new Data();
                UserDao userDao = new UserDao();
                String timeString = userDao.timeString(i);
                String tableName = new String("bool_" + as[a] + timeString);
//                String sql = alter_table(as[a],tableName);
//                System.out.println(sql);
//                if(sql!=null){
//                    baseDao.affectRowMore1(sql,1);
//                }
//                String sql = mysql1.getTblSQL(tableName);
//                baseDao.affectRowMore1(sql, -1);
                data.writeData(tableName, as[a]);
                System.out.println("正在写第"+m+"个测点");
            }

        }
    }
    public static String alter_table(int id ,String tableName ){

        String[] num = tableName.split("\\_");
        int year = Integer.parseInt(num[2]);
        int month = Integer.parseInt(num[3]);
        if(month <10){
            String newTableName = "bool_"+id+"_"+year+"_"+"0"+month;
            String sql ="alter table "+tableName+" rename to "+newTableName+";";
            return sql;
        }


        return null;
    }
}

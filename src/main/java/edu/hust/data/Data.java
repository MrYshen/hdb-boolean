package edu.hust.data;

import edu.hust.mysql.Mysql;
import hdb.*;

import java.util.List;

public class Data {

    //将数据写入数据库
    public void writeData(String tableName,int id){
        Hdb rdb =new Hdb();
        int ret = rdb.open("localhost", 22135, "root", "root1234");
        System.out.println("open result = "+ret);


        String[] num = tableName.split("\\_");
        int year = Integer.parseInt(num[1]);
        int month = Integer.parseInt(num[2]);

        int b = rdb.toUTC(2019, 12, 31, 6, 0, 0);

//        int b = rdb.toUTC(year ,month,1,0,0,0);

        if(month == 12){
            year = year+1;
            month = 0;
        }
        int c = rdb.toUTC(year ,month+1,1,0,0,0);

        FloatDataListHolder dataList = new FloatDataListHolder();

        ret = rdb.readFloatHisDataById(id,b,b+100,1, 2,dataList );
        System.out.println(ret);
        FloatData[] data1 = dataList.value;
        System.out.println(data1.length);

        Mysql mysql = new Mysql();
        mysql.insertBenchMark(data1,tableName,id);

        rdb.close();



    }

}

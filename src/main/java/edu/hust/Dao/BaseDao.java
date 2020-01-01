package edu.hust.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hdb.IndexTag;

public class BaseDao {

    private static String Driver = null;
    private static String URL = null;
    private static String USER = null;
    private static String PWD = null;

    private static Connection conn = null;
    private static PreparedStatement psmt = null;
    private static PreparedStatement psmt2 = null;
    public ResultSet rs = null;
    public int row = 0;

    static {
        Driver ="com.mysql.cj.jdbc.Driver" ;
        URL = "jdbc:mysql://218.197.228.243:3306/history_database?characterEncoding=utf8&useSSL=true&serverTimezone=UTC";
        USER = "remote";
        PWD = "123456";
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getConnection();
        try {
            conn.setAutoCommit(false);
//            psmt = conn.prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接到XJdatabase_02数据库
     * @return
     */
    private static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 多条记录插入操作
     */
    public boolean affectRowMore1(String sql, long flag) {
        try {
            getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.executeUpdate();
            System.out.println("操作数据库成功");
//            psmt.addBatch(sql);
//            // 执行操作
////            psmt.executeBatch(); // 执行Batch中的全部语句
////            conn.commit(); // 提交到数据库
////            for (int i : counts) {
////                if (i == 0) {
////                    conn.rollback();
////                    return false;
////                }
////            }
            System.out.println(flag);
            closeAll(-1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public void closeAll(long flag) {
        try {
            if (psmt != null && flag == -1) {
                psmt.close();
            }
            if (conn != null && flag == -1) {
                // 在完成批量操作后恢复默认的自动提交方式，提高程序的可扩展性
                conn.setAutoCommit(true);
                conn.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
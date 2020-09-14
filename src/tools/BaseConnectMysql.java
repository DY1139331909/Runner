package tools;
//连接mysql数据库

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseConnectMysql {
    public String dbUrlIP = null;
    public String sql = null;
    public String USER = null;
    public String PWD = null;

    public BaseConnectMysql(String IP, String user, String pwd, String sql) {
        this.dbUrlIP = IP;
        this.USER = user;
        this.PWD = pwd;
        this.sql = sql;
    }

    //连接数据库
    public Connection getConnection() {
        Connection conn = null;
        String DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://" + dbUrlIP;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    //关闭连接
    public void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://10.4.6.20:3306/testcenter_26";
        String USER = "root";
        String PWD = "root";

        try {
            //注册加载jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            conn = DriverManager.getConnection(DB_URL, USER, PWD);
            //执行语句
            stmt = conn.createStatement();
            String testUrl = "select * from project_table";
            ResultSet rs = stmt.executeQuery(testUrl);
            ResultSetMetaData rsmd = rs.getMetaData();
            //展开结果集
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

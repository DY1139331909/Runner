package tools;
//连接sqlserver数据库

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseConnectSQLServer {
    public String dbUrlIP = null;
    public String sql = null;
    public String dbUrlUser = null;
    public String dbUrlPwd = null;
    public String dbUrlData = null;


    public BaseConnectSQLServer(String IP, String user, String pwd, String sql) {
        this.dbUrlIP = IP.split("/")[0];
        this.dbUrlUser = user;
        this.dbUrlPwd = pwd;
        this.sql = sql;
        this.dbUrlData = IP.split("/")[1];
    }

    //连接数据库
    public Connection getConnection() {
        Connection conn = null;
        String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DB_URL = "jdbc:sqlserver://"+dbUrlIP+";databaseName="+dbUrlData
                +";user="+dbUrlUser+";password="+dbUrlPwd;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL);
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

        String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DB_URL = "jdbc:sqlserver://10.4.6.11:1433;databaseName=stocktrace;user=dbreader;password=dbreader108";

        try {
            //注册加载jdbc驱动
            Class.forName(JDBC_DRIVER);
            //打开连接
            conn = DriverManager.getConnection(DB_URL);
            //执行语句
            stmt = conn.createStatement();
            String testUrl = "select * from sw_autotestcase";
            ResultSet rs = stmt.executeQuery(testUrl);
            //展开结果集
            while (rs.next()) {
                System.out.println(rs.getString(1));
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

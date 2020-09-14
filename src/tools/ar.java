package tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ar {
    /**
     * @param sqlType  数据库类型
     * @param ip       数据库地址
     * @param user     数据库访问用户名
     * @param password 数据库访问密码
     * @param sql      数据库查询语句
     * @return 查询结果集，二维数组
     */

    public static String[][] getDatabase(String sqlType, String ip, String user, String password, String sql) {
        //返回结果的列表集合
        List list = new ArrayList();

        String[][] data = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        String JDBC_DRIVER = "";
        if (sqlType.equals("SQL Server")) {
            BaseConnectSQLServer connect = new BaseConnectSQLServer(ip, user, password, sql);
            JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            //注册加载jdbc驱动
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //打开连接
            conn = connect.getConnection();
        }
        if (sqlType.equals("mysql")) {
            BaseConnectMysql connect = new BaseConnectMysql(ip, user, password, sql);
            JDBC_DRIVER = "com.mysql.jdbc.Driver";
            //注册加载jdbc驱动
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //打开连接
            conn = connect.getConnection();
        }
        try {
            //执行语句
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            //获取结果集的字段的个数
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            //展开结果集
            while (rs.next()) {
                String[] temp = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    //结果集的某一元素的值
                    String value = String.valueOf(rs.getObject(i));
                    temp[i - 1] = value;
                }
                list.add(temp);
            }
            System.out.println(rsmd.getColumnCount());
            rs.close();
            stmt.close();
            conn.close();
            data = new String[list.size()][columnCount];
            for (int i = 0; i < list.size(); i++) {//循环遍历所有行
                data[i] = (String[]) list.get(i);//每行的列数
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }
}

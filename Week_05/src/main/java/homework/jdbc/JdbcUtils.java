package homework.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class JdbcUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";


    public static void executeQueryByStatement(String sql){
        Connection connection =null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=connection){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void executeQuery(String sql){
        Connection connection =null;
        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=connection){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return conn;
    }

    public static void executeUpdateTx(String name,int id) {
        Connection connection = null;
        String updateSql = "update student set name =? where id =?";
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//======================================Hikari==========================================================


    private static Connection getConnectionByHikari() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource dataSource = (DataSource) context.getBean("dataSource");
        return dataSource.getConnection();
    }

    public static void executeQueryByHikari(String sql){
        Connection connection =null;
        try {
            connection = getConnectionByHikari();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null!=connection){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void executeUpdateTxByHikari(String name,int id) {
        Connection connection = null;
        String updateSql = "update student set name =? where id =?";
        try {
            connection = getConnectionByHikari();
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

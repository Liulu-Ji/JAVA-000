package homework.jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.*;

public class JdbcMain {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";


    public static void main(String[] args) throws SQLException {
        //事务控制
        JdbcUtils.executeUpdateTx("MaMa2",1);
        //JDBC查询
        String sql = "select * from student;";
        //PreparedStatement
        JdbcUtils.executeQuery(sql);
        //Statement
        JdbcUtils.executeQueryByStatement(sql);



        //Hikari
        JdbcUtils.executeUpdateTxByHikari("Hikari",2);
        JdbcUtils.executeQueryByHikari(sql);


    }
}

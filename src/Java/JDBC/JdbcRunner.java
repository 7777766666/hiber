package Java.JDBC;

import Java.JDBC.Util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {

        Class<Driver> driverClass = Driver.class;

        String sql = """
insert into base.it.super_lol (color) VALUES ('yellow + red');

             
 
                """;

        try(Connection connection = ConnectionManager.open()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println(connection.getTransactionIsolation());
            System.out.println(connection.getSchema());
            System.out.println(statement);
//            boolean execute = statement.execute(sql);
            var execute = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                int number = resultSet.getInt("id");
                System.out.println(number);
            }
//            System.out.println(statement.getUpdateCount());
//            int executeUpdate = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
//            ResultSet resultSet = statement.executeQuery(sql);




        }



    }
}

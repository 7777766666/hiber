package Java.JDBC;

import Java.JDBC.Util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {

        String superLol = "'ccc' or '' = ''";
        System.out.println(getSuper(superLol));
        System.out.println(getSuperId(superLol));

    }

    private static List<String> getSuper(String x) throws SQLException {

        String sql = """
                
                select color from base.it.super_lol where color = %s;

                """.formatted(x);

        List<String> listSuper = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                listSuper.add(resultSet.getString("color"));
//                integerList.add(resultSet.getInt("id"));
            }


        }
        return listSuper;
    }

    private static List<Integer> getSuperId(String x) throws SQLException {

        String sql = """
                select id from base.it.super_lol where color = %s;

                """.formatted(x);

        List<String> listSuper = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        try (Connection connection = ConnectionManager.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
//                listSuper.add(resultSet.getString("color"));
                integerList.add(resultSet.getInt("id"));
            }


        }
        return integerList;
    }

}

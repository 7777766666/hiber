package Java.JDBC;

import Java.JDBC.Util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {

        String superLol = "'ccc' or '' = ''";
//        System.out.println(getSuper(superLol));
//        System.out.println(getSuperId(superLol));
        Long x = 15L;
        Long z = 196L;
//        System.out.println(getPrep(x));
//        System.out.println(getBetween(x, z));
        metaData();

    }

    private static void metaData() throws SQLException {
        try (Connection connection = ConnectionManager.open()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet catalogs = metaData.getCatalogs();
            String tableCat = null;
            while (catalogs.next()) {
                tableCat = catalogs.getString("TABLE_CAT");
            }
            ResultSet schemas = metaData.getSchemas();
            while (schemas.next()) {
                String tableSchem = schemas.getString("TABLE_SCHEM");
                ResultSet tables = metaData.getTables(tableCat, tableSchem, "%", new String[] {"TABLE"});
                if (tableSchem.equals("it")) {
                    while (tables.next()) {
                        System.out.println(tables.getString("TABLE_NAME"));
                    }
                }
            }
        }
    }

    private static List<String> getPrep(Long x) throws SQLException {
        List<String> stringList = new ArrayList<>();
        String sql = """
                select color from base.it.super_lol where id = ? ;
                """;

        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLargeMaxRows(2);
            preparedStatement.setLong(1, x);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stringList.add(resultSet.getString("color"));
            }
        }
        return stringList;
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

    private static List<String> getBetween(Long l1, Long l2) throws SQLException {
        List<String> stringList = new ArrayList<>();

        String str = """
                select color from base.it.super_lol where id BETWEEN ? and ?;
                """;

        try (Connection connection = ConnectionManager.open()) {

            PreparedStatement preparedStatement = connection.prepareStatement(str);
            System.out.println(preparedStatement);
            preparedStatement.setFetchSize(1);
            preparedStatement.setMaxRows(5);
            preparedStatement.setQueryTimeout(15);

            preparedStatement.setLong(1, l1);
            System.out.println(preparedStatement);
            preparedStatement.setLong(2, l2);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stringList.add(resultSet.getString("color"));
            }

        }
        return stringList;

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

package Java.JDBC;

import Java.JDBC.Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionRunner {

    public static void main(String[] args) throws SQLException {
        int super_lolId = 13;
        String deleteSuper_lol = " delete from base.it.super_lol where id = ?";
        String deleteTest = "delete from base.it.test where super_lol_id = ?";
        Connection connection = null;
        PreparedStatement deleteSuperStatement = null;
        PreparedStatement delTestStatement = null;
        try  {
            connection = ConnectionManager.open();
            connection.setAutoCommit(false);

            deleteSuperStatement = connection.prepareStatement(deleteSuper_lol);
            delTestStatement = connection.prepareStatement(deleteTest);
            deleteSuperStatement.setInt(1, super_lolId);
            delTestStatement.setInt(1, super_lolId);


            delTestStatement.executeUpdate();
            if (true){
                throw new RuntimeException("lol");
            }
            deleteSuperStatement.executeUpdate();
            connection.commit();
        }catch (Exception e){
            if (connection != null){
                connection.rollback();


            }
            throw e;
        }finally {
            if (connection != null){
                connection.close();
            }
            if (delTestStatement != null){
                delTestStatement.close();
            }
            if (deleteSuperStatement != null){
                deleteSuperStatement.close();
            }
        }

    }
}

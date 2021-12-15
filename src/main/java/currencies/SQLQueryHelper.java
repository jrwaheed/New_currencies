package currencies;

import java.sql.*;

public class SQLQueryHelper {

    public void updateTables() throws SQLException {

        String sql = "CALL buildOut();";

        String myURL = "jdbc:mysql://localhost:3306/Exchange";
        String user = "root";
        String password = "TheHulk1*";

        try (
        Connection conn = DriverManager.getConnection(myURL, user, password);
        CallableStatement stmt = conn.prepareCall(sql);)
        {
            stmt.execute();
            stmt.close();
            System.out.println("Successfully executed mysql call buildOut.");
        } catch
            (SQLException ex)
        {
            System.out.println("Failure to call buildOut()");
        }
    }


    public void resetAutoIncrement() throws SQLException {

        String sql = "CALL resetAutoIncrement();";

        String myURL = "jdbc:mysql://localhost:3306/Exchange";
        String user = "root";
        String password = "TheHulk1*";

        try (
                Connection conn = DriverManager.getConnection(myURL, user, password);
                CallableStatement stmt = conn.prepareCall(sql);)
        {
            stmt.execute();
            stmt.close();
            System.out.println("Successfully executed mysql call resetAutoIncrement.");
        } catch
        (SQLException ex)
        {
            System.out.println("Failure to call resetAutoIncrement()");
        }
    }

    public void clearCurrencyTableAndRetain() throws SQLException {

        String sql = "CALL clear_and_save_currency_table();";

        String myURL = "jdbc:mysql://localhost:3306/Exchange";
        String user = "root";
        String password = "TheHulk1*";

        try (
                Connection conn = DriverManager.getConnection(myURL, user, password);
                CallableStatement stmt = conn.prepareCall(sql);)
        {
            stmt.execute();
            stmt.close();
            System.out.println("Successfully executed mysql call clear_and_save_currency_table.");
        } catch
        (SQLException ex)
        {
            System.out.println("Failure to call clear_and_save_currency_table()");
        }
    }

}

package currencies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
public class SQLQueryHelper {


@RequestMapping(value = "/index2", method = RequestMethod.GET)
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
            System.out.println("Successfully executed mysql call buildOut:" + new Date());
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

    public HashMap findArbitrage(){

        HashMap hashOfPossibilities = new HashMap();

        String sql = "CALL findArbitrage();";

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
        return hashOfPossibilities;
    }
}

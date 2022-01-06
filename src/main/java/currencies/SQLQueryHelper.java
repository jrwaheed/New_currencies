package currencies;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;

@RestController
@CrossOrigin
public class SQLQueryHelper {

    private static final String myURL = "jdbc:mysql://192.168.56.103:3306/Exchange";
    private static final String user = "jamal";
    private static final String password = "Ubuntu";



@RequestMapping(value = "/index2", method = RequestMethod.GET)
    public void updateTables() {

        String sql = "CALL buildOut();";

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


    @RequestMapping(value = "/index3", method = RequestMethod.GET)
    public void findArbitrage(){

        String sql = "CALL findArbitrage();";

        try (
                Connection conn = DriverManager.getConnection(myURL, user, password);
                CallableStatement stmt = conn.prepareCall(sql);)
        {
            stmt.execute();
            stmt.close();
            System.out.println("Successfully executed mysql findArbitrage." + new Date());
        } catch
        (SQLException ex)
        {
            System.out.println("Failure to call findArbitrage()");
        }
    }


    public void resetAutoIncrement() {

        String sql = "CALL resetAutoIncrement();";

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

    public void clearCurrencyTableAndRetain() {

        String sql = "CALL clear_and_save_currency_table();";

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

package currencies;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
public class SQLQueryHelper {
/*
    Campus
    private static final String myURL = "jdbc:mysql://192.168.56.103:3306/Exchange";
    private static final String user = "jamal";
    private static final String password = "Ubuntu";

    Home
    private static final String myURL = "jdbc:mysql://localhost:3306/Exchange";
    private static final String user = "root";
    private static final String password = "TheHulk1*";
*/
    private static final String myURL = "jdbc:mysql://localhost:3306/Exchange";
    private static final String user = "root";
    private static final String password = "TheHulk1*";




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
            System.out.println("Successfully executed mysql findArbitrage. " + new Date());
        } catch
        (SQLException ex)
        {
            System.out.println("Failure to call findArbitrage()");
        }
    }

    @CrossOrigin("http://127.0.0.1:8082/") //Home
    //@CrossOrigin("http://127.0.0.1:5500/") //Campus

    @RequestMapping(value = "/index4", method = RequestMethod.GET)
    public String getSQLArbitrageValues() {


        String sql = "SELECT * FROM Triangle;";
        ArrayList<Combination> ArbCombos = new ArrayList<>();

        String JSON_ArbCombos = null;
        try {
            Connection conn = DriverManager.getConnection(myURL, user, password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combination combination = new Combination(rs.getString("LegOne"),
                        rs.getString("LegTwo"), rs.getString("LegThree"),
                        rs.getBigDecimal("LegOneValue"), rs.getBigDecimal("LegTwoValue"),
                        rs.getBigDecimal("LegThreeValue"), rs.getBigDecimal("LegTotal"),
                        rs.getBigDecimal("Delta"), rs.getString("FullCombo"));

                ArbCombos.add(combination);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JSON_ArbCombos = objectMapper.writeValueAsString(ArbCombos);

            System.out.println(JSON_ArbCombos);
            rs.close();
            stmt.close();
            conn.close();


            System.out.println("Successfully executed arbitrage List." + new Date());
        } catch
        (SQLException | JsonProcessingException ex) {
            System.out.println("Failure to executed arbitrage List.");
        }
        return JSON_ArbCombos;
    }


    @CrossOrigin("http://127.0.0.1:8082/") //Home
    //@CrossOrigin("http://127.0.0.1:5500/") //Campus

    @RequestMapping(value = "/index5", method = RequestMethod.GET)
    public String getSQLScatterValues() {


        String sql = "SELECT legOne FROM Triangle;";
        ArrayList<Combination> ScatterCombos = new ArrayList<>();

        String JSON_ScatterCombos = null;
        try {
            Connection conn = DriverManager.getConnection(myURL, user, password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combination combination = new Combination(rs.getString("LegOne"));
                ScatterCombos.add(combination);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JSON_ScatterCombos = objectMapper.writeValueAsString(ScatterCombos);

            System.out.println(JSON_ScatterCombos);
            rs.close();
            stmt.close();
            conn.close();


            System.out.println("Successfully gathered Scatter Data List." + new Date());
        } catch
        (SQLException | JsonProcessingException ex) {
            System.out.println("Failure to gather Scatter Data.");
        }
        return JSON_ScatterCombos;
    }




    @CrossOrigin("http://127.0.0.1:8082/") //Home
    //@CrossOrigin("http://127.0.0.1:5500/") //Campus

    @RequestMapping(value = "/index6", method = RequestMethod.GET)
    public String sendSQLScatterSelections(@RequestParam String scatterSelection) {


        String sql = "SELECT combo, value, time FROM " + scatterSelection;
        ArrayList<Currency> ScatterButtonSelect = new ArrayList<>();

        String JSON_ScatterButtonSelect = null;
        try {
            Connection conn = DriverManager.getConnection(myURL, user, password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Currency currency = new Currency(rs.getString("combo"),
                        rs.getBigDecimal("value"),rs.getDate("time"));
                ScatterButtonSelect.add(currency);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            JSON_ScatterButtonSelect = objectMapper.writeValueAsString(ScatterButtonSelect);

            System.out.println(JSON_ScatterButtonSelect);
            rs.close();
            stmt.close();
            conn.close();


            System.out.println("Successfully gathered Scatter Data points for buttons." + new Date());
        } catch
        (SQLException | JsonProcessingException ex) {
            System.out.println("Failure to gather Scatter Points.");
        }
        return JSON_ScatterButtonSelect;
    }


    public void getSQLArbitrageValuesTester() {

        String sql = "SELECT * FROM Triangle;";
        ArrayList<Combination> ArbCombos = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(myURL, user, password);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Combination combination = new Combination(rs.getString("LegOne"),
                        rs.getString("LegTwo"), rs.getString("LegThree"),
                        rs.getBigDecimal("LegOneValue"), rs.getBigDecimal("LegTwoValue"),
                        rs.getBigDecimal("LegThreeValue"), rs.getBigDecimal("LegTotal"),
                        rs.getBigDecimal("Delta"), rs.getString("FullCombo"));

                ArbCombos.add(combination);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            String JSON_ArbCombos = objectMapper.writeValueAsString(ArbCombos);

            System.out.println(JSON_ArbCombos);
            rs.close();
            stmt.close();
            conn.close();

            System.out.println("Successfully executed arbitrage List." + new Date());
        } catch
        (SQLException | JsonProcessingException ex) {
            System.out.println("Failure to executed arbitrage List.");
        }
    }
}

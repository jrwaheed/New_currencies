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
    private static final String myURL = "jdbc:mysql://192.168.56.103:3306/Exchange";
    private static final String user = "jamal";
    private static final String password = "Ubuntu";

*/
    private static final String myURL = "jdbc:mysql://localhost:3306/Exchange";
    private static final String user = "root";
    private static final String password = "TheHulk1*";


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

    //produces = MediaType.APPLICATION_JSON_VALUE
    @CrossOrigin("http://127.0.0.1:8082/")
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

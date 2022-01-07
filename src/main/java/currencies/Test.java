package currencies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

 //SQLQueryHelper sqlQueryHelper = new SQLQueryHelper();
 //sqlQueryHelper.getSQLArbitrageValues();

public class Test {
    private static final String myURL = "jdbc:mysql://localhost:3306/Exchange";
    private static final String user = "root";
    private static final String password = "TheHulk1*";


    public static void main(String[] args) throws SQLException {

        SQLQueryHelper sqlQueryHelper = new SQLQueryHelper();
        sqlQueryHelper.getSQLArbitrageValuesTester();


    }

}

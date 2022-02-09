package currencies;

import currencies.Currency;
import currencies.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.math.BigDecimal;

import java.time.Instant;
import java.util.Date;


@Controller
@CrossOrigin
public class UpdateController {

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
    private static final String myURL = "jdbc:mysql://192.168.56.103:3306/Exchange";
    private static final String user = "jamal";
    private static final String password = "Ubuntu";

    private final Logger log = LoggerFactory.getLogger(currencies.MainController.class);

    public final Repository repository;



    public UpdateController(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    private currencies.UpdateController updateController;

    /**
     * Retrieves the target map created in javascript.
     * @param
     * @return
     */


    @PostMapping("/index8")
    public ResponseEntity<Map<String,BigDecimal>> retrieveCurrencyUpdate (@RequestBody Map<String,BigDecimal> currencyMap) throws SQLException {
        log.info("REST request to update all currencies {}", currencyMap.toString());

        updateCurrencyFromMap(currencyMap);

        return new ResponseEntity<>(currencyMap, HttpStatus.CREATED);
    }


    private void updateCurrencyFromMap (Map<String,BigDecimal> currencyMap) throws SQLException {
        for (int i = 1; i < currencyMap.size(); i++) {

            Currency newCurrency = new Currency();

            String newBase = (String) currencyMap.keySet().toArray()[0];
            String newTicker = (String) currencyMap.keySet().toArray()[i];
            BigDecimal newValue = (BigDecimal) currencyMap.values().toArray()[i];


            newCurrency.setBase(newBase);
            newCurrency.setTicker(newTicker);
            newCurrency.setValue(newValue);
            newCurrency.setCombo(newTicker + "_"+ newBase);
            newCurrency.setTime(Timestamp.valueOf(LocalDateTime.now()));
            //newCurrency.setTime(Instant.now().getEpochSecond());

            //repository.save(newCurrency);

            String sql = "INSERT INTO " + newCurrency.getCombo() + " (base, ticker, time, value, combo) " +
                    "VALUES ('" + newCurrency.getBase() + "', '" + newCurrency.getTicker() + "', '" +newCurrency.getTime() +
                    "', " + newCurrency.getValue() +  ", '" +newCurrency.getCombo() + "');";

            try (
                    Connection conn = DriverManager.getConnection(myURL, user, password);
                    CallableStatement stmt = conn.prepareCall(sql);)
            {
                stmt.execute();
                stmt.close();
                System.out.println("The currency combo " + newCurrency.getCombo() + " has been updated in MYSQL");;
            } catch
            (SQLException ex) {
                System.out.println("Failure to update SQL");
            }
        }
    }
}

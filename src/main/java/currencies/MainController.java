package currencies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.math.BigDecimal;


@Controller
@CrossOrigin
public class MainController {

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

    private final Logger log = LoggerFactory.getLogger(MainController.class);

    public final Repository repository;


    public MainController(Repository repository) {
        this.repository = repository;
    }

    @Autowired
    private MainController mainController;

    /**
     * Retrieves the target map created in javascript.
     * @param
     * @return
     */
    @PostMapping("/index1")
    public ResponseEntity<Map<String,BigDecimal>> retrieveCurrency (@RequestBody Map<String,BigDecimal> currencyMap) throws SQLException {
        log.info("REST request to retrieve target currency: {}", currencyMap.toString());

        createCurrencyFromMap(currencyMap);

        return new ResponseEntity<>(currencyMap, HttpStatus.CREATED);
    }



    private void createCurrencyFromMap (Map<String,BigDecimal> currencyMap) throws SQLException {
        for (int i = 1; i < currencyMap.size(); i++) {

            Currency newCurrency = new Currency();

            String newBase = (String) currencyMap.keySet().toArray()[0];
            String newTicker = (String) currencyMap.keySet().toArray()[i];
            BigDecimal newValue = (BigDecimal) currencyMap.values().toArray()[i];


            newCurrency.setBase(newBase);
            newCurrency.setTicker(newTicker);
            newCurrency.setValue(newValue);
            newCurrency.setCombo(newTicker + "_"+ newBase);

            System.out.println("The ticker " + newCurrency.getTicker() + " has the value " + newCurrency.getValue() +
                    " for base " + newCurrency.getBase());
            repository.save(newCurrency);
        }
    }


}

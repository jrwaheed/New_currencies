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

import java.util.Map;

@Controller
@CrossOrigin
public class MainController {

    private final Logger log = LoggerFactory.getLogger(MainController.class);

    private final  Repository repository;

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
    public ResponseEntity<Map<String,Double>> retrieveCurrency (@RequestBody Map<String,Double> currencyMap) {
        log.info("REST request to retrieve target currency: {}", currencyMap.toString());

        createCurrencyFromMap(currencyMap);

        return new ResponseEntity<>(currencyMap, HttpStatus.CREATED);
    }


    private void createCurrencyFromMap (Map<String,Double> currencyMap) {
        for (int i = 1; i < currencyMap.size(); i++) {
            Currency newCurrency = new Currency();

            String newBase = (String) currencyMap.keySet().toArray()[0];
            String newTicker = (String) currencyMap.keySet().toArray()[i];
            Double newValue = (Double) currencyMap.values().toArray()[i];


            newCurrency.setBase(newBase);
            newCurrency.setTicker(newTicker);
            newCurrency.setValue(newValue);


            System.out.println("The ticker " + newCurrency.getTicker() + " And the value " + newCurrency.getValue() +
                    " for base " + newCurrency.getBase());
            repository.save(newCurrency);
        }


    }
}

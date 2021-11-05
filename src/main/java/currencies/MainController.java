package currencies;

import org.jboss.jandex.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
        log.info("REST request to retrieve currency: {}", currencyMap.toString());

        createCurrencyFromMap(currencyMap);

        System.out.println("\n total in the repository" + repository.count());
        return new ResponseEntity<>(currencyMap, HttpStatus.CREATED);
    }

    private void createCurrencyFromMap (Map<String,Double> currencyMap) {
        for (Map.Entry<String, Double> entry : currencyMap.entrySet()) {
            Currency newCurrency = new Currency();
            newCurrency.setTicker(entry.getKey());
            newCurrency.setValue(entry.getValue());

            System.out.println("The ticker " + newCurrency.getTicker() + " And the value " + newCurrency.getValue() +
                    " for each");
            repository.save(newCurrency);
        }


    }
}

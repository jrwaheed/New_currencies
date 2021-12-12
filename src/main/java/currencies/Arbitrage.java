package currencies;


import org.jboss.jandex.Main;

import java.util.Map;

public class Arbitrage {


    private Map <String, Double> grandMap;

    public Arbitrage() {
        this.grandMap = grandMap;
    }

    public void createGrandMap(Map<String, Double> freshMap) {
        for (Map.Entry<String, Double> entry : freshMap.entrySet()) {
            this.grandMap.put(entry.getKey(), entry.getValue());
        }
    }

    public Map<String, Double> getGrandMap() {
        return grandMap;
    }

    public void setGrandMap(Map<String, Double> grandMap) {
        this.grandMap = grandMap;
    }

}



package currencies;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class Combination {

    private String legOne;
    private String legTwo;
    private String legThree;
    private BigDecimal legOneValue;
    private BigDecimal legTwoValue;
    private BigDecimal legThreeValue;
    private BigDecimal legTotal;
    private BigDecimal delta;
    private String fullCombo;


    public Combination() {
    }

    public Combination(String legOne, String legTwo, String legThree, BigDecimal legOneValue,
                       BigDecimal legTwoValue, BigDecimal legThreeValue, BigDecimal legTotal, BigDecimal delta, String fullCombo) {
        this.legOne = legOne;
        this.legTwo = legTwo;
        this.legThree = legThree;
        this.legOneValue = legOneValue;
        this.legTwoValue = legTwoValue;
        this.legThreeValue = legThreeValue;
        this.legTotal = legTotal;
        this.delta = delta;
        this.fullCombo = fullCombo;
    }

    public String getLegOne() {
        return legOne;
    }

    public void setLegOne(String legOne) {
        this.legOne = legOne;
    }

    public String getLegTwo() {
        return legTwo;
    }

    public void setLegTwo(String legTwo) {
        this.legTwo = legTwo;
    }

    public String getLegThree() {
        return legThree;
    }

    public void setLegThree(String legThree) {
        this.legThree = legThree;
    }

    public BigDecimal getLegOneValue() {
        return legOneValue;
    }

    public void setLegOneValue(BigDecimal legOneValue) {
        this.legOneValue = legOneValue;
    }

    public BigDecimal getLegTwoValue() {
        return legTwoValue;
    }

    public void setLegTwoValue(BigDecimal legTwoValue) {
        this.legTwoValue = legTwoValue;
    }

    public BigDecimal getLegThreeValue() {
        return legThreeValue;
    }

    public void setLegThreeValue(BigDecimal legThreeValue) {
        this.legThreeValue = legThreeValue;
    }

    public BigDecimal getLegTotal() {
        return legTotal;
    }

    public void setLegTotal(BigDecimal legTotal) {
        this.legTotal = legTotal;
    }

    public BigDecimal getDelta() {
        return delta;
    }

    public void setDelta(BigDecimal delta) {
        this.delta = delta;
    }

    public String getFullCombo() {
        return fullCombo;
    }

    public void setFullCombo(String fullCombo) {
        this.fullCombo = fullCombo;
    }
}

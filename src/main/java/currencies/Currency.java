package currencies;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Currency {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String ticker;
    private double value;
    private String relation;


    public Currency (){
    }


    public Currency(Long id, String ticker, double value, String relation) {
        this.id = id;
        this.ticker = ticker;
        this.value = value;
        this.relation = relation;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }


}

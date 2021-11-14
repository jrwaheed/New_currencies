package currencies;


import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;

@Entity
public class Currency {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String ticker;
    private double value;
    private String base;

    @Column(name = "time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


    public Currency (){
    }


    public Currency(Long id, String ticker, double value, String base, Date time) {
        this.id = id;
        this.ticker = ticker;
        this.value = value;
        this.base = base;
        this.time = time;


    }

    @PrePersist
    public void persistTimeStamps() {
        time = new Date();
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
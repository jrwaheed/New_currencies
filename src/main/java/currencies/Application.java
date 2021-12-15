package currencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import currencies.SQLQueryHelper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.sql.SQLException;



@SpringBootApplication
public class Application {

    public SQLQueryHelper sqlQueryHelper = new SQLQueryHelper();


    public static void main(String[] args) throws SQLException {

        SQLQueryHelper sqlQueryHelper = new SQLQueryHelper();

        SpringApplication.run(Application.class, args);
        sqlQueryHelper.resetAutoIncrement();
        sqlQueryHelper.clearCurrencyTableAndRetain();

    }


    private void updateSQLTables () throws SQLException {
        sqlQueryHelper.updateTables();
    }


}

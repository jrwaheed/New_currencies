package currencies;

import org.springframework.data.repository.CrudRepository;
import java.math.BigDecimal;

public interface
Repository extends CrudRepository<Currency, BigDecimal> {
}

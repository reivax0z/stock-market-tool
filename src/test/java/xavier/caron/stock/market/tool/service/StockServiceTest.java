package xavier.caron.stock.market.tool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import xavier.caron.stock.market.tool.dto.Stock;
import xavier.caron.stock.market.tool.service.impl.StockServiceImplMock;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")
public class StockServiceTest {
    private StockService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        service = new StockServiceImplMock();
    }

    @Test
    void testGetAllStocks() {
        // when
        Map<String, Stock> stocks = service.getAllStocks();

        // then
        assertThat(stocks).isNotEmpty();
        assertThat(stocks.size()).isEqualTo(3);
    }

    @Test
    void testGetStock() {
        // when
        Stock stock = service.getStock("MSFT");

        // then
        assertThat(stock).isNotNull();
        assertThat(stock.getName()).isEqualTo("Microsoft");
        assertThat(stock.getPrice()).isEqualTo(109.41);
    }
}

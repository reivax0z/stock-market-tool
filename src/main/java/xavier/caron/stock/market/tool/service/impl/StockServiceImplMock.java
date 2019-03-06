package xavier.caron.stock.market.tool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import xavier.caron.stock.market.tool.dto.Stock;
import xavier.caron.stock.market.tool.service.StockService;

import java.util.HashMap;
import java.util.Map;

/**
 * Stock Service Impl - Mock.
 *
 * Using mocked service with the "local" profile, allowing for easy testing without proper market API connection,
 */
@Slf4j
@Profile({"local"})
@Service
public class StockServiceImplMock implements StockService {

    private final Map<String, Stock> fakeStocks = new HashMap<>();

    public StockServiceImplMock() {
        fakeStocks.put("MSFT", new Stock("Microsoft", "MSFT", 109.41));
        fakeStocks.put("GOOG", new Stock("Alphabet", "GOOG", 1096.97));
        fakeStocks.put("TEAM", new Stock("Atlassian", "TEAM", 103.04));
    }

    @Override public Map<String, Stock> getAllStocks() {
        return fakeStocks;
    }

    @Override public Stock getStock(String symbol) {
        return fakeStocks.get(symbol);
    }
}

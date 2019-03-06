package xavier.caron.stock.market.tool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xavier.caron.stock.market.tool.dto.Quote;
import xavier.caron.stock.market.tool.dto.Stock;
import xavier.caron.stock.market.tool.service.StockService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Profile({"dev"})
@Service
public class StockServiceImplAlphaVantage implements StockService {

    @Value("${config.api.key}")
    private String apiKey;

    private String apiEndpoint = "https://www.alphavantage.co/query?";

    private List<String> stocksKeys = new ArrayList<>(Arrays.asList("MSFT", "GOOG", "TEAM"));

    private RestTemplate restTemplate;

    public StockServiceImplAlphaVantage(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override public Map<String, Stock> getAllStocks() {
        log.info("Requesting to get all stocks quote");
        log.info("Using API endpoint={} with key={}", apiEndpoint, apiKey);
        Map<String, Stock> stocks = new HashMap<>();

        stocksKeys.forEach(symbol -> {
            // function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo

            log.info("Requesting to get stock quote - {}", symbol);
            Quote quote = restTemplate.getForObject(
                    apiEndpoint + "function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey,
                    Quote.class);
            log.info("Retrieved stock quote - {} - {}", symbol, quote);
            stocks.put(symbol, new Stock("", symbol, quote.getGlobalQuote().getPrice()));
        });

        log.info("Successfully retrieved all stocks quote");
        return stocks;
    }

    @Override public Stock getStock(String symbol) {
        return null;
    }
}

package xavier.caron.stock.market.tool.service;

import xavier.caron.stock.market.tool.dto.Stock;

import java.util.Map;

/**
 * Stock Service.
 */
public interface StockService {

    Map<String, Stock> getAllStocks();

    Stock getStock(String symbol);
}

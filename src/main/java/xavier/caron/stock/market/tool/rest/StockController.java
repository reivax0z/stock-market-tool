package xavier.caron.stock.market.tool.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xavier.caron.stock.market.tool.dto.Stock;
import xavier.caron.stock.market.tool.service.StockService;

import java.util.Map;

/**
 * Stock Controller.
 */
@Slf4j
@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
@RequestMapping(value = {"/stock"})
public class StockController {

    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Retrieve the Stock of a given symbol.
     * @param symbol the symbol to look for (ie, GOOG)
     * @return {@link Stock}
     */
    @CrossOrigin(origins = {
            "http://localhost:4200",
            "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
    @RequestMapping(
            value = "/{symbol}",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.GET
    )
    public ResponseEntity<Stock> getStock(@PathVariable("symbol") String symbol) {
        return ResponseEntity.ok(stockService.getStock(symbol));
    }

    /**
     * Retrieve the list of Stocks.
     * @return Map<String, Stock>
     */
    @CrossOrigin(origins = {
            "http://localhost:4200",
            "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
    @RequestMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.GET
    )
    public ResponseEntity<Map<String, Stock>> getStocks() {

        return ResponseEntity.ok(stockService.getAllStocks());
    }
}

package xavier.caron.stock.market.tool.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xavier.caron.stock.market.tool.dto.Order;
import xavier.caron.stock.market.tool.dto.Portfolio;
import xavier.caron.stock.market.tool.exception.InvalidOrderException;
import xavier.caron.stock.market.tool.service.PortfolioService;

/**
 * Portfolio Controller.
 */
@Slf4j
@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
@RequestMapping(value = {"/portfolio"})
public class PortfolioController {

    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    /**
     * Retrieve the current state of the portfolio.
     * @return {@link Portfolio}
     */
    @CrossOrigin(origins = {
            "http://localhost:4200",
            "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
    @RequestMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.GET
    )
    public ResponseEntity<Portfolio> getPortfolio() {
        log.info("Getting portfolio...");
        return ResponseEntity.ok(portfolioService.getCurrentPosition());
    }

    /**
     * Place an Order.
     * @param order the order to place
     */
    @CrossOrigin(origins = {
            "http://localhost:4200",
            "http://reivax0z.stock-market-tool.s3-website-ap-southeast-2.amazonaws.com"})
    @RequestMapping(
            value = "/",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.POST
    )
    public ResponseEntity<Portfolio> placeOrder(@RequestBody Order order) throws InvalidOrderException {
        log.info("Placing order... - {}", order.getUuid());
        return ResponseEntity.ok(portfolioService.placeOrder(order));
    }

    /**
     * Deposit Cash to Portfolio.
     * @param amount the amount to deposit
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            value = "/cash",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.POST
    )
    public ResponseEntity<Portfolio> depositCash(@RequestBody Double amount) throws InvalidOrderException {
        log.info("Depositing cash... - {}", amount);
        return ResponseEntity.ok(portfolioService.depositCash(amount));
    }

    /**
     * Deposit Cash to Portfolio.
     * @param amount the amount to deposit
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(
            value = "/cash-withdraw",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            method = RequestMethod.POST
    )
    public ResponseEntity<Portfolio> withdrawCash(@RequestBody Double amount) throws InvalidOrderException {
        log.info("Withdrawing cash... - {}", amount);
        return ResponseEntity.ok(portfolioService.removeCash(amount));
    }
}

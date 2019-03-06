package xavier.caron.stock.market.tool.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xavier.caron.stock.market.tool.dto.Order;
import xavier.caron.stock.market.tool.dto.Portfolio;
import xavier.caron.stock.market.tool.dto.Share;
import xavier.caron.stock.market.tool.exception.InvalidOrderException;
import xavier.caron.stock.market.tool.service.PortfolioService;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Portfolio Service Impl - In Memory.
 * <p>
 * Using only 1 portfolio available for the app. Stays in memory until the server reboot.
 */
@Slf4j
@Service
public class PortfolioServiceImplInMemory implements PortfolioService {

    private static final double DEFAULT_CASH_INITIAL = 10000.00;

    private Portfolio portfolio;

    public PortfolioServiceImplInMemory() {
        this.portfolio = new Portfolio();
        this.portfolio.setCashBalance(DEFAULT_CASH_INITIAL);
        this.portfolio.setHistoryOrders(new ArrayList<>());
        this.portfolio.setShareHoldings(new ArrayList<>());
    }

    @Override public Portfolio getCurrentPosition() {
        return portfolio;
    }

    @Override public Portfolio placeOrder(Order order) throws InvalidOrderException {
        log.info("Requesting to place order - {}", order.getUuid());

        // 1 - validations
        switch (order.getAction()) {
            case BUY:
                if (this.portfolio.getCashBalance() < order.getQuantity() * order.getStock().getPrice()) {
                    throw new InvalidOrderException("Does not have enough cash to place order");
                }
                break;
            case SELL:
                this.portfolio.getShareHoldings()
                        .stream()
                        .filter(share -> share.getUuid().equals(order.getShareUuid()))
                        .filter(share -> share.getStock().getSymbol().equals(order.getStock().getSymbol()))
                        .filter(share -> share.getQuantity() >= order.getQuantity())
                        .findFirst()
                        .orElseThrow(() -> new InvalidOrderException("Does not have enough shares to place order"));
                break;
        }

        // 2 - make order
        order.setPricePaid(order.getQuantity() * order.getStock().getPrice());

        this.portfolio.getHistoryOrders().add(order);

        if (Order.Action.BUY.equals(order.getAction())) {
            this.portfolio.getShareHoldings().add(
                    Share.builder()
                            .stock(order.getStock())
                            .quantity(order.getQuantity())
                            .pricePaid(order.getPricePaid()).uuid(UUID.randomUUID().toString())
                            .build()
            );
            this.portfolio.setCashBalance(this.portfolio.getCashBalance() - order.getPricePaid());
        } else {
            this.portfolio.getShareHoldings()
                    .stream()
                    .filter(share -> share.getUuid().equals(order.getShareUuid()))
                    .findFirst()
                    .ifPresent(share -> {
                        share.setQuantity(share.getQuantity() - order.getQuantity());
                        if (share.getQuantity() == 0) {
                            this.portfolio.getShareHoldings().remove(share);
                        }
                        share.setPricePaid(share.getPricePaid() - order.getPricePaid());
                    });
            this.portfolio.setCashBalance(this.portfolio.getCashBalance() + order.getPricePaid());
        }

        log.info("Successfully placed order - {}", order.getUuid());
        return this.portfolio;
    }

    @Override public Portfolio depositCash(Double amount) throws InvalidOrderException {
        log.info("Requesting to deposit cash - {}", amount);

        if (amount <= 0) {
            throw new InvalidOrderException("Cannot deposit less than 0");
        }

        this.portfolio.setCashBalance(this.portfolio.getCashBalance() + amount);

        log.info("Successfully deposited cash - {}", amount);
        return this.portfolio;
    }

    @Override public Portfolio removeCash(Double amount) throws InvalidOrderException {
        log.info("Requesting to removing cash - {}", amount);

        double newBalance = this.portfolio.getCashBalance() - amount;
        if (newBalance <= 0) {
            throw new InvalidOrderException("Cannot withdraw all or more than balance amount");
        }

        this.portfolio.setCashBalance(newBalance);

        log.info("Successfully removed cash - {}", amount);
        return this.portfolio;
    }
}

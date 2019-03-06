package xavier.caron.stock.market.tool.service;

import xavier.caron.stock.market.tool.dto.Order;
import xavier.caron.stock.market.tool.dto.Portfolio;
import xavier.caron.stock.market.tool.exception.InvalidOrderException;

/**
 * Portfolio Service.
 */
public interface PortfolioService {

    Portfolio getCurrentPosition();

    Portfolio placeOrder(Order order) throws InvalidOrderException;

    Portfolio depositCash(Double amount) throws InvalidOrderException;

    Portfolio removeCash(Double amount) throws InvalidOrderException;
}

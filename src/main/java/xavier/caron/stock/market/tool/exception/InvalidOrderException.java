package xavier.caron.stock.market.tool.exception;

/**
 * Invalid Order Exception.
 *
 * Thrown when trying to buy and not having enough cash.
 */
public class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }
}

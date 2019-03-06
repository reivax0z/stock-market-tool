package xavier.caron.stock.market.tool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import xavier.caron.stock.market.tool.dto.Order;
import xavier.caron.stock.market.tool.dto.Portfolio;
import xavier.caron.stock.market.tool.dto.Stock;
import xavier.caron.stock.market.tool.exception.InvalidOrderException;
import xavier.caron.stock.market.tool.service.impl.PortfolioServiceImplInMemory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Tag("unit")
public class PortfolioServiceTest {

    private PortfolioService service;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        service = new PortfolioServiceImplInMemory();
    }

    @Test
    void testGetCurrentPosition() {
        // when
        Portfolio portfolio = service.getCurrentPosition();

        // then
        assertThat(portfolio.getCashBalance()).isEqualTo(10000.00);
    }

    @Test
    void testDepositCash() throws InvalidOrderException {
        // when
        Portfolio portfolio = service.depositCash(1000.00);

        // then
        assertThat(portfolio.getCashBalance()).isEqualTo(11000.00);
    }

    @Test
    void testDepositCashShouldRejectInvalidRequest() {
        // when
        assertThatThrownBy(() -> service.depositCash(-110.00))
            .isInstanceOf(InvalidOrderException.class)
            .hasMessage("Cannot deposit less than 0");
    }

    @Test
    void testWithdrawCash() throws InvalidOrderException {
        // when
        Portfolio portfolio = service.removeCash(1000.00);

        // then
        assertThat(portfolio.getCashBalance()).isEqualTo(9000.00);
    }

    @Test
    void testWithdrawCashShouldRejectInvalidRequest() {
        // when
        assertThatThrownBy(() -> service.removeCash(11000.00))
                .isInstanceOf(InvalidOrderException.class)
                .hasMessage("Cannot withdraw all or more than balance amount");
    }

    @Test
    void testPlaceOrderBuy() throws InvalidOrderException {
        // given
        Stock stock = new Stock("MSFT", "Microsoft", 100.00);

        Order order = new Order();
        order.setUuid("111");
        order.setAction(Order.Action.BUY);
        order.setQuantity(3);
        order.setStock(stock);
        order.setPricePaid(300.00);

        // when
        Portfolio portfolio = service.placeOrder(order);

        // then
        assertThat(portfolio.getCashBalance()).isEqualTo(9700.00);

        // and
        assertThat(portfolio.getHistoryOrders()).isNotEmpty();
        assertThat(portfolio.getHistoryOrders().size()).isEqualTo(1);
        assertThat(portfolio.getHistoryOrders().get(0)).isEqualTo(order);

        // and
        assertThat(portfolio.getShareHoldings()).isNotEmpty();
        assertThat(portfolio.getShareHoldings().size()).isEqualTo(1);
        assertThat(portfolio.getShareHoldings().get(0).getPricePaid()).isEqualTo(300.00);
        assertThat(portfolio.getShareHoldings().get(0).getQuantity()).isEqualTo(3);
        assertThat(portfolio.getShareHoldings().get(0).getStock()).isEqualTo(stock);
        assertThat(portfolio.getShareHoldings().get(0).getUuid()).isNotNull();
    }

    @Test
    void testPlaceOrderBuyShouldRejectInvalidRequest() throws InvalidOrderException {
        // given
        Stock stock = new Stock("MSFT", "Microsoft", 1000.00);

        Order order = new Order();
        order.setUuid("111");
        order.setAction(Order.Action.BUY);
        order.setQuantity(11);
        order.setStock(stock);
        order.setPricePaid(11000.00);

        // then
        assertThatThrownBy(() -> service.placeOrder(order))
            .isInstanceOf(InvalidOrderException.class)
            .hasMessage("Does not have enough cash to place order");
    }
}

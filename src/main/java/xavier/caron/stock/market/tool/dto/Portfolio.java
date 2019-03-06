package xavier.caron.stock.market.tool.dto;

import lombok.Data;

import java.util.List;

@Data
public class Portfolio {

    private List<Order> historyOrders;
    private List<Share> shareHoldings;
    private double cashBalance;
}

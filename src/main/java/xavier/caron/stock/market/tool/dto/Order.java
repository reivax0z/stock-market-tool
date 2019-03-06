package xavier.caron.stock.market.tool.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class Order {

    public static enum Action {
        BUY, SELL
    }

    private Action action;
    private Stock stock;

    private int quantity;
    private double pricePaid;

    private String uuid;
    private String shareUuid;

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Order))
            return false;
        Order order = (Order) o;
        return getUuid().equals(order.getUuid());
    }

    @Override public int hashCode() {
        return Objects.hash(getUuid());
    }
}

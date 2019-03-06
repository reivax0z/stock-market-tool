package xavier.caron.stock.market.tool.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class Share {
    private Stock stock;

    private int quantity;
    private double pricePaid;

    private String uuid;

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

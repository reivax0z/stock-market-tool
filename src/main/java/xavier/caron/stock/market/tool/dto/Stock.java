package xavier.caron.stock.market.tool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private String name; // Microsoft
    private String symbol; // MSFT
    private double price; // 109.41
}

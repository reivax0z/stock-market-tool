package xavier.caron.stock.market.tool.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    @Data
    @NoArgsConstructor
    public static class GlobalQuote {
        @JsonProperty("05. price")
        private double price;
    }
}

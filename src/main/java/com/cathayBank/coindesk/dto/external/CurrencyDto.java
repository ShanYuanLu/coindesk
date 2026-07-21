package com.cathayBank.coindesk.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyDto {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    @JsonProperty("rate_float")
    private BigDecimal rateFloat;
}

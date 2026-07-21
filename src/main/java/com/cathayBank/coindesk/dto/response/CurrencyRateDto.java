package com.cathayBank.coindesk.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyRateDto {
    private String code;
    private String chineseName;
    private BigDecimal rateFloat;
}

package com.cathayBank.coindesk.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoindeskResponseDto {
    private String updateTime;
    private List<CurrencyRateDto> currencies;
}

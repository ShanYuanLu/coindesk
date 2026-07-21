package com.cathayBank.coindesk.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoindeskApiResponseDto {
    private TimeDto time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrencyDto> bpi;
}

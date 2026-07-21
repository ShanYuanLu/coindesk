package com.cathayBank.coindesk.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyRequestDto {
    private String code;
    private String chineseName;

    public CurrencyRequestDto(String code, String chineseName) {
        this.code = code;
        this.chineseName = chineseName;
    }
}

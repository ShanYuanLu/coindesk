package com.cathayBank.coindesk.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyResponseDto {
    private Long id;
    private String code;
    private String chineseName;

    public CurrencyResponseDto(Long id, String code, String chineseName) {
        this.id = id;
        this.code = code;
        this.chineseName = chineseName;
    }
}

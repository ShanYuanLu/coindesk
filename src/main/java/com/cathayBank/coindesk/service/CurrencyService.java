package com.cathayBank.coindesk.service;

import com.cathayBank.coindesk.dto.request.CurrencyRequestDto;
import com.cathayBank.coindesk.dto.response.CurrencyResponseDto;
import com.cathayBank.coindesk.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAll();
    CurrencyResponseDto create(CurrencyRequestDto currencyRequestDto);
    CurrencyResponseDto update(Long id, CurrencyRequestDto currencyRequestDto);
    void delete(Long id);
}

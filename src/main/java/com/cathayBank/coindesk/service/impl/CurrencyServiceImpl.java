package com.cathayBank.coindesk.service.impl;

import com.cathayBank.coindesk.dto.request.CurrencyRequestDto;
import com.cathayBank.coindesk.dto.response.CurrencyResponseDto;
import com.cathayBank.coindesk.entity.Currency;
import com.cathayBank.coindesk.exception.CurrencyException;
import com.cathayBank.coindesk.repository.CurrencyRepository;
import com.cathayBank.coindesk.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public CurrencyResponseDto create(CurrencyRequestDto currencyRequestDto) {

        Optional<Currency> existCurrency = currencyRepository.findByCode(currencyRequestDto.getCode());

        if (existCurrency.isPresent()) {
            throw new CurrencyException("Code 已存在");
        }

        Currency currency = new Currency();
        currency.setCode(currencyRequestDto.getCode());
        currency.setChineseName(currencyRequestDto.getChineseName());

        Currency result = currencyRepository.save(currency);

        return toResponseDto(result);
    }

    @Override
    public CurrencyResponseDto update(Long id, CurrencyRequestDto currencyRequestDto) {

        Currency existCurrency = currencyRepository.findById(id).orElseThrow(() -> new CurrencyException("查無此資料"));

        existCurrency.setCode(currencyRequestDto.getCode());
        existCurrency.setChineseName(currencyRequestDto.getChineseName());

        Currency result = currencyRepository.save(existCurrency);

        return toResponseDto(result);
    }

    @Override
    public void delete(Long id) {

        Currency existCurrency = currencyRepository.findById(id).orElseThrow(() -> new CurrencyException("查無此資料，無法刪除"));

        currencyRepository.delete(existCurrency);
    }

    private CurrencyResponseDto toResponseDto(Currency currency) {
        return new CurrencyResponseDto(
                currency.getId(),
                currency.getCode(),
                currency.getChineseName()
        );
    }
}

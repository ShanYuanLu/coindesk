package com.cathayBank.coindesk.service.impl;

import com.cathayBank.coindesk.dto.external.CoindeskApiResponseDto;
import com.cathayBank.coindesk.dto.external.CurrencyDto;
import com.cathayBank.coindesk.dto.response.CoindeskResponseDto;
import com.cathayBank.coindesk.dto.response.CurrencyRateDto;
import com.cathayBank.coindesk.entity.Currency;
import com.cathayBank.coindesk.exception.CoindeskException;
import com.cathayBank.coindesk.exception.CurrencyException;
import com.cathayBank.coindesk.repository.CurrencyRepository;
import com.cathayBank.coindesk.service.CoindeskApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CoindeskApiServiceImpl implements CoindeskApiService {

    @Value("${api_url}")
    private String apiUrl;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CoindeskApiResponseDto callCoindeskApi() {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(apiUrl, CoindeskApiResponseDto.class);
    }

    @Override
    public CoindeskResponseDto convertInfo() {

        RestTemplate restTemplate = new RestTemplate();

        CoindeskApiResponseDto response = restTemplate.getForObject(apiUrl, CoindeskApiResponseDto.class);

        if(response == null){
            throw new CoindeskException("CoinDesk API 回傳資料為空");
        }

        return getResult(response);
    }

    private CoindeskResponseDto getResult(CoindeskApiResponseDto coindeskApiResponseDto){
        CoindeskResponseDto coindeskResponseDto = new CoindeskResponseDto();
        coindeskResponseDto.setUpdateTime(getUpdateTimeFormat(coindeskApiResponseDto.getTime().getUpdated()));
        coindeskResponseDto.setCurrencies(convertCurrency(coindeskApiResponseDto.getBpi()));
        return coindeskResponseDto;
    }

    private String getUpdateTimeFormat(String updateTime){
        DateTimeFormatter input = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss z", Locale.ENGLISH);

        DateTimeFormatter output = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        return ZonedDateTime.parse(updateTime, input).format(output);
    }

    private List<CurrencyRateDto> convertCurrency(Map<String, CurrencyDto> bpi){
        List<CurrencyRateDto> result = new ArrayList<>();
        for (CurrencyDto currencyDto : bpi.values()) {

            Currency currency = currencyRepository.findByCode(currencyDto.getCode())
                    .orElseThrow(() -> new CurrencyException("找不到幣別：" + currencyDto.getCode()));

            CurrencyRateDto currencyRateDto = new CurrencyRateDto();

            currencyRateDto.setCode(currencyDto.getCode());
            currencyRateDto.setChineseName(currency.getChineseName());
            currencyRateDto.setRateFloat(currencyDto.getRateFloat());

            result.add(currencyRateDto);
        }
        return result;
    }
}

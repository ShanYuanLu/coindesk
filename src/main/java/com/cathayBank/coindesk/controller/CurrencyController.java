package com.cathayBank.coindesk.controller;

import com.cathayBank.coindesk.dto.request.CurrencyRequestDto;
import com.cathayBank.coindesk.dto.response.CurrencyResponseDto;
import com.cathayBank.coindesk.entity.Currency;
import com.cathayBank.coindesk.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> findAll(){
       return ResponseEntity.ok(currencyService.findAll());
    }

    @PostMapping
    public ResponseEntity<CurrencyResponseDto> createCurrency(@RequestBody CurrencyRequestDto currencyRequestDto){

        CurrencyResponseDto result = currencyService.create(currencyRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyResponseDto> updateCurrency(@PathVariable Long id, @RequestBody CurrencyRequestDto currencyRequestDto){
        CurrencyResponseDto result = currencyService.update(id, currencyRequestDto);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id){
        currencyService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

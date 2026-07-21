package com.cathayBank.coindesk.controller;

import com.cathayBank.coindesk.dto.external.CoindeskApiResponseDto;
import com.cathayBank.coindesk.dto.response.CoindeskResponseDto;
import com.cathayBank.coindesk.service.CoindeskApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    @Autowired
    private CoindeskApiService coindeskApiService;

    @GetMapping("/testApi")
    public ResponseEntity<CoindeskApiResponseDto> callCoindeskApi(){
        CoindeskApiResponseDto result = coindeskApiService.callCoindeskApi();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/convertInfo")
    public ResponseEntity<CoindeskResponseDto> convertInfo(){

        CoindeskResponseDto result = coindeskApiService.convertInfo();

        return ResponseEntity.ok(result);
    }
}

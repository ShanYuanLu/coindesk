package com.cathayBank.coindesk.service;

import com.cathayBank.coindesk.dto.external.CoindeskApiResponseDto;
import com.cathayBank.coindesk.dto.response.CoindeskResponseDto;

public interface CoindeskApiService {
    public CoindeskApiResponseDto callCoindeskApi();
    public CoindeskResponseDto convertInfo();
}

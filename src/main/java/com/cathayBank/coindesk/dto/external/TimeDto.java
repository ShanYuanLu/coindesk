package com.cathayBank.coindesk.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeDto {
    private String updated;
    @JsonProperty("updatedISO")
    private String updatedIso;
    private String updateduk;
}

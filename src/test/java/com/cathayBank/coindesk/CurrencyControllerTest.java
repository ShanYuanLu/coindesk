package com.cathayBank.coindesk;

import com.cathayBank.coindesk.entity.Currency;
import com.cathayBank.coindesk.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    private Currency usd;

    @BeforeEach
    void setUp(){
        currencyRepository.deleteAll();

        usd = saveCurrency("USD", "美元");
        saveCurrency("GBP", "英鎊");
        saveCurrency("EUR", "歐元");
    }

    private Currency saveCurrency(String code, String chineseName) {
        Currency currency = new Currency();
        currency.setCode(code);
        currency.setChineseName(chineseName);
        return currencyRepository.save(currency);
    }

    @Test
    public void findAll() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void createCurrency() throws Exception {
        String requestBody = "{\"code\":\"JYP\",\"chineseName\":\"日圓\"}";
        MvcResult result = mockMvc.perform(
                        post("/api/currencies")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("JYP"))
                .andExpect(jsonPath("$.chineseName").value("日圓"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void updateCurrency() throws Exception {
        String requestBody = "{\"code\":\"JYP\",\"chineseName\":\"日圓\"}";
        MvcResult result = mockMvc.perform(
                        put("/api/currencies/{id}", usd.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("JYP"))
                .andExpect(jsonPath("$.chineseName").value("日圓"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    public void deleteCurrency() throws Exception {
        mockMvc.perform(delete("/api/currencies/{id}", usd.getId()))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}

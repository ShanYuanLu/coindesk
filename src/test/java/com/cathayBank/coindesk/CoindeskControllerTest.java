package com.cathayBank.coindesk;

import com.cathayBank.coindesk.entity.Currency;
import com.cathayBank.coindesk.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CoindeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    void setUp(){
        currencyRepository.deleteAll();

        saveCurrency("USD", "美元");
        saveCurrency("GBP", "英鎊");
        saveCurrency("EUR", "歐元");
    }

    private void saveCurrency(String code, String chineseName) {
        Currency currency = new Currency();
        currency.setCode(code);
        currency.setChineseName(chineseName);
        currencyRepository.save(currency);
    }

    @Test
    void testCoindeskApi() throws Exception {
        MvcResult result = mockMvc.perform(get("/coindesk/testApi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.time").exists())
                .andExpect(jsonPath("$.bpi").exists())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    void testConvertInfo() throws Exception {
        MvcResult result = mockMvc.perform(get("/coindesk/convertInfo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updateTime").exists())
                .andExpect(jsonPath("$.currencies").exists())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString(StandardCharsets.UTF_8));
    }
}

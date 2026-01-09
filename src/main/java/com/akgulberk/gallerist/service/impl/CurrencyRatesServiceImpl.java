package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.CurrencyRatesItems;
import com.akgulberk.gallerist.dto.CurrencyRatesResponse;
import com.akgulberk.gallerist.exception.BaseException;
import com.akgulberk.gallerist.exception.ErrorMessage;
import com.akgulberk.gallerist.exception.MessageType;
import com.akgulberk.gallerist.service.ICurrencyRatesService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService {
    @Override
    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
        //https://evds2.tcmb.gov.tr/service/evds/series=TP.DK.USD.A.YTL&startDate=09-01-2026&endDate=09-01-2026&type=json
        String rootUrl = "https://evds2.tcmb.gov.tr/service/evds/";
        String series = "TP.DK.USD.A.YTL";
        String type = "json";

        String endpoint = rootUrl + "series=" + series + "&startDate=" + startDate + "&endDate=" + endDate + "&type=" + type;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("key", "jvrrxzJNB1");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        try {
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
            });
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(e.getMessage(), MessageType.CURRENCY_RATES_IS_OCCURED));
        }

        return null;


    }
}

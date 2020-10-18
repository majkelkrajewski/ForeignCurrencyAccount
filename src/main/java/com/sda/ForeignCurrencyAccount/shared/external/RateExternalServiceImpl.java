package com.sda.ForeignCurrencyAccount.shared.external;

import com.sda.ForeignCurrencyAccount.shared.exceptions.ServiceUnavailableException;
import com.sda.ForeignCurrencyAccount.shared.model.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RateExternalServiceImpl implements RateExternalService {

    private static Map<String, String> params;

    static {
        params = new HashMap<>();
        params.put("format", "json");
    }

    private final String apiUrl;

    private final RestTemplate restTemplate;

    public RateExternalServiceImpl(@Value("${rates.api.url}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public RateExternalView getBidRate(Currency currency) {
        try {
            RateResponse rateResponse = restTemplate.getForObject(apiUrl + currency, RateResponse.class, params);
            if (rateResponse == null || rateResponse.getRates() == null || rateResponse.getRates().size() !=1) {
                throw new ServiceUnavailableException("Rate service unavailable!");
            }
            return new RateExternalView(rateResponse.getCode(), rateResponse.getRates().get(0).getBid());
        } catch (RestClientException ex) {
            throw new ServiceUnavailableException("Rate service unavailable!");
        }
    }

    @Override
    public RateExternalView getAskRate(Currency currency) {
        try {
            RateResponse rateResponse = restTemplate.getForObject(apiUrl + currency, RateResponse.class, params);
            if (rateResponse == null || rateResponse.getRates() == null || rateResponse.getRates().size() !=1) {
                throw new ServiceUnavailableException("Rate service unavailable!");
            }
            return new RateExternalView(rateResponse.getCode(), rateResponse.getRates().get(0).getAsk());
        } catch (RestClientException ex) {
            throw new ServiceUnavailableException("Rate service unavailable!");
        }
    }
}

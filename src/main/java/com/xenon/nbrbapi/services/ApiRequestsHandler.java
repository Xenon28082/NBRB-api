package com.xenon.nbrbapi.services;

import com.xenon.nbrbapi.exceptions.NoDataFoundException;
import com.xenon.nbrbapi.models.Currency;
import com.xenon.nbrbapi.models.Rate;
import com.xenon.nbrbapi.repositories.CurrencyRepository;
import com.xenon.nbrbapi.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ApiRequestsHandler {

    private final RestTemplate restTemplate;

    private final String HOST = "api.nbrb.by";

    public List<Rate> getRatesByDateAndPeriodicity(LocalDate date, String periodicity) {
        String url = UrlBuilder.build(HOST, List.of("exrates", "rates"), Map.of("ondate", date.toString(), "periodicity", periodicity));
        List<Rate> returnRate;
        try {
            returnRate = Arrays.asList(restTemplate.getForEntity(url, Rate[].class).getBody());
        } catch (HttpClientErrorException e) {
            returnRate = new ArrayList<>();
        }
        return returnRate;
    }

    public List<Currency> getCurrencies() {
        String url = UrlBuilder.build(HOST, List.of("exrates", "currencies"), Map.of());
        ResponseEntity<Currency[]> curResponse = restTemplate.getForEntity(url, Currency[].class);
        return Arrays.asList(curResponse.getBody());
    }


    public List<Rate> getRatesByDate(LocalDate date) throws NoDataFoundException {
        var newRates = new ArrayList<Rate>();
        newRates.addAll(getRatesByDateAndPeriodicity(date, "0"));
        newRates.addAll(getRatesByDateAndPeriodicity(date, "1"));
        if (newRates.isEmpty()) {
            throw new NoDataFoundException("No data foound for date: " + date);
        }
        return newRates;
    }

}

package com.xenon.nbrbapi.services;

import com.xenon.nbrbapi.exceptions.NoDataFoundException;
import com.xenon.nbrbapi.models.Rate;
import com.xenon.nbrbapi.repositories.CurrencyRepository;
import com.xenon.nbrbapi.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RepositoryOperator {

    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;
    private final ApiRequestsHandler handler;

    public Rate findRates(LocalDate date, String code) throws NoDataFoundException {
        int periodicity = currencyRepository.findAllByCurAbbreviation(code).stream().findFirst().orElseThrow(() -> new NoDataFoundException("No currency with code: " + code + " has been found")).getCurPeriodicity();

        Optional<Rate> foundRate;
        switch (periodicity) {
            case 0:
                foundRate = rateRepository.findByDateAndAndCurAbbreviation(date, code).stream().findFirst();
                break;
            case 1:
                LocalDate start = date.withDayOfMonth(1);
                LocalDate end = date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
                foundRate = rateRepository.findByDateAfterAndDateBeforeAndCurAbbreviation(start, end, code).stream().findFirst();
                break;
            default:
                foundRate = Optional.empty();
        }

        if (foundRate.isEmpty()) {
            updateRatesByDate(date);
            return findRates(date, code);
        }

        return foundRate.get();
    }

    public List<Rate> findRatesByDate(LocalDate date){
        return rateRepository.findAllByDate(date);
    }

    public void updateRatesByDate(LocalDate date) throws NoDataFoundException {
        rateRepository.saveAll(handler.getRatesByDate(date));
    }

    public void updateCurrencies() {
        currencyRepository.saveAll(handler.getCurrencies());
    }


}

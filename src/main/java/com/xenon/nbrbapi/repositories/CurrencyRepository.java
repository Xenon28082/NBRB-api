package com.xenon.nbrbapi.repositories;

import com.xenon.nbrbapi.models.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {
    List<Currency> findAllByCurAbbreviation(String curAbbreviation);
}

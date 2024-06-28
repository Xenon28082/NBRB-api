package com.xenon.nbrbapi.repositories;

import com.xenon.nbrbapi.models.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {
}

package com.xenon.nbrbapi.repositories;

import com.xenon.nbrbapi.models.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

    List<Rate> findAllByDate(LocalDate date);

    List<Rate> findByDateAndAndCurAbbreviation(LocalDate date, String abbreviation);

    List<Rate> findByDateAfterAndDateBeforeAndCurAbbreviation(LocalDate start, LocalDate end, String abbreviation);
}

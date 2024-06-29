package com.xenon.nbrbapi.controllers;

import com.xenon.nbrbapi.exceptions.NoDataFoundException;
import com.xenon.nbrbapi.models.Rate;
import com.xenon.nbrbapi.services.RepositoryOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BusinessController {

    private final RepositoryOperator operator;

    @ResponseBody
    @GetMapping("/status")
    public ResponseEntity checkStatus(@RequestParam(name = "date") LocalDate date) {
        List<Rate> foundRates = operator.findRatesByDate(date);
        if (foundRates.isEmpty()) {
            return new ResponseEntity<>("No rates found for date: " + date, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Rates where fetched successfully for date: " + date, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/fetch")
    public ResponseEntity fetchData(@RequestParam(name = "date") LocalDate date, @RequestParam(name = "code") String code) {
        try {
            return new ResponseEntity(operator.findRates(date, code.toUpperCase()), HttpStatus.OK);
        }catch (NoDataFoundException exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }
}

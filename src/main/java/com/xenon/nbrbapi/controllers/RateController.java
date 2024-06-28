package com.xenon.nbrbapi.controllers;

import com.xenon.nbrbapi.models.Rate;
import com.xenon.nbrbapi.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rate")
public class RateController {

    private final RateRepository rateRepository;

    @GetMapping
    public Iterable<Rate> findAll(){
        return rateRepository.findAll();
    }

}

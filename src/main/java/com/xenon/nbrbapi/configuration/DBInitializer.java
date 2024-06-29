package com.xenon.nbrbapi.configuration;

import com.xenon.nbrbapi.services.RepositoryOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DBInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final RepositoryOperator operator;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("Fetching api...");
        operator.updateCurrencies();
        System.out.println("Success");

    }
}

package com.esgi.configuration;

import com.esgi.application.CreatePaymentCommandHandler;
import com.esgi.domain.PaymentProofs;
import com.esgi.infrastructure.RedisPaymentProofs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    @Bean
    PaymentProofs paymentProofs() {
        return new RedisPaymentProofs();
    }

    @Bean
    public CreatePaymentCommandHandler createPaymentCommandHandler() {
        return new CreatePaymentCommandHandler(paymentProofs());
    }
}

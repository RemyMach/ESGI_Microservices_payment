package com.esgi.configuration;

import com.esgi.application.CreatePaymentCommandHandler;
import com.esgi.domain.PaymentProofs;
import com.esgi.infrastructure.RedisConfigurationProperties;
import com.esgi.infrastructure.RedisPaymentProofs;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class PaymentConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "redis")
    public RedisConfigurationProperties redisConfigurationProperties() {
        return new RedisConfigurationProperties();
    }

    @Bean
    PaymentProofs paymentProofs() {
        return new RedisPaymentProofs(redisConfigurationProperties());
    }

    @Bean
    public CreatePaymentCommandHandler createPaymentCommandHandler() {
        return new CreatePaymentCommandHandler(paymentProofs());
    }

}

package io.swagger.configuration;
import io.swagger.application.CreatePaymentCommandHandler;
import io.swagger.domain.PaymentProofs;
import io.swagger.infrastructure.RedisPaymentProofs;
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

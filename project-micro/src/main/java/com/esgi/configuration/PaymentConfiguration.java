package com.esgi.configuration;

import com.esgi.application.CreatePaymentCommandHandler;
import com.esgi.domain.PaymentProofs;
import com.esgi.infrastructure.RedisConfigurationProperties;
import com.esgi.infrastructure.RedisPaymentProofs;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;

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
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        poolCfg.setMaxTotal(3);
        return new RedisPaymentProofs(redisConfigurationProperties(), poolCfg);
    }

    @Bean
    public CreatePaymentCommandHandler createPaymentCommandHandler() {
        return new CreatePaymentCommandHandler(paymentProofs());
    }

}

package com.esgi.microservice.microredis.domain;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) { }

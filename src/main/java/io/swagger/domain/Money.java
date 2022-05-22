package io.swagger.domain;

import java.math.BigDecimal;

public record Money(BigDecimal amount, Currency currency) { }

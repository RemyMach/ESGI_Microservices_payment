package com.esgi.domain;

public enum Currency {
    EUR("EUR"),
    USD("USD");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Currency getUnitFromCode(String code) {
        Currency currency = null;
        for (Currency unit : Currency.values()) {
            if (unit.getCode().equals(code)) {
                currency = unit;
            }
        }

        if (currency == null) {
            return null;
        }

        return currency;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                '}';
    }
}

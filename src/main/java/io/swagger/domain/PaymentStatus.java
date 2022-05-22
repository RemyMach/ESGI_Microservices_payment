package io.swagger.domain;

public enum PaymentStatus {
    PAYMENT_FAILED("payment_failed"),
    PAYMENT_PROCESSING("payment_processing"),
    PAYMENT_SUCCESS("payment_success");

    private final String code;

    PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PaymentStatus getUnitFromCode(String code) {
        PaymentStatus paymentStatus = null;
        for (PaymentStatus unit : PaymentStatus.values()) {
            if (unit.getCode().equals(code)) {
                paymentStatus = unit;
            }
        }

        if (paymentStatus == null) {
            return null;
        }

        return paymentStatus;
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "code='" + code + '\'' +
                '}';
    }
}

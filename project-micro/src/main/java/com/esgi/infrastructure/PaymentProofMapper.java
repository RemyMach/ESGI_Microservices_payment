package com.esgi.infrastructure;

import com.esgi.domain.PaymentProof;
import com.esgi.domain.PaymentProofId;
import com.esgi.domain.PaymentStatus;
import com.esgi.domain.TransactionId;
import com.microredis.generate.model.AddPayment201Response;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PaymentProofMapper {

    public static AddPayment201Response mapPaymentProofToPaymentProofResponse(PaymentProof paymentProof) {
        // string date to offset date time in java
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse(paymentProof.getDate(), formatter);
        return new AddPayment201Response()
                .date(OffsetDateTime.from(dateTime))
                .transactionId(UUID.fromString(paymentProof.getTransactionId().value));
    }

    public static PaymentProof mapRedisPaymentProofToPaymentProof(RedisPaymentProof redisPaymentProof) {
        return new PaymentProof(new PaymentProofId(redisPaymentProof.id), PaymentStatus.getUnitFromCode(redisPaymentProof.PaymentStatus), new TransactionId(redisPaymentProof.transactionId), redisPaymentProof.date);
    }

    public static RedisPaymentProof mapPaymentProofToRedisPaymentProof(PaymentProof paymentProof) {
        return new RedisPaymentProof(paymentProof.getPaymentProofId().id, paymentProof.getTransactionId().value, paymentProof.getDate(), paymentProof.getPaymentStatus().getCode());
    }
}

package io.swagger.infrastructure;

import io.swagger.domain.PaymentProof;
import io.swagger.domain.PaymentProofId;
import io.swagger.domain.PaymentStatus;
import io.swagger.domain.TransactionId;
import io.swagger.model.PaymentProofResponse;

public class PaymentProofMapper {

    public static PaymentProofResponse mapPaymentProofToPaymentProofResponse(PaymentProof paymentProof) {
        return new PaymentProofResponse(paymentProof.getTransactionId().value(), paymentProof.getDate());
    }

    public static PaymentProof mapRedisPaymentProofToPaymentProof(RedisPaymentProof redisPaymentProof) {
        return new PaymentProof(new PaymentProofId(redisPaymentProof.id), PaymentStatus.getUnitFromCode(redisPaymentProof.PaymentStatus), new TransactionId(redisPaymentProof.transactionId), redisPaymentProof.date);
    }

    public static RedisPaymentProof mapPaymentProofToRedisPaymentProof(PaymentProof paymentProof) {
        return new RedisPaymentProof(paymentProof.getPaymentProofId().value(), paymentProof.getTransactionId().value(), paymentProof.getDate(), paymentProof.getPaymentStatus().getCode());
    }
}

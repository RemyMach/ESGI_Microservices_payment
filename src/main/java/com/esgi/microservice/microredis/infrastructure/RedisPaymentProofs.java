package com.esgi.microservice.microredis.infrastructure;

import com.esgi.microservice.microredis.domain.PaymentProof;
import com.esgi.microservice.microredis.domain.PaymentProofId;
import com.esgi.microservice.microredis.domain.PaymentProofs;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPaymentProofs implements PaymentProofs {

    private JedisPool pool;
    private final String baseName = "payment:";
    public RedisPaymentProofs() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        poolCfg.setMaxTotal(3);

        this.pool = new JedisPool(poolCfg, "localhost", 6379, 500);
    }

    @Override
    public void add(PaymentProof paymentProof) {
        try (Jedis jedis = this.pool.getResource()) {

            jedis.set(this.baseName + paymentProof.getPaymentProofId().value(), paymentProof.getPaymentProofId().value());
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public PaymentProof findById(String id) {
        try (Jedis jedis = this.pool.getResource()) {
            String idEntity = jedis.get(this.baseName + id);
            return new PaymentProof(new PaymentProofId(idEntity));
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

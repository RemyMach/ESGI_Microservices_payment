package io.swagger.infrastructure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.domain.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.UUID;

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

            jedis.set(this.baseName + paymentProof.getPaymentProofId().value(), paymentProof.toString());
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public PaymentProof findById(String id) {
        try (Jedis jedis = this.pool.getResource()) {
            String paymentProofStringify = jedis.get(this.baseName + id);
            if(paymentProofStringify == null) return null;
            return new ObjectMapper().readValue(paymentProofStringify , PaymentProof.class);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

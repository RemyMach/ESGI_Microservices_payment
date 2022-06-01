package com.esgi.infrastructure;

import com.esgi.domain.PaymentProof;
import com.esgi.domain.PaymentProofs;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPaymentProofs implements PaymentProofs {

    @Value("${REDIS_HOST}")
    private String host;

    @Value("${REDIS_PORT}")
    private int port;

    @Value("${REDIS_TIMEOUT}")
    private int timeout;

    private JedisPool pool;
    private final String baseName = "payment:";
    public RedisPaymentProofs() {
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        poolCfg.setMaxTotal(3);
        System.out.println(this.host);
        System.out.println(this.port);
        System.out.println(this.timeout);
        this.pool = new JedisPool(poolCfg, "localhost", 6379, 5000);
    }

    @Override
    public void add(PaymentProof paymentProof) {
        try (Jedis jedis = this.pool.getResource()) {
            RedisPaymentProof redisPaymentProof = PaymentProofMapper.mapPaymentProofToRedisPaymentProof(paymentProof);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(redisPaymentProof);
            jedis.set(this.baseName + paymentProof.getPaymentProofId().id, json);
        }catch (Exception e) {
            System.out.println("erreur dans le add ---");
            System.out.println(e);
        }
    }

    @Override
    public PaymentProof findById(String id) {
        try (Jedis jedis = this.pool.getResource()) {
            String redisPaymentProofStringify = jedis.get(this.baseName + id);
            System.out.println("dans le findById ---");
            System.out.println(redisPaymentProofStringify);
            if(redisPaymentProofStringify == null) return null;
            RedisPaymentProof redisPaymentProof = new ObjectMapper().readValue(redisPaymentProofStringify , RedisPaymentProof.class);
            System.out.println(redisPaymentProof.toString());
            return PaymentProofMapper.mapRedisPaymentProofToPaymentProof(redisPaymentProof);
        }catch (Exception e) {
            System.out.println("erreur dans le findById ---");
            System.out.println(e);
        }
        return null;
    }
}

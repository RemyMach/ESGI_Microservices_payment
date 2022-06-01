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

    private final RedisConfigurationProperties redisConfigurationProperties;
    private JedisPool pool;
    private final String baseName = "payment:";
    public RedisPaymentProofs(RedisConfigurationProperties redisConfigurationProperties) {
        this.redisConfigurationProperties = redisConfigurationProperties;
        JedisPoolConfig poolCfg = new JedisPoolConfig();
        poolCfg.setMaxTotal(3);
        this.pool = new JedisPool(poolCfg, redisConfigurationProperties.getRedisHost(), redisConfigurationProperties.getRedisPort(), redisConfigurationProperties.getRedisTimeout());
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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public PaymentProof findById(String id) {
        try (Jedis jedis = this.pool.getResource()) {
            String redisPaymentProofStringify = jedis.get(this.baseName + id);
            System.out.println("dans le findById ---");
            if(redisPaymentProofStringify == null) return null;
            RedisPaymentProof redisPaymentProof = new ObjectMapper().readValue(redisPaymentProofStringify , RedisPaymentProof.class);
            return PaymentProofMapper.mapRedisPaymentProofToPaymentProof(redisPaymentProof);
        }catch (Exception e) {
            System.out.println("erreur dans le findById ---");
            System.out.println(e.getMessage());
        }
        return null;
    }
}

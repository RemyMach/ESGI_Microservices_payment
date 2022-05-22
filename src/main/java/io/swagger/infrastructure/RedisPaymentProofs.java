package io.swagger.infrastructure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.domain.*;
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
            RedisPaymentProof redisPaymentProof = PaymentProofMapper.mapPaymentProofToRedisPaymentProof(paymentProof);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(redisPaymentProof);
            jedis.set(this.baseName + paymentProof.getPaymentProofId().value(), json);
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

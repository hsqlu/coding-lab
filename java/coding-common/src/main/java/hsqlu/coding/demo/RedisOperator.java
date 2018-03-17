package hsqlu.coding.demo;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created: 16/11/2017.
 * Author: Qiannan Lu
 */
public class RedisOperator {
    private void demo() {
        Jedis jedis = new Jedis("cluster2");
        Set<String> result = jedis.keys("闽A?393?-*-*-");
        result.size();
    }

    public static void main(String[] args) {
        new RedisOperator().demo();
    }
}

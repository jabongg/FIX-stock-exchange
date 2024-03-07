package com.staywell.cache;

import java.util.Arrays;

import org.redisson.Redisson;
import org.redisson.api.RScript;
import org.redisson.api.RScript.Mode;
import org.redisson.api.RScript.ReturnType;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
//import org.redisson.command.CommandExecutor;
//import org.redisson.command.CommandSyncService;
import org.redisson.config.Config;
import org.redisson.config.ConfigSupport;
import org.redisson.connection.ConnectionManager;

public class RateLimiter {

    public static void main(String[] args) throws InterruptedException {
        // Connect to local redis instance. This can be changed to oci-redis or oke redis
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        //ConnectionManager connectionManager = ConfigSupport.createConnectionManager(config);
        RedissonClient redisson = Redisson.create(config);
        RScript script = redisson.getScript(StringCodec.INSTANCE);
        // execute lue script in read write mode

        //CommandExecutor commandExecutor = createRedissonExecutor(connectionManager);

        String rateLimitingLuaScript = getRateLimitingLuaScript();
        // key for service1
        Object[] keys = new Object[]{"service1RateLimit"};
        Object[] params = new Object[]{4, 1}; // 100--> 100 requests, 1--> 1 second

        // (example: 100 requests/second)
        for (int i = 1; i < 10; i++) {
            Boolean isAllowed = script.eval("service1RateLimit", Mode.READ_WRITE,
                    rateLimitingLuaScript, ReturnType.BOOLEAN, Arrays.asList(keys), params);
            System.out.println("isAllowed::" + ":" + isAllowed + " i ::" + i);

            if (i == 5) {
                System.out.println("request rate limited: more than 4/sec");
                Thread.sleep(2000);
            }
        }


        //connectionManager.shutdown();
        redisson.shutdown();
    }

//    private static CommandExecutor createRedissonExecutor(ConnectionManager connectionManager) {
//        return new CommandSyncService(connectionManager, null);
//    }

    public static String getRateLimitingLuaScript() {
        //lua script for rate limiter
        String script =
                "local key = KEYS[1]; " +
                        "local requests = tonumber(redis.call('GET', key) or '-1'); " +
                        "local max_requests = tonumber(ARGV[1]); " +
                        "local expiry = tonumber(ARGV[2]); " +
                        "if (requests == -1) then " +
                        "redis.call('INCR', key); " +
                        "redis.call('EXPIRE', key, expiry); " +
                        "return true; " +
                        "elseif (requests < max_requests) then " +
                        "redis.call('INCR', key); " +
                        "return true; " +
                        "else " +
                        "return false; " +
                        "end";
        return script;
    }

}
package de.cuelex.database.redis;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.HomeCloud;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.database.redis
    Date: 14.05.2021
    
*/
public class RedisConnectionHandler {

    private String hostname;
    private int port;
    private String username;
    private String password;
    private Jedis redis = null;
    private JedisPool redisPool = null;

    public void connect() {
        this.hostname = null;
        this.port = 0;
        ;
        this.password = null;
        ;
        redisPool = new JedisPool(new JedisPoolConfig(), hostname, port, Protocol.DEFAULT_TIMEOUT, password);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, RedisConnectionHandler.class, "Successfully connected to database using jedis.");
    }

    public void disconnect() {
        redisPool.destroy();
    }

    public void openConnection() {
        redis.set("foo", "bar");
        String value = redis.get("foo");
        System.out.println(value);
    }

    public String defineJedis(String keyName) {
        try {
            redis = redisPool.getResource();
            return redis.get(keyName);
        } catch (JedisConnectionException e) {
            if (redis != null) {
                redisPool.returnBrokenResource(redis);
                redis = null;
            }
            throw e;
        } finally {
            if (redis != null) {
                redisPool.returnResource(redis);
            }
        }
    }

    public int getPort() {
        return port;
    }

    public Jedis getRedis() {
        return redis;
    }

    public JedisPool getRedisPool() {
        return redisPool;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}

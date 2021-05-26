package de.cuelex.database.redis;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.main.TavaniaCloud;
import org.simpleyaml.configuration.file.YamlFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.database.redis
    Date: 14.05.2021
    
*/
public class RedisConnectionHandler {

    private String hostname;
    private int port;
    private String password;
    public Jedis redis = null;
    private JedisPool redisPool = null;

    public void connect() {
        this.hostname = TavaniaCloud.getInstance().getDatabaseHandler().getHostname();
        this.port = TavaniaCloud.getInstance().getDatabaseHandler().getPort();
        this.password = TavaniaCloud.getInstance().getDatabaseHandler().getPassword();
        redisPool = new JedisPool(new JedisPoolConfig(), hostname, port, Protocol.DEFAULT_TIMEOUT);
        redis = redisPool.getResource();
        try {
            //Set redis-configured true in config file
            YamlFile yamlFile = TavaniaCloud.getInstance().getYamlFileHandler().getConfigFile();
            yamlFile.set("DatabaseConfiguration", true);
            yamlFile.set("DatabaseType", "REDIS");
            yamlFile.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TavaniaCloud.getInstance().getDatabaseHandler().saveConfigurations();
        TavaniaCloud.getInstance().getDatabaseHandler().setDatabaseConfigured(true);
        ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, RedisConnectionHandler.class, "Successfully connected to database using jedis.");
    }

    public void disconnect() {
        redisPool.destroy();
    }

    /**
     * initializing test-data
     */
    public void openConnection() {
        redis.set("foo", "bar");
        System.out.println(defineJedis("foo"));
    }

    /**
     * Getting a value from a defined key, working like a hashmap
     *
     * @param keyName is the key from the hash
     * @return gets the value
     */
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

}

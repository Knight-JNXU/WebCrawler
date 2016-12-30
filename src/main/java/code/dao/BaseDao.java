package code.dao;

import code.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
public class BaseDao {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    @Autowired
    protected JSONUtils jsonUtils;
    protected String Prefix = "blog:";

    /**
     * 获取RedisSerializer
     * @return
     */
    protected RedisSerializer<String> getRedisSerializer(){
        return redisTemplate.getStringSerializer();
    }

    /**
     * 获取json转换工具
     * @return
     */
    public JSONUtils getJsonUtils(){
        return this.jsonUtils;
    }

    /**
     *
     * @param key
     * @return
     */
    protected Object get(String key){
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     *
     * @param key
     * @param value
     */
    protected void set(String key, String value){
        redisTemplate.boundValueOps(key).set(value);
    }

    /**
     * 批处理set
     * @param keys
     * @param values
     */
    protected void setArray(final String prefix, final List<String> keys, final List<String> values){
        redisTemplate.execute(new RedisCallback<Void>() {
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for(int i=0; i<keys.size(); i++){
                    byte[] key = getRedisSerializer().serialize(prefix+keys.get(i));
                    byte[] value = getRedisSerializer().serialize(values.get(i));
                    redisConnection.setNX(key, value);
                }
                return null;
            }
        }, false, true);
    }

    /**
     *
     * @param key
     */
    protected void del(String key){
        redisTemplate.delete(key);
    }

    /**
     *
     * @param strKey
     * @param strField
     * @param strValue
     * @throws Exception
     */
    protected void hset(final String strKey, final String strField, final String strValue) throws Exception{
        Object result = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] key = getRedisSerializer().serialize(strKey);
                byte[] field = getRedisSerializer().serialize(strField);
                byte[] value = getRedisSerializer().serialize(strValue);
                return redisConnection.hSet(key, field, value);
            }
        });
    }

    /**
     *
     * @param key
     * @param field
     * @return
     * @throws Exception
     */
    protected String hget(final String key, final String field) throws Exception{
        final byte[] valueByte = redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] keyByte = getRedisSerializer().serialize(key);
                byte[] fieldByte = getRedisSerializer().serialize(field);
                return redisConnection.hGet(fieldByte, keyByte);
            }
        });
        return (new String(valueByte,"UTF-8"));
    }
}

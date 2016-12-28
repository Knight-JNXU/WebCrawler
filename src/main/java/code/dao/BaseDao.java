package code.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
public class BaseDao {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取RedisSerializer
     * @return
     */
    protected RedisSerializer<String> getRedisSerializer(){
        return redisTemplate.getStringSerializer();
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

package io.github.tianxingovo.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtil {


    @Resource
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 操作String类型
     */
    @Resource
    StringRedisTemplate stringRedisTemplate;

    ValueOperations<String, String> stringOperation;
    HashOperations<String, Object, Object> hashOperation;
    ListOperations<String, Object> listOperation;
    SetOperations<String, Object> setOperation;
    ZSetOperations<String, Object> zSetOperation;


    @PostConstruct
    public void init() {
        stringOperation = stringRedisTemplate.opsForValue();
        hashOperation = redisTemplate.opsForHash();
        listOperation = redisTemplate.opsForList();
        setOperation = redisTemplate.opsForSet();
        zSetOperation = redisTemplate.opsForZSet();
    }

    /**
     * String-set
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringOperation.set(key, value, timeout, unit);
    }

    /**
     * String-get
     */
    public String get(String key) {
        return stringOperation.get(key);
    }

    /**
     * Hash-put
     */
    public void put(String key, String hashKey, String value) {
        hashOperation.put(key, hashKey, value);
    }

    /**
     * Hash-get
     */
    public Object hashGet(String key, String hashKey) {
        return hashOperation.get(key, hashKey);
    }

    /**
     * List-leftPush
     */
    public void leftPush(String key, String value) {
        listOperation.leftPush(key, value);
    }

    /**
     * List-rightPush
     */
    public void rightPush(String key, String value) {
        listOperation.rightPush(key, value);
    }

    /**
     * List-leftPop
     */
    public Object leftPop(String key) {
        return listOperation.leftPop(key);
    }

    /**
     * List-rightPop
     */
    public Object rightPop(String key) {
        return listOperation.rightPop(key);
    }

    /**
     * Set-add
     */
    public void setAdd(String key, String value) {
        setOperation.add(key, value);
    }

    /**
     * Set-pop
     */
    public Object setPop(String key) {
        return setOperation.pop(key);
    }

    /**
     * ZSet-add
     */
    public void ZSetAdd(String key, String value, double score) {
        zSetOperation.add(key, value, score);
    }

    /**
     * ZSet-PopMin
     */
    public Object ZSetPopMin(String key) {
        return zSetOperation.popMin(key);
    }

    /**
     * ZSet-PopMax
     */
    public Object ZSetPopMax(String key) {
        return zSetOperation.popMax(key);
    }

    /**
     * 删除
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}


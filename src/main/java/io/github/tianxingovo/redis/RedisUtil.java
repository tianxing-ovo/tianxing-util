package io.github.tianxingovo.redis;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtil {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    ValueOperations<String, String> stringOperation;
    HashOperations<String, String, String> hashOperation;
    ListOperations<String, String> listOperation;
    SetOperations<String, String> setOperation;
    ZSetOperations<String, String> zSetOperation;


    @PostConstruct
    public void init() {
        stringOperation = stringRedisTemplate.opsForValue();
        hashOperation = stringRedisTemplate.opsForHash();
        listOperation = stringRedisTemplate.opsForList();
        setOperation = stringRedisTemplate.opsForSet();
        zSetOperation = stringRedisTemplate.opsForZSet();
    }

    /**
     * String-set
     */
    public void set(String key, String value, long timeout, TimeUnit unit) {
        stringOperation.set(key, value, timeout, unit);
    }

    /**
     * set key value ex|px timeout nx
     * key存在,返回false;key不存在,返回true
     *
     * @return 是否成功
     */
    public Boolean setIfAbsent(String key, String value, long timeout, TimeUnit unit) {
        return stringOperation.setIfAbsent(key, value, timeout, unit);
    }

    /**
     * String-set
     */
    public void set(String key, String value) {
        stringOperation.set(key, value);
    }


    /**
     * String-get
     */
    public String get(String key) {
        return stringOperation.get(key);
    }

    /**
     * 设置过期时间
     */
    public void expire(String key, long timeout, TimeUnit unit) {
        stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * Hash-put
     */
    public void put(String key, String hashKey, String value) {
        hashOperation.put(key, hashKey, value);
    }

    /**
     * Hash-putAll
     */
    public void putAll(String key, Map<String, String> map) {
        hashOperation.putAll(key, map);
    }

    /**
     * Hash-get
     */
    public Object hashGet(String key, String hashKey) {
        return hashOperation.get(key, hashKey);
    }

    /**
     * 获取指定key对应的所有字段和值
     */
    public Map<String, String> entries(String key) {
        return hashOperation.entries(key);
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
        stringRedisTemplate.delete(key);
    }
}


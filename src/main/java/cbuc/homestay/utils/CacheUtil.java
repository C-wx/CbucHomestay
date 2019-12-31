package cbuc.homestay.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Explain:   通过Map实现缓存
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2019/11/6
 */
@Slf4j
@Component
public class CacheUtil {

    /**
     * 默认存储1024个缓存
     * 设置单例
     */
    private static final int DEFAULT_CACHES = 1024;

    private static final CacheUtil cache = new CacheUtil();

    public static CacheUtil getCache() {
        return cache;
    }

    /**
     * 缓存容器
     */
    private Map<String, CacheObject> cachePool;

    public CacheUtil() {
        this(DEFAULT_CACHES);
    }

    public CacheUtil(int cacheCount) {
        cachePool = new ConcurrentHashMap<>(cacheCount);
    }
    /**
     * 设置缓存
     *
     * @param key     缓存key
     * @param value   缓存value
     */
    public void set(String key, Object value){
        //缓存池容量超出三分之二时, 清空缓存池
        if (cachePool.size() > cachePool.size()*(2/3)) {
            cachePool.clear();
        }
        CacheObject cacheObject = new CacheObject(key, value);
        cachePool.put(key, cacheObject);
    }

    /**
     * 设置带有过期时间缓存
     *
     * @param key     缓存key
     * @param value   缓存value
     * @param expired 过期时间，单位为秒
     */
    public void set(String key, Object value, long expired) {
        expired = expired > 0 ? System.currentTimeMillis() / 1000 + expired : expired;
        //缓存池容量超出三分之二时, 清空缓存池
        if (cachePool.size() > cachePool.size()*(2/3)) {
            cachePool.clear();
        }
        CacheObject cacheObject = new CacheObject(key, value, expired);
        cachePool.put(key, cacheObject);
    }

    /**
     * 读取一个缓存对象
     * @param key 缓存key
     * @param <T>
     * @return
     */
    public <T> T get(String key) {
        CacheObject cacheObject = cachePool.get(key);
        if (null != cacheObject) {
            long cur = System.currentTimeMillis() / 1000;
            //未过期直接返回
            if (cacheObject.getExpired() <= 0 || cacheObject.getExpired() > cur) {
                Object result = cacheObject.getValue();
                return (T) result;
            }
            //已过期直接删除
            if (cur > cacheObject.getExpired()) {
                cachePool.remove(key);
                log.info("------缓存过期已删除------");
            }
        }
        return null;
    }

    /**
     * 根据key删除缓存
     *
     * @param key 缓存key
     */
    public void del(String key) {
        cachePool.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clean() {
        cachePool.clear();
    }

    /**
     * 缓存对象
     */
    static class CacheObject {
        private String key;
        private Object value;
        private long expired;

        public CacheObject(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public CacheObject(String key, Object value, long expired) {
            this.key = key;
            this.value = value;
            this.expired = expired;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public long getExpired() {
            return expired;
        }
    }


}

package com.jingyuan.zhifeng.core.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by cloud_000 on 12/16/2014.
 */
@Component("CacheHelper")
public class CacheHelper<K,V> {

    private final String CACHE_NAME = "friend";
//  自动装配注解好的manager和cache
    @Autowired
    private CacheManager cacheManager;

    private Cache cache;

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public Cache getCache() {
        return cache;
    }
    @Autowired
    public void setCache(CacheManager cacheManager) {
        cache = cacheManager.getCache(CACHE_NAME);
    }

//  切换cache
    public void changeCache(String cacheName)
    {
        this.cache = cacheManager.getCache(cacheName);
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    //  放入缓存
    public void put(K key,V value)throws CacheException
    {
        try
        {

            Element element = new Element(key,value);
            cache.put(element);

        }catch (Throwable t)
        {
            throw new CacheException(t);
        }
    }

    //  放入缓存
    public void putList(K key,List<V> value)throws CacheException
    {
        try
        {
            Element element = new Element(key,value);
            cache.put(element);

        }catch (Throwable t)
        {
            throw new CacheException(t);
        }
    }

//  通过Key获取element
    public Element get(K key)
    {
        return cache.get(key);
    }

//  获取cache的名字
    public String getCacheName()
    {
        return this.getCache().getName();
    }


    public Element remove(String key)
    {
        Element previous = cache.get(key);
        if(previous != null)
        {
            cache.remove(key);
        }
        return previous;
    }
//  拼接cache的key
    public String getCacheKey(int cacheId,Class entityClass)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(cacheId).append(".").append(entityClass.getName());
        return sb.toString();
    }

//  多参数拼接key
    public String getTwoIdCacheKey(Class entityClass,Object... objects)
    {
        StringBuffer sb = new StringBuffer();
        if (null != objects && objects.length != 0)
        {
            for (Object o : objects)
            {
                sb.append(o).append(".");
            }
        }
        sb.append(".").append(entityClass.getName());
        return sb.toString();
    }
}

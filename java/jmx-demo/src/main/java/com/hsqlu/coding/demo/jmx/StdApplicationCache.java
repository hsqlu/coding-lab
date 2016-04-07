package com.hsqlu.coding.demo.jmx;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created: 2016/4/6.
 * Author: Qiannan Lu
 */
public class StdApplicationCache implements IApplicationCache {
    private int maxCacheSize = 100;
    private List<Object> cache = Lists.newArrayList();

    @Override
    public synchronized void clearCache() {
        cache.clear();
    }

    @Override
    public synchronized int getCachedObjects() {
        return cache.size();
    }

    @Override
    public synchronized int getMaxCacheSize() {
        return maxCacheSize;
    }

    @Override
    public synchronized void setMaxCacheSize(int value) {
        if(value < 1) {
            throw new IllegalArgumentException("Value must be >= 1");
        }
        maxCacheSize = value;
    }

    public synchronized void cacheObject(Object o) {
        while(cache.size() >= maxCacheSize) {
            cache.remove(0);
        }
        cache.add(o);
    }
}

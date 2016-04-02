package com.hsqlu.coding.demo.jmx;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public class ApplicationCacheMBeanImpl implements ApplicationCacheMBean {
    private ApplicationCache _cache;

    public ApplicationCacheMBeanImpl(ApplicationCache cache){
        _cache = cache;
    }

    @Override
    public int getMaxCacheSize() {
        return _cache.getMaxCacheSize();
    }

    @Override
    public void setMaxCacheSize(int value) {
        _cache.setMaxCacheSize(value);
    }

    @Override
    public int getCachedObjects() {
        return _cache.getCachedObjects();
    }

    @Override
    public void clearCache() {
        _cache.clearCache();
    }
}

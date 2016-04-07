package com.hsqlu.coding.demo.jmx;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public class ApplicationCache implements ApplicationCacheMBean {
    private IApplicationCache _cache;

    public ApplicationCache(IApplicationCache cache){
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

package com.hsqlu.coding.demo.jmx;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public interface ApplicationCacheMBean {
    int getMaxCacheSize();
    void setMaxCacheSize(int value);
    int getCachedObjects();
    void clearCache();
}

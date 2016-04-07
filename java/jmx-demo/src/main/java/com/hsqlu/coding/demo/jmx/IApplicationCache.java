package com.hsqlu.coding.demo.jmx;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public interface IApplicationCache {
    int getMaxCacheSize();
    void setMaxCacheSize(int value);
    int getCachedObjects();
    void clearCache();
}

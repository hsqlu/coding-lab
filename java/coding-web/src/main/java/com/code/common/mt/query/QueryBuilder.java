package com.code.common.mt.query;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created: 2016/4/19.
 * Author: Qiannan Lu
 */
public class QueryBuilder {

    private static final String DEFAULT_QUERY_PROVIDER_NAME = "default";

    private static final Map<String, QueryProvider> providers = new ConcurrentHashMap<>();

    public static void registerDefaultProvider(QueryProvider provider) {
        registerProvider(DEFAULT_QUERY_PROVIDER_NAME, provider);
    }

    public static void registerProvider(String name, QueryProvider provider) {
        providers.put(name, provider);
    }

    public static Query newInstance() {
        return newInstance(DEFAULT_QUERY_PROVIDER_NAME);
    }

    private static Query newInstance(String name) {
        QueryProvider provider = providers.get(name);
        if (provider == null)
            throw new IllegalArgumentException("No query provider registered with name: " + name);
        return provider.newQuery();
    }
}

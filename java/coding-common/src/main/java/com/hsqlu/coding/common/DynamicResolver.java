package com.hsqlu.coding.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class DynamicResolver implements ScopeMetadataResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicResolver.class);

    @Override
    public ScopeMetadata resolveScopeMetadata(BeanDefinition beanDefinition) {
        return null;
    }
}

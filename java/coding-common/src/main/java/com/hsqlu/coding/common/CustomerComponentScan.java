package com.hsqlu.coding.common;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@ComponentScan(scopeResolver = DynamicResolver.class)
public @interface CustomerComponentScan {
}

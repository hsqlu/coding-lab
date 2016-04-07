package com.hsqlu.coding.demo.jmx.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created: 2016/4/6.
 * Author: Qiannan Lu
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:application-context.xml");

        Thread.sleep(Long.MAX_VALUE);
    }
}

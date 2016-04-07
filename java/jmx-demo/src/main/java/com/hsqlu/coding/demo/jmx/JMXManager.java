package com.hsqlu.coding.demo.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public class JMXManager {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        StdApplicationCache cache = new StdApplicationCache();
        ApplicationCacheMBean mBean = new ApplicationCache(cache);
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.hsqlu.coding.demo.jmx:name=ApplicationCacheMBean");
        server.registerMBean(mBean, name);
        imitateActivity(cache);
    }

    private static void imitateActivity(StdApplicationCache cache) {
        while (true) {
            try {
                cache.cacheObject(new Object());
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

package com.hsqlu.coding.demo.jmx;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created: 2016/4/1.
 * Author: Qiannan Lu
 */
public class JMXManager {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        ApplicationCache cache = new ApplicationCache();
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.hsqlu.coding.demo.jmx:type=ApplicationCacheMBean");
        server.registerMBean(cache, name);
        imitateActivity(cache);
    }

    private static void imitateActivity(ApplicationCache cache) {
        while (true) {
            try {
                cache.cacheObject(new Object());
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

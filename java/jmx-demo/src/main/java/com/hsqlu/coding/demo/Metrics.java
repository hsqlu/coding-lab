package com.hsqlu.coding.demo;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Map;

/**
 * Created: 2016/3/28.
 * Author: Qiannan Lu
 */
public class Metrics {
    private static final Logger log = LoggerFactory.getLogger(Metrics.class);
    private static final Metrics instance = new Metrics();
    private Map<String, Metric> metrics = Maps.newHashMap();

    public static Metrics getInstance() {
        return instance;
    }

    private Metrics(){}

    public Metrics register(String name, Metric metric) {
        metrics.put(name, metric);
        return this;
    }

    public void createMBean() {
        MetricsMBean mBean = new MetricsMBean(metrics);
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        try {
            final String name = MemoryMXBean.class.getPackage().getName() + ":type=" + MemoryMXBean.class.getSimpleName();
            log.debug("Registering MBean: {}", name);
            server.registerMBean(mBean, new ObjectName(name));
        } catch (MalformedObjectNameException | NotCompliantMBeanException | InstanceAlreadyExistsException | MBeanRegistrationException e) {
            log.warn("Error registering metrics mbean", e);
        }
    }
}

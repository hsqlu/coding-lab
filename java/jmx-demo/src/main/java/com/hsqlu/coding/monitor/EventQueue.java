package com.hsqlu.coding.monitor;

import com.hsqlu.coding.monitor.log.filter.IQuery;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created: 2016/4/7.
 * Author: Qiannan Lu
 */
public class EventQueue {
    String host = "localhost";  // or some A.B.C.D

    public static void main(String[] args) {
        try {
            new EventQueue().connect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException, MalformedObjectNameException {
        int port = 1009;
        String url = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        JMXServiceURL serviceUrl = new JMXServiceURL(url);
        JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
        try {
            MBeanServerConnection mbeanConn = jmxConnector.getMBeanServerConnection();
            // now query to get the beans or whatever
            Set<ObjectName> beanSet = mbeanConn.queryNames(new ObjectName("java.lang:type=Memory"), null);
        } finally {
            jmxConnector.close();
        }
    }
}

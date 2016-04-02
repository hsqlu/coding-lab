package com.hsqlu.coding.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import javax.management.*;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.stream.Collectors;

/**
 * Created: 2016/3/28.
 * Author: Qiannan Lu
 */
public class MetricsMBean implements DynamicMBean {
    private final Map<String, Metric> metrics;

    public MetricsMBean(Map<String, Metric> metrics) {
        this.metrics = Maps.newHashMap(metrics);
    }

    @Override
    public Object getAttribute(String attribute) throws AttributeNotFoundException, MBeanException, ReflectionException {
        Metric metric = metrics.get(attribute);
        if (metric == null)
            throw new AttributeNotFoundException("Attribute " + attribute + "not found");
        return metric.getValue();
    }

    @Override
    public void setAttribute(Attribute attribute) throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
        // 我们仅仅需要做监控，没有设置属性的需要，所以直接抛异常
        throw new UnsupportedOperationException("Setting attribute is not supported");
    }

    @Override
    public AttributeList getAttributes(String[] attributes) {
        AttributeList attrList = new AttributeList();
        for (String attr : attributes) {
            Metric metric = metrics.get(attr);
            if (metric != null)
                attrList.add(new Attribute(attr, metric.getValue()));
        }
        return attrList;
    }

    @Override
    public AttributeList setAttributes(AttributeList attributes) {
        // 我们仅仅需要做监控，没有设置属性的需要，所以直接抛异常
        throw new UnsupportedOperationException("Setting attribute is not supported");
    }

    @Override
    public Object invoke(String actionName, Object[] params, String[] signature) throws MBeanException, ReflectionException {
        // 方法调用也是不需要实现的
        throw new UnsupportedOperationException("Invoking is not supported");
    }

    @Override
    public MBeanInfo getMBeanInfo() {
        SortedSet<String> names = Sets.newTreeSet(metrics.keySet());
        List<MBeanAttributeInfo> attrInfo = Lists.newArrayList();
        attrInfo.addAll(names.stream().map(name -> new MBeanAttributeInfo(name,
                "long",
                "Metric " + name,
                true,
                false,
                false)).collect(Collectors.toList()));
        return new MBeanInfo(getClass().getName(),
                "Application Metrics",
                attrInfo.toArray(new MBeanAttributeInfo[attrInfo.size()]),
                null,
                null,
                null);
    }
}

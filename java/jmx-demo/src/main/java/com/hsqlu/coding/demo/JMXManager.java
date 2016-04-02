package com.hsqlu.coding.demo;

/**
 * Created: 2016/3/26.
 * Author: Qiannan Lu
 */
public class JMXManager {
    public static void main(String[] args) {
        // createMaxValueMetric 和 createCountMetric 可以基于同一份数据来得到
        // 最大值和次数的指标，详见下面 AverageMetric 的具体实现。
        Metrics.getInstance()
                .register("SearchAvgTime", MetricLoggers.searchTime)
                .register("SearchMaxTime", MetricLoggers.searchTime.createMaxValueMetric())
                .register("SearchCount", MetricLoggers.searchTime.createCountMetric())
                .createMBean();
    }
}


package com.hsqlu.coding.concurrent;

import org.junit.Test;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class CalculatorTest {
    @Test
    public void test() {
        for (int i = 1; i < 10; ++i) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
        }
    }

}
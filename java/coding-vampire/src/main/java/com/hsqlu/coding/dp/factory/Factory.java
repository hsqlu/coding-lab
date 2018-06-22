package com.hsqlu.coding.dp.factory;

/**
 * @author Qiannan Lu
 * @date 30/05/2018.
 */
public class Factory {
    public static void main(String[] args) {
        int a = 5, b = 6;

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);
    }
}

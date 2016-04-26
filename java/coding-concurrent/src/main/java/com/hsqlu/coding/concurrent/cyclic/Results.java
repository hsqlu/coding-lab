package com.hsqlu.coding.concurrent.cyclic;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class Results {
    private int[] data;

    public Results(int size) {
        data = new int[size];
    }

    public void setData(int position, int value) {
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}

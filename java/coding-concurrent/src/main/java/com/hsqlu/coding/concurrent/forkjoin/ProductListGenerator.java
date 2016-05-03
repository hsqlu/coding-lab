package com.hsqlu.coding.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: 2016/4/27.
 * Author: Qiannan Lu
 */
public class ProductListGenerator {
    public List<Product> generator(int size) {
        List<Product> ret = new ArrayList<>();

        for (int i = 0; i < size; ++i) {
            Product product = new Product();
            product.setName("Product " + i);
            product.setPrice(10);
            ret.add(product);
        }
        return ret;
    }
}

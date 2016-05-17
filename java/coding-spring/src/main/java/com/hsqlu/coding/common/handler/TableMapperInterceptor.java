package com.hsqlu.coding.common.handler;

import org.hibernate.EmptyInterceptor;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class TableMapperInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 1618350082903999124L;

    @Override
    public String onPrepareStatement(String sql) {
        return super.onPrepareStatement(sql.replace("", ""));
    }
}

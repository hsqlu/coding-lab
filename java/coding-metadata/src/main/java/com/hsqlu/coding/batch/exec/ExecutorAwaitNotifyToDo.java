package com.hsqlu.coding.batch.exec;

import com.hsqlu.coding.batch.ExecuteMessage;

import java.util.Map;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public class ExecutorAwaitNotifyToDo implements com.hsqlu.coding.batch.AwaitNotifier.AwaitNotifyToDo<String,com.hsqlu.coding.batch.ExecuteMessage> {
    @Override
    public void notifyDoSth(String s, ExecuteMessage message) {

    }

    @Override
    public void awaitDoSth(Map<String, ExecuteMessage> notifyInfo) {

    }

    @Override
    public void finallyDoSth(Map<String, ExecuteMessage> notifyInfo, boolean isInterrupted) {

    }
}

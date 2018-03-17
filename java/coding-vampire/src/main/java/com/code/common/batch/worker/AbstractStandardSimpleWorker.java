package com.code.common.batch.worker;

import com.code.common.batch.message.CommunicationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractStandardSimpleWorker<E, T> extends AbstractMiddleWorker<E, T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStandardSimpleWorker.class);

    public AbstractStandardSimpleWorker() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void executeWork() throws Exception {
        //正在执行或队列还未空
        while (working() || finishWithRestData()) {
            try {
                Object object = leader.takeRawRes(this.index);
                if (object instanceof CommunicationMessage) {
                    LOGGER.info("[{}]获取到完成标记，准备收尾!", this.organizationName);
                    break;
                } else {
                    T result = execute((E) object);
                    if (result != null) {
                        putProduct(result);
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.warn("[{}]在工作过程中收到中断消息!", this.organizationName, e);
            }
        }
        try {
            lastWork();
        } catch (InterruptedException e) {
            LOGGER.warn("[{}]在工作过程中收到中断消息!", this.organizationName, e);
        }
    }

    protected abstract T execute(E e) throws Exception;
}

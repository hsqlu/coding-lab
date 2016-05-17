package com.hsqlu.coding.batch.process;

import com.hsqlu.coding.batch.AwaitNotifier;
import com.hsqlu.coding.batch.ExecuteMessage;
import com.hsqlu.coding.batch.IOrganizable;
import com.hsqlu.coding.batch.OrganizationState;
import com.hsqlu.coding.lifecycle.LifecycleListener;
import com.hsqlu.coding.lifecycle.OutputResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collection;

/**
 * Created: 2016/5/6.
 * Author: Qiannan Lu
 */
public abstract class AbstractOrganizeAble<LEADER extends IOrganizable> extends Thread implements IOrganizable<LEADER> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected LEADER leader;

    protected String organizationName;

    protected String organizationType;

    protected int index;

    protected int organizationLevel = 0;

    protected AwaitNotifier<String, ExecuteMessage> notifier;

    protected long startTime;

    protected long endTime;

    protected OrganizationState state = OrganizationState.NEW;

    protected OutputResult finished = new OutputResult();

    protected MultiValueMap<OrganizationState, LifecycleListener> listeners = new LinkedMultiValueMap<>();

    public AbstractOrganizeAble(String name) {
        Assert.notNull(name, "");
        this.organizationName = name;
        super.setName(name);
    }

    @Override
    public void addListener(OrganizationState organizationState, LifecycleListener listener) {
        listeners.add(organizationState, listener);
    }

    @Override
    public void addListenerAllState(LifecycleListener listener) {
        for (OrganizationState state : OrganizationState.values()) {
            listeners.add(state, listener);
        }
    }

    @Override
    public Collection<LifecycleListener> getListeners(OrganizationState organizationState) {
        return listeners.get(organizationState);
    }

    @Override
    public OrganizationState initWorking() {
        try {
            assertExecute();
            preExecute();
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
            notifyListener(false, OrganizationState.ERROR, "【" + getOrganizationName() + "】初始化异常,异常消息[" + e.getMessage() + "]！", e);
//            throw new IllegalLogicException(message);
        }
        return state;
    }

    protected abstract void preExecute() throws Throwable;

    protected abstract void assertExecute();

    public void notifyListener(boolean error, OrganizationState state, String message, Throwable e) {
        notifyListener(error, state, message, e, null);
    }

    private void notifyListener(boolean error, OrganizationState state, String message, Throwable e, Object... infos) {
        this.state = state;
        if (listeners.get(state) != null) {
            for (LifecycleListener listener : listeners.get(state)) {
                try {
                    listener.handleEvent(this, this.state, e, message, infos);
                } catch (Throwable ee) {
                    LOGGER.error(ee.getMessage());
                }
            }
        }
    }

    @Override
    public synchronized boolean isFinished() {
        return finished.isFinished();
    }

    public AwaitNotifier<String, ExecuteMessage> getNotifier() {
        return notifier;
    }

    @Override
    public String getOrganizationType() {
        return organizationType;
    }

    @Override
    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public LEADER getLeader() {
        return leader;
    }

    public void setLeader(LEADER leader) {
        this.leader = leader;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int getOrganizationLevel() {
        return organizationLevel;
    }

    public void setOrganizationLevel(int organizationLevel) {
        this.organizationLevel = organizationLevel;
    }
}

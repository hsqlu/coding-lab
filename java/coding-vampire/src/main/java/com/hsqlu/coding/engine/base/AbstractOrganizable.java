package com.hsqlu.coding.engine.base;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.hsqlu.coding.concurrent.AwaitNotifier;
import com.hsqlu.coding.engine.base.exception.IllegalLogicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Objects;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public abstract class AbstractOrganizable<LEADER extends IOrganizable> extends Thread implements IOrganizable<LEADER> {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    protected LEADER leader;
    private String organizationName;
    private String organizationType;
    private int organizationLevel;
    private int index;
    protected OrganizationState state = OrganizationState.NEW;

    protected OperateResult operateResult = new OperateResult();

    protected long startTime;
    protected long endTime;

    protected AwaitNotifier<String, ExecuteMessage> notifier;

    protected Multimap<OrganizationState, LifecycleListener> listeners = LinkedListMultimap.create();

    public AbstractOrganizable(String organizationName) {
        this.organizationName = organizationName;
        setName(organizationName);
    }

    protected abstract void assertExecute();

    public abstract void preExecute();

    public void notifyListener(boolean error, OrganizationState state, String message, Throwable e, Object... infoArray) {
        Collection<LifecycleListener> stateListeners = listeners.get(state);
        this.state = state;
        stateListeners.forEach(listener -> {
            try {
                listener.handleEvent(this, state, e, message, infoArray);
            } catch (Throwable t) {
                LOGGER.error("Error happened while " + listener.getClass() + " handle state [" + state + "] event.", t);
            }
        });
    }

    public void notifyListener(boolean error, OrganizationState state, String message, Throwable e) {
        notifyListener(error, state, message, e, null);
    }

    @Override
    public void addListener(OrganizationState state, LifecycleListener listener) {
        listeners.put(state, listener);
    }

    @Override
    public void addListenerAllState(LifecycleListener listener) {
        for (OrganizationState state : OrganizationState.values()) {
            listeners.put(state, listener);
        }
    }

    @Override
    public Collection<LifecycleListener> getListeners(OrganizationState state) {
        return listeners.get(state);
    }

    @Override
    public OrganizationState initWork() {
        try {
            assertExecute();
            preExecute();
        } catch (Throwable e) {
            String errorMessage = "Error happened while init work of [" + getOrganizationName() + "]";
            LOGGER.error(errorMessage, e);
            notifyListener(false, OrganizationState.ERROR, errorMessage + ". Exception stack info: " + e.getMessage(), e);
            throw new IllegalLogicException(errorMessage);
        }
        return state;
    }

    @Override
    public OperateResult startWork() {
        notifyListener(false, OrganizationState.STARTED, "Engine [" + getOrganizationName() + "] started.", null);
        start();
        return operateResult;
    }

    @Override
    public OrganizationState currentState() {
        return state;
    }

    @Override
    public synchronized boolean isFinished() {
        return operateResult.isFinished();
    }

    public AwaitNotifier<String, ExecuteMessage> getNotifier() {
        return notifier;
    }

    @Override
    public LEADER getLeader() {
        return leader;
    }

    public void setLeader(LEADER leader) {
        this.leader = leader;
    }

    @Override
    public String getOrganizationName() {
        return organizationName;
    }

    @Override
    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    @Override
    public int getOrganizationLevel() {
        return organizationLevel;
    }

    public void setOrganizationLevel(int organizationLevel) {
        this.organizationLevel = organizationLevel;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}

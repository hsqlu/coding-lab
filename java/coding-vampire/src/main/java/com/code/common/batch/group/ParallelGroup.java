package com.code.common.batch.group;

import com.code.common.batch.IOrganizable;
import com.code.common.batch.message.CommunicationMessage;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroup extends AbstractGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParallelGroup.class);

    protected List<IOrganizable> lowers = Lists.newArrayList();

    public ParallelGroup(String organizationName) {
        super(organizationName);
    }

    public void addLower(IOrganizable lower) {
        Preconditions.checkNotNull(lower, "下级不允许为空");
        lower.setLeader(this);
        lower.setIndex(this.lowers.size());
        lower.setOrganizationName(this.organizationName + "-[" + lower.getIndex() + "]号-" + lower.getOrganizationType());
        this.lowers.add(lower);
    }

    @Override
    protected int lowerLength() {
        return lowers.size();
    }

    @Override
    protected void startLowersWorking() {
        Preconditions.checkState(!lowers.isEmpty(), "工厂不能没有生产车间!");
        for (IOrganizable lower : lowers) {
            LOGGER.info("{0}级[{1}]开始启动生产!", lower.getOrganizationLevel(), lower.getOrganizationName());
            lower.setOrganizationLevel(organizationLevel + 1);
            lower.startWorking();
        }
    }

    @Override
    protected void notifyErrorToLowers(CommunicationMessage errorMessage) {
        this.lowers.stream().filter(lower -> !lower.isFinished()).forEach(lower -> {
            lower.receiveError(CommunicationMessage.errorMessage(
                    this.organizationName,
                    this.organizationLevel,
                    errorMessage.getMessage()));
        });
    }

    @Override
    public int lowerCount() {
        return lowers.size();
    }

    @Override
    public void receiveFinishOnHand(CommunicationMessage finishMessage) {
    }
}

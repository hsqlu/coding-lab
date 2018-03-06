package com.code.common.batch.group;

import com.code.common.batch.IGroup;
import com.code.common.batch.message.CommunicationMessage;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SerialGroup extends AbstractGroup {
    private static final Logger LOGGER = LoggerFactory.getLogger(SerialGroup.class);

    protected List<IGroup> lowers = Lists.newArrayList();

    public SerialGroup(String name) {
        super(name);
    }

    private void addLower(IGroup lower) {
        Preconditions.checkNotNull(lower, "加入的生产车间不允许为空");
        lower.setIndex(this.lowers.size());
        if (!this.lowers.isEmpty()) {
            IGroup beforeGroup = this.lowers.get(this.lowers.size() - 1);
            beforeGroup.setNextGroup(lower);
        }
        this.lowers.add(lower);
    }

//    public void addStartingShop(String shopName,int queueSize,AbstractStartingWorker<?>... workers){
//        Assert.notEmpty(workers,"工人不允许为空");
//        SingleShop as = new SingleShop(this, shopName, queueSize){};
//        for (AbstractWorker worker : workers) {
//            as.addLower(worker);
//            worker.setLeader(as);
//        }
//        as.init();
//        addLower(as);
//    }
//
//    public void addMiddleShop(String shopName,int queueSize,AbstractMiddleWorker<?,?>... workers){
//        Assert.notEmpty(workers,"工人不允许为空");
//        SingleShop as = new SingleShop(this, shopName, queueSize){};
//        for (AbstractMiddleWorker<?,?> worker : workers) {
//            as.addLower(worker);
//            worker.setLeader(as);
//        }
//        as.init();
//        this.addLower(as);
//    }
//
//    public void addEndingShop(String shopName,AbstractEndingWorker<?>... workers){
//        Assert.notEmpty(workers,"工人不允许为空");
//        SingleShop as = new SingleShop(this, shopName, 1){};
//        for (AbstractEndingWorker<?> worker : workers) {
//            as.addLower(worker);
//            worker.setLeader(as);
//        }
//        as.init();
//        this.addLower(as);
//    }


    @Override
    protected int lowerLength() {
        return lowers.size();
    }

    @Override
    protected void startLowersWorking() {
        Preconditions.checkState(!lowers.isEmpty(), "工厂不能没有生产车间!");
        for (IGroup lower : lowers) {
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
        boolean notify = false;
        for (IGroup lower : lowers) {
            //通知下一个车间上游车间已经工作完成
            if (notify && !lower.isFinished()) {
                LOGGER.info("[{0}]开始通知[{1}]的下游车间[{2}]尽快完成剩余生产!",
                        this.organizationName,
                        finishMessage.getSender(),
                        lower.getOrganizationName());
                lower.receiveFinishOnHand(CommunicationMessage.finishMessage(
                        this.organizationName,
                        this.organizationLevel));
                break;
            }
            if (lower.getOrganizationName().equals(finishMessage.getSender())) {
                notify = true;
            }
        }
    }
}

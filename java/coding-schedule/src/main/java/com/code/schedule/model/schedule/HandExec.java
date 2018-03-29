/**
 * 版权所有：厦门市巨龙软件工程有限公司
 * Copyright 2010 Xiamen Dragon Software Eng. Co. Ltd.
 * All right reserved.
 * ====================================================
 * 文件名称: HandExec.java
 * 修订记录：
 * No    日期				作者(操作:具体内容)
 * 1.    2010-7-22			李炜(创建:创建文件)
 * ====================================================
 * 类描述：(说明未实现或其它不应生成javadoc的内容)
 */
package com.code.schedule.model.schedule;

import com.code.schedule.model.ScheduleModeType;

public class HandExec extends AbstractExec {
    public HandExec() {
        this.type = ScheduleModeType.HAND;
    }
}

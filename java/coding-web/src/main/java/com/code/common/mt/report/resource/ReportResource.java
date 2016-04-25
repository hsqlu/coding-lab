package com.code.common.mt.report.resource;

import com.code.common.mt.report.EventWrapper;
import com.code.common.mt.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created: 2016/4/13.
 * Author: Qiannan Lu
 */
@RestController
@RequestMapping("/report")
public class ReportResource {

    @Autowired
    private ReportService reportService;

    @RequestMapping()
    public void postEvent(EventWrapper eventWrapper) {
        reportService.create(eventWrapper);
    }
}

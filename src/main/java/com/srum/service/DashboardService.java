package com.srum.service;

import com.srum.dto.DashboardReportDTO;

import java.util.Date;


/**
 * AUTHOR:TuNT26
 * CREATED DATE: 14/09/2021
 */
public interface DashboardService {
    DashboardReportDTO countAllClassByStatusAndType(String type);

    DashboardReportDTO countAllClassByStatusAndTypeInPeriod(String type, Date openDate, Date endDate);

    DashboardReportDTO countAllTraineeByClassStatusAndClassType(String type);

    DashboardReportDTO countAllTraineeByClassStatusAndClassTypeInPeriod(String type, Date openDate, Date endDate);
}

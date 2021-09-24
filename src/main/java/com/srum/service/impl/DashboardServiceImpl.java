package com.srum.service.impl;

import com.srum.dto.DashboardReportDTO;
import com.srum.repository.ClassRepository;
import com.srum.repository.TraineeRepository;
import com.srum.service.DashboardService;
import com.srum.util.type.StatusClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * AUTHOR:TuNT26
 * CREATED DATE: 14/09/2021
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Override
    @Transactional
    public DashboardReportDTO countAllClassByStatusAndType(String type) {
        int countWaiting, countRunning, countRelease;
        countWaiting = classRepository.countAllByStatusAndType(StatusClass.WAITING, type);
        countRunning = classRepository.countAllByStatusAndType(StatusClass.RUNNING, type);
        countRelease = classRepository.countAllByStatusAndType(StatusClass.RELEASE, type);
        return new DashboardReportDTO(countWaiting, countRunning, countRelease);
    }

    @Override
    @Transactional
    public DashboardReportDTO countAllClassByStatusAndTypeInPeriod(String type, Date openDate, Date endDate) {
        int countWaiting, countRunning, countRelease;
        countWaiting = classRepository.countAllByStatusAndTypeInPeriod(StatusClass.WAITING, type, openDate, endDate);
        countRunning = classRepository.countAllByStatusAndTypeInPeriod(StatusClass.RUNNING, type, openDate, endDate);
        countRelease = classRepository.countAllByStatusAndTypeInPeriod(StatusClass.RELEASE, type, openDate, endDate);
        return new DashboardReportDTO(countWaiting, countRunning, countRelease);
    }

    @Override
    @Transactional
    public DashboardReportDTO countAllTraineeByClassStatusAndClassType(String type) {
        int countWaiting, countRunning, countRelease;
        countWaiting = traineeRepository.countAllByClazz_StatusAndClazz_Type(StatusClass.WAITING, type);
        countRunning = traineeRepository.countAllByClazz_StatusAndClazz_Type(StatusClass.RUNNING, type);
        countRelease = traineeRepository.countAllByClazz_StatusAndClazz_Type(StatusClass.RELEASE, type);
        return new DashboardReportDTO(countWaiting, countRunning, countRelease);
    }

    @Override
    @Transactional
    public DashboardReportDTO countAllTraineeByClassStatusAndClassTypeInPeriod(String type, Date openDate, Date endDate) {
        int countWaiting, countRunning, countRelease;
        countWaiting = traineeRepository.countAllByClassStatusAndClassTypeInPeriod(StatusClass.WAITING, type, openDate, endDate);
        countRunning = traineeRepository.countAllByClassStatusAndClassTypeInPeriod(StatusClass.RUNNING, type, openDate, endDate);
        countRelease = traineeRepository.countAllByClassStatusAndClassTypeInPeriod(StatusClass.RELEASE, type, openDate, endDate);
        return new DashboardReportDTO(countWaiting, countRunning, countRelease);
    }
}

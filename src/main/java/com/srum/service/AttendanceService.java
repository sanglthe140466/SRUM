package com.srum.service;

import com.srum.entity.Attendance;

import java.util.List;


/**
 * @author sanglthe140466
 */
public interface AttendanceService {
    List<Attendance> getAttendanceListByTrainee(Long id);
}

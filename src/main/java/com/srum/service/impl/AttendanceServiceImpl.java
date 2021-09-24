package com.srum.service.impl;

import com.srum.entity.Attendance;
import com.srum.repository.AttendanceRepository;
import com.srum.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sanglthe140466
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Override
	public List<Attendance> getAttendanceListByTrainee(Long id) {
		return attendanceRepository.getAttendanceByTrainee(id);
	}
}

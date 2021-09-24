package com.srum.service.impl;

import com.srum.entity.Trainee;
import com.srum.repository.AttendanceRepository;
import com.srum.repository.TraineeRepository;
import com.srum.service.TraineeService;
import com.srum.util.type.TypeAttendance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TraineeServiceImpl.class);

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Override
	@Transactional
	public List<Trainee> getAllTraineeByClassId(Long classId) {
		return traineeRepository.getTraineeByClazzId(classId);
	}
	
	@Override
	@Transactional
	public List<Trainee> getAllTrainees() {
		return traineeRepository.getTraineeList();
	}

	@Override
	@Transactional
	public Trainee getTraineeByTraineeId(Long traineeId) {
		return traineeRepository.findById(traineeId).orElse(null);
	}

	@Override
	public Trainee getTraineeByAccountAndEmail(String account,String email) {
		return traineeRepository.getTraineeByAccount(account,email);
	}

	@Override
	@Transactional
	public Float getAvgScoreByTraineeId(Long traineId) {
		Float avg = traineeRepository.getAvgScoreByTraineeId(traineId);
		if (avg != null){
			return avg * 10;
		}
		return null;
	}

	@Override
	public boolean saveTrainee(Trainee trainee) {
		boolean check = false;
		try {
			traineeRepository.save(trainee);
			check = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return check;
	}

	@Override
	@Transactional
	public int countTotalAttendanceByTraineeId(Long traineeId) {
		return attendanceRepository.getCountTotalAttendanceByTraineeId(traineeId);
	}

	@Override
	@Transactional
	public int countAttendanceTypePresentByTraineeId(TypeAttendance type, Long traineeId) {
		return attendanceRepository.getCountAttendanceTypePresentByTraineeId(type, traineeId);
	}
}

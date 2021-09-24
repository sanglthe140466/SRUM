package com.srum.repository;


import com.srum.entity.Trainee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TraineeRepository extends CrudRepository<Trainee, Long> {
	List<Trainee> getTraineeByClazzId(Long classId);
	
	@Query("From Trainee order by name asc ")
	List<Trainee> getTraineeList();

	@Query("SELECT AVG(s.value) FROM Score s WHERE s.trainee.id = :traineeId")
	Float getAvgScoreByTraineeId(Long traineeId);

	int countAllByClazz_StatusAndClazz_Type(int status, String type);

	@Query("SELECT COUNT(t) FROM Trainee t INNER JOIN t.clazz c WHERE c.status = :status " +
			"AND c.type = :type " +
			"AND c.openDate >= :openDate " +
			"AND c.endDate <= :endDate")
	int countAllByClassStatusAndClassTypeInPeriod(int status, String type, Date openDate, Date endDate);

	@Query("From Trainee where account =:account or email =:email ")
	Trainee getTraineeByAccount(String account,String email);
	
}

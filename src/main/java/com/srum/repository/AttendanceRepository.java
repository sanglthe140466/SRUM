package com.srum.repository;

import com.srum.entity.Attendance;
import com.srum.util.type.TypeAttendance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 08/09/2021
 */
@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.user.id = :traineeId")
    int getCountTotalAttendanceByTraineeId(Long traineeId);

    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.type = :type AND a.user.id = :traineeId")
    int getCountAttendanceTypePresentByTraineeId(TypeAttendance type, Long traineeId);

    @Query("FROM Attendance  where user.id= ?1")
    List<Attendance> getAttendanceByTrainee(Long id);
}

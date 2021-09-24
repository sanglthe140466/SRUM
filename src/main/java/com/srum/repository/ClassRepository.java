package com.srum.repository;

import com.srum.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 31/08/2021
 */
@Repository
public interface ClassRepository extends JpaRepository<Clazz, Long> {
    Clazz findClazzByName(String name);

    List<Clazz> findByOrderByNameDesc();

    Clazz findClazzById(Long classId);

    int countAllByStatusAndType(int status, String type);

    @Query("SELECT COUNT(c) FROM Clazz c WHERE c.status = :status " +
            "AND c.type = :type " +
            "AND c.openDate >= :openDate " +
            "AND c.endDate <= :endDate")
    int countAllByStatusAndTypeInPeriod(int status, String type, Date openDate, Date endDate);
}

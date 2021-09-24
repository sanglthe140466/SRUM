package com.srum.repository;

import com.srum.entity.Interview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 09/09/2021
 */
@Repository
public interface InterviewRepository extends CrudRepository<Interview, Long> {
    List<Interview> findAllByTraineeId(Long traineeId);
}

package com.srum.repository;

import com.srum.entity.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 15/09/2021
 */
@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Long> {
    List<Feedback> findByOrderByConsultDateDesc();

    List<Feedback> findAllByClazz_Id(Long classId);
}

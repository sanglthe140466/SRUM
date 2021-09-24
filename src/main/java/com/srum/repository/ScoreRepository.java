package com.srum.repository;

import com.srum.entity.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AUTHOR:VuGT
 */
@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
    List<Score> getAllByTraineeId(Long traineeId);
}

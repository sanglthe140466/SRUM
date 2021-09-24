package com.srum.repository;

import com.srum.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 01/09/2021
 */
@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findTrainerById(Long id);
}

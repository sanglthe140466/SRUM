package com.srum.repository;

import com.srum.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
}

package com.srum.repository;

import com.srum.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Trung Sang (sanglthe140466)
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

}

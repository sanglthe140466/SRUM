package com.srum.repository;

import com.srum.entity.Mistake;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ThoNN1
 */
@Repository
public interface MistakeRepository extends CrudRepository<Mistake, Long> {
    Mistake findMistakeById(Long mistakeId);

    void deleteById(Long id);
}

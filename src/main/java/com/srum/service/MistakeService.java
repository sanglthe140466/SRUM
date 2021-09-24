package com.srum.service;

import com.srum.entity.Mistake;

/**
 * @author ThoNN1
 */
public interface MistakeService {
    void save(Mistake mistake);

    Mistake findMistakeById(Long mistakeId);

    void deleteMistakebyId(Long id);

}

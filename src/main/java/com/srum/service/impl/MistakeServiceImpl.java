package com.srum.service.impl;

import com.srum.entity.Mistake;
import com.srum.repository.MistakeRepository;
import com.srum.service.MistakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ThoNN1
 */
@Service
public class MistakeServiceImpl implements MistakeService {

    @Autowired
    private MistakeRepository mistakeRepository;

    @Override
    @Transactional
    public void save(Mistake mistake) {
        mistakeRepository.save(mistake);
    }

    @Override
    @Transactional
    public Mistake findMistakeById(Long mistakeId) {
        return mistakeRepository.findMistakeById(mistakeId);
    }

    @Override
    @Transactional
    public void deleteMistakebyId(Long id) {
        mistakeRepository.deleteById(id);
    }

}

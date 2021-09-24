package com.srum.service;

import com.srum.entity.Subject;

import java.util.List;

/**
 * @author Trung Sang (sanglthe140466)
 */
public interface SubjectService {
	List<Subject> getSubjectList();
	
	Subject getSubjectByID(Long id);
}


package com.application.web.university.service;

import java.util.List;
import java.util.Optional;

import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;

public interface SubjectService {

	Optional<Subject> findById(Long id);

	List<Subject> findAll();

	Subject save(Subject subject,String studentFile);

	Optional<Teacher> findTeacherBySubjectId(Long subjectId);

}

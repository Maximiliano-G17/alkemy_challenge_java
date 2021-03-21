package com.application.web.university.service;

import java.util.List;
import java.util.Optional;

import com.application.web.university.domain.Teacher;

public interface TeacherService {

	List<Teacher> findAll();

	Optional<Teacher> findById(Long id);

	Teacher save(Teacher teacher);

}

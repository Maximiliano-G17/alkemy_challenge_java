package com.application.web.university.service;

import java.util.Optional;

import com.application.web.university.domain.Student;

public interface StudentService {

	Optional<Student> findById(Long id);

	Student save(Student student);

	Optional<Student> findByLegajo(String legajo);

}

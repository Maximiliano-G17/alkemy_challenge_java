package com.application.web.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.web.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByLegajo(String legajo);

	Student findByDni(String dni);

}

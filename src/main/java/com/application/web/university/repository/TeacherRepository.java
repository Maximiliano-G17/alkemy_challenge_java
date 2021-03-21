package com.application.web.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.web.university.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}

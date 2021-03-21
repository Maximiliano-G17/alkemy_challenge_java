package com.application.web.university.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

	@Query(value ="select s.teacher from Subject s where s.id = :subjectId")
	Optional<Teacher> findTeacherBySubjectId(Long subjectId);

}

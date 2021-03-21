package com.application.web.university.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.web.university.domain.Student;
import com.application.web.university.repository.StudentRepository;
import com.application.web.university.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	
	public Optional<Student> findById(Long id){
		return studentRepo.findById(id);
	}


	@Override
	public Student save(Student student) {
		return studentRepo.save(student);
	}


	@Override
	public Optional<Student> findByLegajo(String legajo) {
		return studentRepo.findByLegajo(legajo);
	}

}

package com.application.web.university.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.web.university.domain.Teacher;
import com.application.web.university.repository.TeacherRepository;
import com.application.web.university.service.TeacherService;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherRepository teacherRepo;

	@Override
	public List<Teacher> findAll() {
		return teacherRepo.findAll();
	}

	@Override
	public Optional<Teacher> findById(Long id) {
		return teacherRepo.findById(id);
	}

	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepo.save(teacher);
	}

}

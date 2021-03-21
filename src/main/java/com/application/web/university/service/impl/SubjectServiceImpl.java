package com.application.web.university.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.web.university.domain.Student;
import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;
import com.application.web.university.repository.StudentRepository;
import com.application.web.university.repository.SubjectRepository;
import com.application.web.university.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Optional<Subject> findById(Long id) {
		return subjectRepo.findById(id);
	}

	@Override
	public List<Subject> findAll() {
		List<Subject> subjects = subjectRepo.findAll();
		List<Subject> orderedSubjects = subjects.stream().sorted().collect(Collectors.toList());
		return orderedSubjects;
	}

	@Override
	public Subject save(Subject subject,String studentFile) {
		if(!studentFile.isEmpty()) {
			subject = errorWhenRegisteringStudentAtTheSameSchedule(subject,studentFile);
		}	
		return subjectRepo.save(subject);
	}

	private Subject errorWhenRegisteringStudentAtTheSameSchedule(Subject subject,String studentFile) {
		Optional<Student> studentFound = studentRepo.findByLegajo(studentFile);
		if(studentFound.isPresent()) {
			boolean flag = false;
			List<String>  listSchedule = studentFound.get().getSubjects().stream()
																		.map(x -> x.getSchedule())
																		.collect(Collectors.toList());
			for(int i = 0; i < listSchedule.size(); i++) {
				if(listSchedule.get(i).equals(subject.getSchedule())) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				List<Subject> allSubjects = studentFound.get().getSubjects();
				if(allSubjects == null) {
					allSubjects = new ArrayList<>();
					allSubjects = studentFound.get().getSubjects();
				}
				allSubjects.add(subject);
				studentFound.get().setSubjects(allSubjects);
				List<Student> allStudents = subject.getStudents();
				if(allStudents == null) {
					allStudents = new ArrayList<>();
					allStudents.add(studentFound.get());
				}else {
					allStudents.add(studentFound.get());
				}		
				subject.setStudents(allStudents);
				capacityStudent(subject);
			}
		}	
		return subject;
	}

	private void capacityStudent(Subject subject) {
		int totalCapacity = Integer.parseInt(subject.getAvailableCapacity());
		int capacity = totalCapacity - 1;
		subject.setAvailableCapacity(String.valueOf(capacity));	
	}

	@Override
	public Optional<Teacher> findTeacherBySubjectId(Long subjectId) {
		return subjectRepo.findTeacherBySubjectId(subjectId);
	}
}

package com.application.web.university;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.web.university.domain.Teacher;
import com.application.web.university.service.TeacherService;

@SpringBootTest
public class TeacherServiceTest {
	
	@Autowired
	private TeacherService teacherService;
	
	@Test
	public void findAll_returnAListTeachers() {
		List<Teacher> teachersFound = teacherService.findAll();
		
		assertThat(teachersFound).isNotEmpty();
		assertEquals(1, teachersFound.size());
	}
	@Test
	public void findById_withIdExist_returnATeacher() {
		Long id = 1L;
		
		Optional<Teacher> teacherUpdated = teacherService.findById(id);
		
		assertThat(teacherUpdated).isNotEmpty();
		assertEquals(id, teacherUpdated.get().getId());
	}
	@Test
	public void findById_withIdNonexistent_returnEmpty() {
		Long id = -99L;
		
		Optional<Teacher> teacherUpdated = teacherService.findById(id);
		
		assertThat(teacherUpdated).isEmpty();
	}
	@Test
	public void updateTeacher_withIdExist_returnATeacherUpdated() {
		Long id = 1L;
			
		Optional<Teacher> teacher = teacherService.findById(id);
		teacher.get().setName("Martin");
		Teacher teacherUpdated = teacherService.save(teacher.get());
		
		assertThat(teacherUpdated).isNotNull();
		assertEquals(teacher.get().getName(),teacherUpdated.getName());
	}
}
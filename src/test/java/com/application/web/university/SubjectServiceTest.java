package com.application.web.university;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;
import com.application.web.university.service.SubjectService;

@SpringBootTest
public class SubjectServiceTest {
	
	@Autowired
	private SubjectService subjectService;
	
	@Test
	public void findAll_returnAListSubject() {
		List<Subject> subjectsFound = subjectService.findAll();
		
		assertThat(subjectsFound).isNotEmpty();
		assertEquals(1, subjectsFound.size());
	}
	@Test
	public void findById_withExistingId_returnASubject() {
		Long id = 1L;
		
		Optional<Subject> subjectFound = subjectService.findById(id);
		
		assertThat(subjectFound).isNotEmpty();
		assertEquals(id, subjectFound.get().getId());
	}
	@Test
	public void findById_withNonexistentId_returnEmpty() {
		Long id = -99L;
		
		Optional<Subject> subjectFound = subjectService.findById(id);
		
		assertThat(subjectFound).isEmpty();
	}
	@Test
	public void updateSubject_withExinstingId_returnASubjectUpdated() {
		Long id = 1L;
		String studentFile = "";
		Optional<Subject> subjectFound = subjectService.findById(id);
		
		subjectFound.get().setSchedule("10:00");
		Subject subjectUpdated = subjectService.save(subjectFound.get(),studentFile);
		
		assertEquals(subjectUpdated.getSchedule(), subjectFound.get().getSchedule());
	}
	@Test
	public void findTeacherBySubjectId_withExinstingId_returnATeacher() {
		Long subjectId = 1L;
		
		Optional<Teacher> teacherFound = subjectService.findTeacherBySubjectId(subjectId);

		assertThat(teacherFound).isNotEmpty();
		assertEquals(1L, teacherFound.get().getId());
	}
}
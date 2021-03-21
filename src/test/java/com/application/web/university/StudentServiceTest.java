package com.application.web.university;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.web.university.domain.Student;
import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;
import com.application.web.university.service.StudentService;

@SpringBootTest
public class StudentServiceTest {
	
	@Autowired
	private StudentService studentService;

	@Test
	public void findById_withIdExist_returnAStudent() {
		Long id = 1L;
		
		Optional<Student> studentFound = studentService.findById(id);
		
		assertThat(studentFound).isNotEmpty();
		assertEquals(id, studentFound.get().getId());
	}
	@Test
	public void findById_withIdNonexistent_returnEmpty() {
		Long id = -99L;
		
		Optional<Student> studentNotFound = studentService.findById(id);
		
		assertThat(studentNotFound).isEmpty();
	}
	@Test
	public void saveStudent_returnAStudent() {
		//I create the instances
		Student student = new Student();
		Teacher teacher = new Teacher();
		Subject subject = new Subject();
		List<Teacher> teachers = new ArrayList<>();		
		List<Subject> subjects = new ArrayList<>();
		
		
		//set the instances
		student.setId(2L);
		student.setDni("333");
		student.setLegajo("c232");
		student.setName("Carlos");
		student.setPassword();
		student.setRol("user");
		student.setSurname("lolo");		
					
		subject.setId(1L);
					
		teacher.setId(1L);
		
		subject.setTeacher(teacher);
		teacher.setSubjects(subjects);
		
		teachers.add(teacher);	
		subjects.add(subject);
		
		student.setSubjects(subjects);
		student.setTeachers(teachers);
		
		Student studentFound = studentService.save(student);
		
		assertThat(studentFound).isNotNull();
		assertEquals(studentFound.getName(), student.getName());
	}
	@Test
	public void findByLegajo_withLegajoExist_returnAStudent() {
		String legajo = "c222";
		
		Optional<Student> studentFound = studentService.findByLegajo(legajo);
		
		assertThat(studentFound).isNotEmpty();
		assertEquals(legajo, studentFound.get().getLegajo());
	}
	@Test
	public void findByLegajo_withLegajoNonexistent_returnAEmpty() {
		String legajo = "-ZZZZZZZZ";
		
		Optional<Student> studentFound = studentService.findByLegajo(legajo);
		
		assertThat(studentFound).isEmpty();
	}
}

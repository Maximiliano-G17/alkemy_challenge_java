package com.application.web.university.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.web.university.domain.Subject;
import com.application.web.university.domain.Teacher;
import com.application.web.university.service.StudentService;
import com.application.web.university.service.SubjectService;

import javassist.NotFoundException;

@Controller
@RequestMapping("api/subjects")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String findAll(Model model) {
		List<Subject> allSubject = subjectService.findAll();
		model.addAttribute("findAll", allSubject);
		return "views/subjects";
	}
	@GetMapping("/description/{id}")
	public String findById(@PathVariable Long id, Model model) {
		Optional<Subject> subjectFound = subjectService.findById(id);
		model.addAttribute("subject", subjectFound.get());
		return "views/subjectDescription";
	}
	@GetMapping("/teacher/{id}")
	public String findTeacherBySubjectId(@PathVariable Long id, Model model) {
		Optional<Teacher> teacherBySubjectId = subjectService.findTeacherBySubjectId(id);
		model.addAttribute("teacher", teacherBySubjectId.get());	
		return "views/teachers";
	}
	
	@GetMapping("/register/{id}")
	public String preRegister(@PathVariable Long id, Model model) {
		Optional<Subject> subjectFound = subjectService.findById(id);
		if (!subjectFound.isPresent()) {
			return "redirect:/api/subjects/";
		}
		model.addAttribute("subject", subjectFound.get());
		return "views/register";		
	}
	
	@PostMapping("/postRegister")
	public String postRegister(@Valid Subject subject, BindingResult result,@RequestParam String studentFile, Model model) throws NotFoundException {
		if(!studentService.findByLegajo(studentFile).isPresent()) {
			throw new NotFoundException("The student with file " + studentFile + " does not exist");
		}
		if (result.hasErrors() || studentFile.isEmpty()) {
			model.addAttribute("subject", subject);
			return "views/register";
		}
		String capacityBeforeSaving = subject.getAvailableCapacity();
		subjectService.save(subject,studentFile);	
		if(subject.getAvailableCapacity().equals(capacityBeforeSaving)){
			return "views/errorSave";
		}
		return "redirect:/api/subjects/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		Optional<Subject> subjectFound = subjectService.findById(id);
		if (!subjectFound.isPresent()) {
			return "redirect:/api/subjects/";
		}
		String name = subjectFound.get().getName();
		String title = "UPDATE SUBJECT: " + name.toUpperCase();
		Optional<Teacher> subjectTeacher = subjectService.findTeacherBySubjectId(id);
		model.addAttribute("teacher", subjectTeacher.get());
		model.addAttribute("subject", subjectFound.get());
		model.addAttribute("title", title);
		return "views/update";		
	}
	@PostMapping("/postUpdate")
	public String postUpdate(@Valid Subject subject,BindingResult result,Model model) {
		if (result.hasErrors()) {
			String name = subject.getName();
			String title = "UPDATE SUBJECT: " + name.toUpperCase();
			Optional<Teacher> subjectTeacher = subjectService.findTeacherBySubjectId(subject.getId());
			model.addAttribute("teacher", subjectTeacher.get());
			model.addAttribute("subject", subject);
			model.addAttribute("title", title);
			return "views/update";		
		}
		String studentFile ="";
		subjectService.save(subject,studentFile);
		return "redirect:/api/subjects/";
	}
}
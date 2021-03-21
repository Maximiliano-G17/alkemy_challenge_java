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

import com.application.web.university.domain.Teacher;
import com.application.web.university.service.TeacherService;

@Controller
@RequestMapping("api/teachers")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;

	@GetMapping("/")
	public String findAll(Model model) {
		List<Teacher> allSubject = teacherService.findAll();
		model.addAttribute("teacher", allSubject);
		return "views/teachers";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model) {
		Optional<Teacher> teacherFound = teacherService.findById(id);
		if (!teacherFound.isPresent()) {
			return "redirect:/api/subjects/";
		}
		String name = teacherFound.get().getSurname();
		String title = "UPDATE Teacher: " + name.toUpperCase();
		model.addAttribute("teacher", teacherFound.get());
		model.addAttribute("title", title);
		return "views/updateTeacher";		
	}
	@PostMapping("/postUpdate")
	public String postUpdate(@Valid Teacher teacher,BindingResult result, Model model) {
		if (result.hasErrors()) {
			String name = teacher.getSurname();
			String title = "UPDATE Teacher: " + name.toUpperCase();
			model.addAttribute("teacher", teacher);
			model.addAttribute("title", title);
			return "views/updateTeacher";	
		}
		teacherService.save(teacher);
		return "redirect:/api/teachers/";
	}
}

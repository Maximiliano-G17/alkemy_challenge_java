package com.application.web.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.web.university.domain.Director;
import com.application.web.university.domain.Student;
import com.application.web.university.repository.DirectorRepository;
import com.application.web.university.repository.StudentRepository;

@Service
public class SecurityService implements UserDetailsService{

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private DirectorRepository directorRepo;

	@Override
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
		Student studentFound = studentRepo.findByDni(dni);	
		if(studentFound == null) {
			Director directorFound = directorRepo.findByDni(dni);
			if(directorFound == null) {
				throw new UsernameNotFoundException(dni);
			}			
			return new User(directorFound.getDni(),directorFound.getPassword(),getRols(null, directorFound));
		}		
		return new User(studentFound.getDni(),studentFound.getPassword(),getRols(studentFound, null));
	}
	
	private List<GrantedAuthority> getRols(Student student, Director director) {
		List<GrantedAuthority> rols = new ArrayList<>();		
		if(student != null) {
			rols.add(new SimpleGrantedAuthority(student.getRol()));
		}else {
			rols.add(new SimpleGrantedAuthority(director.getRol()));
		}		
		return rols;
	}
}
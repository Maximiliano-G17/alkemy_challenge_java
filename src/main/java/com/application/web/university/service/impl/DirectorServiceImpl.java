package com.application.web.university.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.application.web.university.repository.DirectorRepository;
import com.application.web.university.service.DirectorService;

public class DirectorServiceImpl implements DirectorService{
	
	@Autowired
	private DirectorRepository directorRepo;

}

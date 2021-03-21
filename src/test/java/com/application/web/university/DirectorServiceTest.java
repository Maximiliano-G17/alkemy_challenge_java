package com.application.web.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.application.web.university.service.DirectorService;

@SpringBootTest
public class DirectorServiceTest {

	@Autowired
	private DirectorService directorService;
}

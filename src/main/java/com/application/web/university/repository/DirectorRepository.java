package com.application.web.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.web.university.domain.Director;

public interface DirectorRepository extends JpaRepository<Director, Long>{

	Director findByDni(String dni);
}

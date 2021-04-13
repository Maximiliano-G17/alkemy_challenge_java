package com.application.web.university;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
public class SecurityServiceTest {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Test
	public void findByDni_withDniExisting_returnAPerson() {
		String dni = "36860436";
		
		UserDetails personFound = userDetailsService.loadUserByUsername(dni);
		assertThat(personFound).isNotNull();
		assertEquals(dni, personFound.getUsername());
	}
	
	@Test
	public void findByDni_withDniNonexistent_returnUsernameNotFoundException(){	
		String dni = "ZZZZZZZz";
		Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
			userDetailsService.loadUserByUsername(dni);
	    });
		
		 String expectedMessage = dni;
		 String actualMessage = exception.getMessage();
		    
		 assertTrue(actualMessage.contains(expectedMessage));
	}
}
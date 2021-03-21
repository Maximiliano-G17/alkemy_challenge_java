package com.application.web.university;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/subjects/").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/subjects/description/**").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/subjects/register/**").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/subjects/postRegister").hasAnyAuthority("USER","ADMIN")
		.antMatchers("/api/subjects/teacher/**").hasAuthority("ADMIN")
		.antMatchers("/api/subjects/update/**").hasAuthority("ADMIN")
		.antMatchers("/api/subjects/postUpdate").hasAuthority("ADMIN")
		.antMatchers("/api/teachers/**").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/logout");
	}

	//NOTA IMPORTANTE: cuando se registra a un estudiante en la BBDD, hay que ponerle en password el legajo bcrypteado, 
	//				   asi el estudiante se puede logear  con su legajo, lo tuve que hacer asi porque cuando un 
	//				   estudiante se registra en la materia necesita de su legajo para anotarse, intente desencryptar el legajo
	//				   que ingresaba el alumno cuando se registraba pero me daba otro hash que no era como el del legajo 
	//				   que estaba en la BBDD.
	@Autowired
	public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {	
			builder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passEncoder)
			.usersByUsernameQuery("SELECT s.rol, s.password,'true' from students s WHERE s.dni = ?")
			.authoritiesByUsernameQuery("SELECT r.user,s.rol FROM students s INNER JOIN rols r ON r.user=s.rol WHERE r.user = ?");
			builder.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passEncoder)
			.usersByUsernameQuery("SELECT d.rol, d.password,'true' from directors d WHERE d.dni = ?")
			.authoritiesByUsernameQuery("SELECT r.user,d.rol FROM directors d INNER JOIN rols r ON r.user=d.rol WHERE r.user = ?");
	}
}
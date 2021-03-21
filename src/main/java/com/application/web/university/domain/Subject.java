package com.application.web.university.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "subjects")
public class Subject implements Comparable<Subject>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "The name cannot be empty")
	private String name;
	@NotBlank(message = "The schedule cannot be empty")
	private String schedule;
	@NotBlank(message = "The capacity student cannot be empty")
	private String maximum_amount_of_students;
	private String availableCapacity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="subjects")
	private Teacher teacher;	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	private List<Student> students;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getMaximum_amount_of_students() {
		return maximum_amount_of_students;
	}
	public void setMaximum_amount_of_students(String maximum_amount_of_students) {
		this.maximum_amount_of_students = maximum_amount_of_students;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public String getAvailableCapacity() {
		return availableCapacity;
	}
	public void setAvailableCapacity(String availableCapacity) {
		this.availableCapacity = availableCapacity;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", schedule=" + schedule + ", maximum_amount_of_students="
				+ maximum_amount_of_students + ", teacher=" + teacher + ", students=" + students + "]";
	}
	@Override
	public int compareTo(Subject o) {
		return this.name.compareTo(o.getName());
	}
}
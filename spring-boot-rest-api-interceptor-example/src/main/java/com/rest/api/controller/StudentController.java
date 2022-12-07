package com.rest.api.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.api.entity.Student;
import com.rest.api.repo.StudentRepo;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentRepo repo;

	@GetMapping("/")
	public List<Student> getStudent() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Student getById(@PathVariable int id) throws Exception {

		Optional<Student> student = repo.findById(id);
		if (student.isEmpty()) {

			throw new Exception("Student not found on given id " + id);

		}
		return student.get();
	}

	@PostMapping("/")
	public Student addStudent(@RequestBody Student student) {
		
		 Student newStudent = new Student();
		 newStudent.setId(student.getId());
		 newStudent.setName(student.getName());
		 
		return repo.save(newStudent);
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable int id, Student student) {
		repo.deleteById(id);
		return "student deleted of Id: " + student.getId();
	}

	@PutMapping("/{id}")
	public Student updateById(@PathVariable int id, @RequestBody Student student) {
		Optional<Student> newStudent = repo.findById(id);
		
		Student student1 = newStudent.get();
		student1.setId(student.getId());
		student1.setName(student.getName());
		return repo.save(student1);

	}

}

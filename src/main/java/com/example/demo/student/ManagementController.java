package com.example.demo.student;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class ManagementController {

	private static final List<Student> STUDENTS = Arrays.asList(
			new Student(1, "James Bond"),
			new Student(2, "Maria Jones"),
			new Student(3, "Anna Smith")
	);

	@GetMapping
	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	@PostMapping
	public void createStudent(@RequestBody Student student) {
		System.out.println("createStudent");
		System.out.println(student);
	}

	@PutMapping("/{studentId}")
	public void updateStudent(@PathVariable String studentId, @RequestBody Student student) {
		System.out.println("updateStudent");
		System.out.printf("%s, %s%n", studentId, student.getStudentName());
	}

	@DeleteMapping("/{studentId}")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("deleteStudent");
		System.out.println(studentId);
	}
}

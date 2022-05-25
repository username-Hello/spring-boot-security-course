package com.example.demo.student;

import com.example.demo.domain.Student;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINEE')")
	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	@PostMapping
	@PreAuthorize("hasAuthority('student:write')")
	public void createStudent(@RequestBody Student student) {
		System.out.println("createStudent");
		System.out.println(student);
	}

	@PutMapping("/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void updateStudent(@PathVariable String studentId, @RequestBody Student student) {
		System.out.println("updateStudent");
		System.out.printf("%s, %s%n", studentId, student.getStudentName());
	}

	@DeleteMapping("/{studentId}")
	@PreAuthorize("hasAuthority('student:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("deleteStudent");
		System.out.println(studentId);
	}
}

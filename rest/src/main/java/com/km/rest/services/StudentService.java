package com.km.rest.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.km.rest.models.Course;
import com.km.rest.models.Student;

@Component
public class StudentService {
	
	private static List<Student> students = new ArrayList<Student>();
	
	private SecureRandom random = new SecureRandom();
	
	static {
		
		// Courses Data
		Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course3 = new Course("Course3", "Spring Boot", "6K Students",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course course4 = new Course("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));
		
		//Students Data
		Student ranga = new Student("Student1", "Ranga Karanam",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Student satish = new Student("Student2", "Satish T",
				"Hiker, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));
		
		students.add(satish);
		students.add(ranga);
	}
	
	
	public Student retriveStudent(String studentId) {
		return students.stream().filter(student -> student.getId().equals(studentId))
				.findFirst()
				.orElse(null);
	}
	
	public List<Course> retriveCourses(String studentId) {
		Student student = retriveStudent(studentId);
		return (student == null) ? null : student.getCourses();
	}
	
	public Course retriveCourse(String studentId, String courseId) {
		
		Student student = retriveStudent(studentId);
		
		if(student == null) {
			return null;
		}
		
		return student.getCourses().stream().filter( course -> course.getId().equals(courseId))
									.findFirst().orElse(null);
	}
	
	public Course addCourse(String studentId, Course course) {
		Student student = retriveStudent(studentId);

		if (student == null) {
			return null;
		}
		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);
		student.getCourses().add(course);

		return course;
	}
}

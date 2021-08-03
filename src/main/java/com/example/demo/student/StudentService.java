package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {
	
	private StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public void addNewStudent(Student student) {
		
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("Email was taken");
		}
		
		studentRepository.save(student);
	}
	
	public void deleteStudent(Long studentId) {
		boolean exist = studentRepository.existsById(studentId);
		if(!exist) {
			throw new IllegalStateException("student id does not exist");
		}
		
		studentRepository.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email, String address, String mobileNo, LocalDate dob) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("student id does not exist"){
					
				});
		
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
		
		if(address != null && address.length() > 0 && !Objects.equals(student.getAddress(), address)) {
			student.setAddress(address);
		}
		
		if(mobileNo != null && mobileNo.length() > 0 && !Objects.equals(student.getMobileNo(), mobileNo)) {
			student.setMobileNo(mobileNo);
		}
		
		if(dob != null && !Objects.equals(student.getDob(), dob)) {
			student.setDob(dob);
		}
	}


}

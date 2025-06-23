package com.blt.etud.etudpaybackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blt.etud.etudpaybackend.dtos.StudentDto;
import com.blt.etud.etudpaybackend.entities.Student;

@Service
@Transactional
public class StudentServices {
	private StudentDto dto;
	public StudentDto stutDto(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.firstname(student.getFirstname())
				.lastname(student.getLastname())
				.code(student.getCode())
				.programId(student.getProgramId())
				.photo(student.getPhoto())
				.build();
	}
	public List<StudentDto> etudto(List<Student> st){
		return st.stream().map(this::stutDto).toList();
		
	}
	

}

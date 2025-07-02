package com.blt.etud.etudpaybackend.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blt.etud.etudpaybackend.dtos.StudentDto;
import com.blt.etud.etudpaybackend.dtos.StudentNameDto;
import com.blt.etud.etudpaybackend.entities.Student;
import com.blt.etud.etudpaybackend.repositories.StudentRepository;
import com.blt.etud.etudpaybackend.services.StudentServices;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentsController {
	private final StudentRepository studentRepository;
	private final StudentServices studentServices;
	
	
	@GetMapping(path="/allStudents")
	public List<StudentDto> getAllStudents(){
		List<Student> listStud = studentRepository.findAll();
		return studentServices.etudto(listStud);
	}
	
	@GetMapping(path="/students/{code}")
	public Optional<Student> getStudentByCode(@PathVariable String code) {
		return studentRepository.findByCode(code);
	}
	
	
	@GetMapping(path="/studentsByProgramId")
	public List<Student> getStudentByProgramId(@RequestParam String programId){
		return studentRepository.findByProgramId(programId);
	}
	
  @GetMapping(path = "/names")
  public List<StudentNameDto> getStudentsName() {
      return studentRepository.findAllNames()
	         .stream()
			 .map(s->new StudentNameDto(s.firstname(),s.lastname()))
			 .collect(Collectors.toList());
  
  }
}

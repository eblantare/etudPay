package com.blt.etud.etudpaybackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blt.etud.etudpaybackend.entities.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
	
	Optional<Student> findByCode(String code);
	List<Student> findByProgramId(String programId);

}

package com.blt.etud.etudpaybackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blt.etud.etudpaybackend.dtos.StudentNameDto;
import com.blt.etud.etudpaybackend.entities.Student;

public interface StudentRepository extends JpaRepository<Student, String> {
	
	Optional<Student> findByCode(String code);
	List<Student> findByProgramId(String programId);
	Optional<Student> findByFirstnameAndLastname(String firstname, String lastname);

	//Si tu as beaucoup d'étudiants, tu peux utiliser une requête JPQL pour ne récupérer
	// que les deux colonnes au lieu de charger tout.

	@Query("SELECT new com.blt.etud.etudpaybackend.dtos.StudentNameDto(s.firstname,s.lastname) FROM Student s")
	List<StudentNameDto>findAllNames();

}

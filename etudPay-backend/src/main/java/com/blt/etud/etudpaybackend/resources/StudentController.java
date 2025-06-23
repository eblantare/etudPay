package com.blt.etud.etudpaybackend.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blt.etud.etudpaybackend.dtos.MethodOfDtos;
import com.blt.etud.etudpaybackend.dtos.PayementsDto;
import com.blt.etud.etudpaybackend.dtos.StudentDto;
import com.blt.etud.etudpaybackend.dtos.StudentWithPayementsDto;
import com.blt.etud.etudpaybackend.entities.Payements;
import com.blt.etud.etudpaybackend.entities.Student;
import com.blt.etud.etudpaybackend.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
	@Autowired
	private final StudentRepository studentRepository;
	
	@PostMapping
	public ResponseEntity<StudentDto> createdStudent(@RequestBody StudentDto dto){
		Student student = fromDto(dto);
		Student saved = studentRepository.save(student);
		return ResponseEntity.ok(toDto(saved));
	}
	
	@PutMapping("/id")
	public ResponseEntity<?> updateStudent(@PathVariable String id, @RequestBody StudentDto dto){
		Student existing = studentRepository.findById(id).orElseThrow();
		          existing.setFirstname(dto.getFirstname());
		          existing.setLastname(dto.getLastname());
		          existing.setCode(dto.getCode());
		          existing.setProgramId(dto.getProgramId());
		          existing.setPhoto(dto.getPhoto());
		Student updated = studentRepository.save(existing);
		return ResponseEntity.ok(toDto(updated));
	}
	 // Méthodes de mapping (à externaliser proprement plus tard)
	//DTO vers Entité (pour insertion ou mise à jour)
	public Student fromDto(StudentDto dto) {
		return Student.builder()
				.id(dto.getId())  //Peut être null, en cas de création
				.firstname(dto.getFirstname())
				.lastname(dto.getLastname())
				.code(dto.getCode())
				.programId(dto.getProgramId())
				.photo(dto.getPhoto())
				.build();
	}
	//Entité vers DTO (pour renvoi API)
	public StudentDto toDto (Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.firstname(student.getFirstname())
				.lastname(student.getLastname())
				.code(student.getCode())
				.programId(student.getProgramId())
				.photo(student.getPhoto())
				.build();
	}
    
//GET /students/{id}
   @GetMapping("/{id}")
   public ResponseEntity<StudentWithPayementsDto> getStudentsWithPayements(@PathVariable String id){
	   Student stu = studentRepository.findById(id)
			   .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
	   StudentWithPayementsDto dto = toDetailsDto(stu);
	   return ResponseEntity.ok(dto);
   }
   
   private PayementsDto toPayementDto(Payements p) {
	   return PayementsDto.builder()
			   .id(p.getId())
			   .amount(p.getAmount())
			   .datePay(p.getDatePayement())
			   .type(p.getType())
			   .statut(p.getStatut())
			   .file(p.getFile())
			   .build();
   }
  private StudentWithPayementsDto toDetailsDto(Student student) {
		return StudentWithPayementsDto.builder()
				.id(student.getId())
				.firstname(student.getFirstname())
				.lastname(student.getLastname())
				.code(student.getCode())
				.programId(student.getProgramId())
				.photo(student.getPhoto())
				.payements(student.getPayements()!= null ? student.getPayements().stream()
						.map(this::toPayementDto)
						.toList():List.of())
				.build();
	}
}

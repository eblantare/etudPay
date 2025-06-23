package com.blt.etud.etudpaybackend.services;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.blt.etud.etudpaybackend.dtos.PayementsDto;
import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;
import com.blt.etud.etudpaybackend.entities.Payements;
import com.blt.etud.etudpaybackend.entities.Student;
import com.blt.etud.etudpaybackend.repositories.PayementRepository;
import com.blt.etud.etudpaybackend.repositories.StudentRepository;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
@Service
@Transactional
@RequiredArgsConstructor
public class PayementsServices {
	private final PayementRepository payementRepository;
	private final StudentRepository studentRepository;
	private PayementsDto dto;

	public PayementsDto toDto (Payements p) {
		return PayementsDto.builder()
				.id(p.getId())
				.amount(p.getAmount())
				.datePay(p.getDatePayement())
				.type(p.getType())
				.statut(p.getStatut())
				.file(p.getFile())
				.studentId(p.getStudent()!=null?p.getStudent().getId():null)
				.firstname(p.getStudent()!=null?p.getStudent().getFirstname():null)
				.lastname(p.getStudent()!=null?p.getStudent().getLastname():null)
				.build();
		
	}
	public List<PayementsDto> toDoList(List<Payements> payements){
		return payements.stream().map(this::toDto).toList();
	}
	
	public ResponseEntity<Payements> savePayement(MultipartFile file,Double amount,String studentCode, String date,PayementType type) 
					 throws IOException { 
		// création du dossier dans lequel seront stockés nos fichiers.
		Path folderPath = Paths.get(System.getProperty("user.home"),"blt-data","payements");
		//Vérfions l'existence du fichier
		if(!Files.exists(folderPath)) {
			Files.createDirectories(folderPath);
		}
		String fileName =UUID.randomUUID().toString();// Définition d'un nom de fichier unique
		Path filePath = folderPath.resolve(fileName+".pdf");
		Files.copy(file.getInputStream(), filePath);//Enregistrement du fichier
		System.out.println("Student code reçu : "+studentCode);
		Student student = studentRepository.findByCode(studentCode)
				.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student has not found!"));
		LocalDate datePaye = LocalDate.parse(date);//conversion
		Payements paye = Payements.builder()
				.datePayement(datePaye)
				.amount(amount)
				.type(type)
				.student(student)
				.statut(PayementStatut.CREATED)
				.file(filePath.toUri().toString())
				.build();
		return ResponseEntity.ok(payementRepository.save(paye));		
	}
	public byte[]getPayementFile(Long payementId) throws IOException{
		Payements payement = payementRepository.findById(payementId).get();
		return Files.readAllBytes(Path.of(URI.create(payement.getFile())));
		
	}
	
	public Payements updatePayementStatut( PayementStatut status, Long id) {
		Payements payement = payementRepository.findById(id).get();
		payement.setStatut(status);
		return payementRepository.save(payement);
		
	}

}

package com.blt.etud.etudpaybackend.web;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.blt.etud.etudpaybackend.dtos.PayementsDto;
import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;
import com.blt.etud.etudpaybackend.entities.Payements;
import com.blt.etud.etudpaybackend.entities.Student;
import com.blt.etud.etudpaybackend.repositories.PayementRepository;
import com.blt.etud.etudpaybackend.repositories.StudentRepository;
import com.blt.etud.etudpaybackend.services.PayementsServices;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PayementsRestController {

	private final PayementRepository payementRepository;
	private final StudentRepository studentRepository; 
	
	private final PayementsServices payementsServices;
	
	@GetMapping(path="/allPayements")
	public List<PayementsDto> getAllPayements(){
	List<Payements> lispPay = payementRepository.findAll();
			return payementsServices.toDoList(lispPay);
	}
	
	@GetMapping(path = "/payements/{id}")
	public Payements getPayementsByid(@PathVariable Long id) {
		return payementRepository.findById(id).get();
	}
	
	@GetMapping(path="/payements/byStatut")
	public List<Payements> getPayementByStatut(@RequestParam PayementStatut statut){
		return payementRepository.findByStatut(statut);
	}
	@GetMapping(path="/payements/byType")
	public List<Payements> getPayementByType(@RequestParam PayementType type){
		return payementRepository.findByType(type);
	}
	
	@GetMapping(path="/students/{code}/payements")
	public List<Payements> listPayementsByStudents(@PathVariable String code){
		return payementRepository.findByStudentCode(code);
		
	}
	
	@PutMapping("/payements/{id}")
	public Payements updatePayementStatut(@RequestParam PayementStatut status, @PathVariable Long id) {
		return payementsServices.updatePayementStatut(status, id);
		
	}
	
	@PostMapping(path="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Payements> savePayement(
			@Parameter(description = "PDF file to upload")
			@RequestPart("file") MultipartFile file,
			@Parameter(description = "Student code")
			@RequestPart("studentCode") String studentCode, 
			@Parameter(description = "Amount")
			@RequestPart("amount") Double amount,
			@Parameter(description = "Payement date (yyyy-MM-dd")
			@RequestPart("date") String date,
			@Parameter(description = "Payement type")
			@RequestPart("type") PayementType type) throws IOException { 
		return payementsServices.savePayement(file, amount, studentCode, date, type);
	}
//Méthode permettant de consulter le fichier
	@GetMapping(path="/payementFile/{payementId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[]getPayementFile(@PathVariable Long payementId) throws IOException{
		return payementsServices.getPayementFile(payementId);
		
	}
}

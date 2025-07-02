package com.blt.etud.etudpaybackend.web;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.blt.etud.etudpaybackend.dtos.PayementOptionsDto;
import com.blt.etud.etudpaybackend.dtos.PayementsDto;
import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;
import com.blt.etud.etudpaybackend.entities.Payements;
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
				if(!file.getContentType().equals("application/pdf")) {
					throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE,"Le fichier doit être un pdf");
				}
		return payementsServices.savePayement(file, amount, studentCode, date, type);
	}
//Méthode permettant de consulter le fichier
	@GetMapping(path="/payementFile/{payementId}", produces = MediaType.APPLICATION_PDF_VALUE)
	public byte[]getPayementFile(@PathVariable Long payementId) throws IOException{
		return payementsServices.getPayementFile(payementId);
		
	}

	@GetMapping(path="/payements/types")
	public PayementType[] listeType(){
		return PayementType.values();
	}

	@GetMapping(path = "/payements/status")
	public PayementStatut[]getAllStatus(){
		return PayementStatut.values();
	}

	@GetMapping(path = "/payement-options")
	public ResponseEntity<PayementOptionsDto> gstPayementOptions(){
		List<String> types=Arrays.stream(PayementType.values())
		                          .map(Enum::name).collect(Collectors.toList());
		List<String> statuts = Arrays.stream(PayementStatut.values())
		                          .map(Enum::name).collect(Collectors.toList());
		PayementOptionsDto dto = new PayementOptionsDto(types, statuts);
      return ResponseEntity.ok(dto);
	}

	@DeleteMapping("payement/{id}")
	public ResponseEntity<Void> deletePayement(@PathVariable Long id){
		boolean deleteP = payementsServices.deletePayById(id);
		if (deleteP)
		{return ResponseEntity.noContent().build();
		}else{
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(path = "/uploadFichier", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> addPayement(
		
			@RequestParam("file") MultipartFile file,
		
			@RequestParam("date") String date, 
		
			@RequestParam("amount") Double amount,
			
			@RequestParam("type") String type,
		
			@RequestParam("statut") String statut,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname
			){
				System.out.println("File: "+(file!=null?file.getOriginalFilename():"null"));
				System.out.println("Date :"+date);
				System.out.println("Amount :"+amount);
				System.out.println("type :"+type);
				System.out.println("statut :"+statut);
				System.out.println("firstname :"+firstname);
				System.out.println("lastname :"+lastname);
				return payementsServices.ajoutPay(file, date, amount, type, statut,firstname,lastname);
			}
		}

package com.blt.etud.etudpaybackend.dtos;

import java.time.LocalDate;

import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayementsDto {
	private Long id;
	private Double amount;
	private LocalDate datePay;
	private PayementType type;
	private PayementStatut statut;
	private String file;
	private String studentId;
	private String firstname;
	private String lastname;

}

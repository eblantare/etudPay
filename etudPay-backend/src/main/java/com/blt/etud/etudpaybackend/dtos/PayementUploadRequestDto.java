package com.blt.etud.etudpaybackend.dtos;

import com.blt.etud.etudpaybackend.entities.PayementType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayementUploadRequestDto {
	private String studentCode;
	private double amount;
	private String date; //string ici, pour que swagger gère bien le champ
	private PayementType type;
	

}

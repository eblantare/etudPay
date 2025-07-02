package com.blt.etud.etudpaybackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

	private String id;
	private String firstname;
	private String lastname;
	private String code;
	private String programId;
	private String photo;
}

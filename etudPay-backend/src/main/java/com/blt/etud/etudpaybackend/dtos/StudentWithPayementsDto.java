package com.blt.etud.etudpaybackend.dtos;

import java.util.List;

import com.blt.etud.etudpaybackend.entities.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentWithPayementsDto { /*Tu veux afficher un étudiant + ses paiements (ex. GET /students/{id} détaillé)
	👉 Dans ce cas, tu crées un StudentDetailsDTO ou StudentWithPayementsDTO, par exemple :*/
	private String id;
	private String firstname;
	private String lastname;
	private String code;
	private String programId;
	private String photo;
	private List<PayementsDto> payements;//liste transformée proprement
	


}

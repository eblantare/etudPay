package com.blt.etud.etudpaybackend.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.*;


@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
//@Table(name="univ_student")
public class Student {

	@Id 
	@Column(name="student_Id")
	private String id;
//  permet à Hibernate de faire la gestion de concurrence optiministe et d'éviter l'erreur
	@Version
    private Long version;
	@Column(name="student_first_name")
	private String firstname;
	@Column(name="student_last_name")
	private String lastname;
	@Column(name="student_code",unique = true)
	private String code;
	@Column(name="student_program_id")
	private String programId;
	@Column(name="student_photo")
	private String photo;
	@OneToMany(mappedBy ="student", cascade = CascadeType.ALL)

    private List<Payements> payements;
	
	/* methode garantissant que l'id sera générer automatiquement juste avant la première insertion en base*/
	@PrePersist
	public void prepersist() {
		if(this.id == null || this.id.isEmpty()) {
			this.id = java.util.UUID.randomUUID().toString();
		}
	}
}

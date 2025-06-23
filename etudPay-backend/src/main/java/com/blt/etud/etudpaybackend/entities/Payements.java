package com.blt.etud.etudpaybackend.entities;

import java.time.LocalDate;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Payements {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="payement_id")
	private Long id;
   @Column(name="payement_date")
	private LocalDate datePayement;
   @Column(name="payement_amount")
	private double amount;
   @Enumerated(EnumType.STRING)
   @Column(name="payement_type")
	private PayementType type;
   @Enumerated(EnumType.STRING)
   @Column(name="payement_statut")
	private PayementStatut statut;
   @Column(name="payement_file")
	private String file;
//   @Column(name="payement_student")
   @ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
   @JoinColumn(name="student_Id",referencedColumnName="student_Id")

	private Student student;
}

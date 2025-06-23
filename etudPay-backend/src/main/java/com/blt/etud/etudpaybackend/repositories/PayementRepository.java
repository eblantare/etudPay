package com.blt.etud.etudpaybackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blt.etud.etudpaybackend.entities.PayementStatut;
import com.blt.etud.etudpaybackend.entities.PayementType;
import com.blt.etud.etudpaybackend.entities.Payements;

public interface PayementRepository extends JpaRepository<Payements, Long>{
	List<Payements> findByStudentCode(String code);
	List<Payements> findByStatut(PayementStatut statut);
	List<Payements> findByType(PayementType type);
//	List<Payements> findByStudentId(String studentId);

}

package com.blt.etud.etudpaybackend.securities.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blt.etud.etudpaybackend.securities.entities.Utilisateurs;

public interface UtilisateurRepository extends JpaRepository<Utilisateurs,Long>{
            Optional<Utilisateurs> findByUsername(String username);
    
} 

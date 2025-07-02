package com.blt.etud.etudpaybackend.securities.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blt.etud.etudpaybackend.securities.dtos.LoginRequest;
import com.blt.etud.etudpaybackend.securities.entities.Utilisateurs;
import com.blt.etud.etudpaybackend.securities.repositories.UtilisateurRepository;


import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;






@RestController
@RequestMapping("/api/admin")
// @PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  UtilisateurRepository utilisateurRepository;

    @PostMapping("/users")
    public Utilisateurs createUser(@RequestBody Utilisateurs utilisateurs) {
        utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
        
        return utilisateurRepository.save(utilisateurs);
    } 

    @GetMapping
    public List<Utilisateurs> ListUsers() {
        return utilisateurRepository.findAll();
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication auth) {
             // Renvoie l'utilisateur connecté
        return ResponseEntity.ok(Map.of("username", auth.getName(),"roles",auth.getAuthorities()));
    }

    
    
}

package com.blt.etud.etudpaybackend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blt.etud.etudpaybackend.securities.entities.Utilisateurs;
import com.blt.etud.etudpaybackend.securities.repositories.UtilisateurRepository;

@Component
public class AdminInitializer implements CommandLineRunner {
private final UtilisateurRepository utilisateurRepository;
private PasswordEncoder passwordEncoder;

public AdminInitializer(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder){
    this.utilisateurRepository = utilisateurRepository;
    this.passwordEncoder = passwordEncoder;
}

public void run(String...args)  throws Exception{
    //Vérifier si admin existe déjà
    if(utilisateurRepository.findByUsername("admin").isEmpty()){
        Utilisateurs admin = Utilisateurs.builder()
        .id(null)
        .nom("super")
        .prenoms("admin")
        .email("admin@exemple.com")
        .username("admin")
        .password(passwordEncoder.encode("admin123"))
        .roles("ADMIN")
        .enabled(true)
        .build();

        utilisateurRepository.save(admin);
        System.out.println("✅ Utilisateur admin créé : admin / admin123");
    }else{
        System.out.println("ℹ️ Utilisateur admin déjà existant");
    }
}
}

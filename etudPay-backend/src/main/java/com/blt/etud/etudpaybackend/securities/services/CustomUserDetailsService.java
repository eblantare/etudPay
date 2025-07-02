package com.blt.etud.etudpaybackend.securities.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blt.etud.etudpaybackend.securities.entities.Utilisateurs;
import com.blt.etud.etudpaybackend.securities.repositories.UtilisateurRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository){
        this.utilisateurRepository = utilisateurRepository;
    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    Utilisateurs utilisateurs = this.utilisateurRepository.findByUsername(username)
    .orElseThrow(()->new UsernameNotFoundException("Utilisateur non trouvé"));
    
    // Gérer plusieurs rôles séparés par des virgules (ex: "ADMIN,USER")
    List<SimpleGrantedAuthority> authorities = Arrays.stream(utilisateurs.getRoles().split(","))
    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
    .collect(Collectors.toList());
  // Retourner un UserDetails avec toutes les propriétés
    return new org.springframework.security.core.userdetails
    .User(utilisateurs.getUsername(), utilisateurs.getPassword(),utilisateurs.isEnabled(),
    true,true,
    true,authorities);
}

}

package com.blt.etud.etudpaybackend.securities.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
@Entity
@Table(name = "users")
 @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder

public class Utilisateurs {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_nom")
    private String nom;
    @Column(name = "user_prenoms")
    private String prenoms;
    @Column(name = "user_roles")
    private String roles;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_username")
    private String username;
    @Column(name = "user_password")
    private String password;
    private boolean enabled = true;



}

package com.blt.etud.etudpaybackend.securities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisateurDto {
    private String id;
    private String nom;
    private String prenoms;
    private String username;
    private String password;
    private String roles;
    private String description;
    private String email;
    private boolean enabled;

}

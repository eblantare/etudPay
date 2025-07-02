package com.blt.etud.etudpaybackend.dtos;

//Créer un DTO (pour éviter de retourner toute l'entité Student inutilement) :

public record StudentNameDto(String firstname, String lastname) {

}

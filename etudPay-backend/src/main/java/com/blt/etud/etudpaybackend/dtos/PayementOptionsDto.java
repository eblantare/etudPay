package com.blt.etud.etudpaybackend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayementOptionsDto {
    private List<String> types;
    List<String> statuts;

}

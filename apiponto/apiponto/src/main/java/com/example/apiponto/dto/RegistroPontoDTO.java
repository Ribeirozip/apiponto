package com.example.apiponto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroPontoDTO {
    private Long id;
    private Long usuarioId;
    private String tipo;
    private LocalDateTime horario;
}
